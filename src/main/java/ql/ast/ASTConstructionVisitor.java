package ql.ast;


import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import ql.QLBaseVisitor;
import ql.QLParser;
import ql.ast.expressions.ExprBoolNode;
import ql.ast.expressions.ExprNode;
import ql.ast.expressions.ExprNumNode;
import ql.ast.expressions.ExprStrNode;
import ql.ast.operations.CompNode;
import ql.ast.operations.CompSymNode;
import ql.ast.operations.OpNode;
import ql.ast.values.IDNode;
import ql.ast.values.ValBoolNode;
import ql.ast.values.ValIntNode;
import ql.ast.values.ValStrNode;
import ql.ast.statements.*;


//TODO define classes for binary and unary operations. When constructing the ql.ast, make sure that each ExprNum is replaced with a ExprNumUnary, or ExprNumBinary. Do the same
//for boolean and strings. With booleans, we might have fiddle with the comparisons to make sure they are recognized as boolean expressions.

public class ASTConstructionVisitor extends QLBaseVisitor<ASTNode> {

    public ASTNode visitTerminal(TerminalNode node) {

        String content = node.getText();


        String[] irrelevant = new String[]{
                "form",
                "{",
                "}",
                ":",
                "if",
        };

        for(String str : irrelevant){
            if(content.equals(str)){
                return null;
            }
        }

        String[] comparison = new String[]{
                "==",
                "<",
                ">",
                "<=",
                "=>"
        };

        for(String str : comparison){
            if(content.equals(str)){
                return new CompSymNode(content);
            }
        }

        String[] op = new String[]{
                "&&",
                "||",
                "+",
                "-",
                "*",
                "/",
                "(",
                ")"
        };

        for(String str : op){
            if(content.equals(str)){
                return new OpNode(content);
            }
        }

        //check whether the terminal is an int or some other numeric value.
        if(isInt(content)){
            return new ValIntNode(content);
        }
        else if(isMoney(content)){
            return new ValIntNode(content);
        }
        else if(isFloat(content)){
            return new ValIntNode(content);
        }

        //Keywords with semantic value
        switch (content) {
            case "true":{
                return new ValBoolNode(content);
            }
            case "false":{
                return new ValBoolNode(content);
            }
            case "boolean": {
                return new TypeNode(content);
            }
            case "money": {
                return new TypeNode(content);
            }
            case "int": {
                return new TypeNode(content);
            }
            case "float": {
                return new TypeNode(content);
            }
            case "string": {
                return new TypeNode(content);
            }
        }

        //String literal check
        if(content.substring(0, 1).equals("\"")) {
            return new ValStrNode(content.substring(1, content.length()-1));
        }

        //Anything else was a var
        return new IDNode(content);

    }

    public static boolean isFloat(String str) {
        try {
            Double.parseDouble(str);
        }
        catch(NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    //TODO Add a distinct function for money.
    public static boolean isMoney(String str){
        return isFloat(str);
    }

    public static boolean isInt(String str){
        try {
            Integer.parseInt(str);
        }
        catch(NumberFormatException nfe) {
            return false;
        }
        return true;
    }


    @Override
    protected ASTNode defaultResult() {
        return null;   //This use of ASTNode should be eliminated, by creating a class for each type of node.
    }

    //aggregate is the tree which the parent is constructing
    //nextResult should be the child node
    //This method should add the node to the parent's child list
    @Override
    protected ASTNode aggregateResult(ASTNode aggregate, ASTNode nextResult) {
        if(nextResult==null){
            return aggregate;
        }
        aggregate.children.add(nextResult);
        return aggregate;
    }

    public ASTNode visitChildren(RuleNode node, ASTNode parentNode) {
        ASTNode result = parentNode;
        int n = node.getChildCount();

        for(int i = 0; i < n && this.shouldVisitNextChild(node, result); ++i) {
            ParseTree c = node.getChild(i);
            ASTNode childResult = c.accept(this);
            result = this.aggregateResult(result, childResult);
        }

        return result;
    }

    @Override
    public ASTNode visitForm(QLParser.FormContext ctx) {
        // return visitChildren(ctx, new FormNode());
        FormNode fn = new FormNode();
        TerminalNode labelNode = (TerminalNode)ctx.children.get(1);
        IDNode in = (IDNode) visitTerminal(labelNode);
        fn.label = in.getContent();
        fn.block = visit(ctx.children.get(2)).children;
        return fn;
    }

    @Override
    public ASTNode visitBlock(QLParser.BlockContext ctx) {
        return visitChildren(ctx, new BlockNode());
    }

    @Override
    public ASTNode visitStatement(QLParser.StatementContext ctx) {
        // return visitChildren(ctx);
        return visit(ctx.children.get(0));
    }

    @Override
    public ASTNode visitQuestion(QLParser.QuestionContext ctx) {
        // return visitChildren(ctx);
        QuestionNode in = new QuestionNode();
        TerminalNode labelNode = (TerminalNode)ctx.children.get(0);
        ValStrNode ln = (ValStrNode)visitTerminal(labelNode);
        in.setLabel(ln.getContent());
        DeclarationNode dn = (DeclarationNode) visit(ctx.children.get(1));
        in.setId(dn.getId());
        in.setType(dn.getType());
        return in;
    }

    @Override
    public ASTNode visitDeclaration(QLParser.DeclarationContext ctx) {
        // return visitChildren(ctx);
        DeclarationNode dn = new DeclarationNode();
        IDNode in = (IDNode)visit(ctx.children.get(0));
        dn.setId(in.getContent());
        TypeNode tn = (TypeNode)visit(ctx.children.get(2));
        dn.setType(tn.getContent());
        return dn;
    }

    @Override
    public ASTNode visitComputedQuestion(QLParser.ComputedQuestionContext ctx) {
        // return visitChildren(ctx);
        ComputedQuestionNode on = new ComputedQuestionNode();
        TerminalNode labelNode = (TerminalNode)ctx.children.get(0);
        ValStrNode vn = ((ValStrNode)visitTerminal(labelNode));
        on.setLabel(vn.getContent());
        AssignmentNode an = (AssignmentNode)visit(ctx.children.get(1));
        on.setId(an.getId());
        on.setType(an.getType());
        on.setExpr(an.getExpr());
        return on;
    }

    @Override
    public ASTNode visitAssignment(QLParser.AssignmentContext ctx) {
        // return visitChildren(ctx);
        AssignmentNode an = new AssignmentNode();
        DeclarationNode dn = (DeclarationNode) visit(ctx.children.get(0));
        an.setId(dn.getId());
        an.setType(dn.getType());
        ExprNode en = (ExprNode) visit(ctx.children.get(2));
        an.setExpr(en);
        return an;
    }

    @Override
    public ASTNode visitIfStatement(QLParser.IfStatementContext ctx) {
        // return visitChildren(ctx);
        IfStatementNode en = new IfStatementNode();
        en.setCond((ExprNode) visit(ctx.children.get(2)));
        ASTNode blockNode = visit(ctx.children.get(4));
        en.setBlock(blockNode.children);
        return en;
    }

    @Override
    public ASTNode visitElseBlock(QLParser.ElseBlockContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitExpr(QLParser.ExprContext ctx) {
        // return visitChildren(ctx);
        return visit(ctx.children.get(0));
    }

    //Catch valnum

    @Override
    public ASTNode visitExprBool(QLParser.ExprBoolContext ctx) {
        // return visitChildren(ctx, new ExprBoolNode());

        //Must be a value or id
        if(ctx.children.size()==1) {
            return visit(ctx.children.get(0));
        }
        //Must be a negation
        else if(ctx.children.size()==2){
            ExprBoolNode en = new ExprBoolNode();
            TerminalNode symbolNode = (TerminalNode)ctx.children.get(0);
            OpNode on = (OpNode)visitTerminal(symbolNode);
            en.setSymbol(on.getContent());
            en.setFirst((ExprNode)visit(ctx.children.get(1)));
            return en;
        }
        //Must be parenthesis
        else if(ctx.children.get(0) instanceof TerminalNode){
            ExprBoolNode en = new ExprBoolNode();
            TerminalNode symbolNode = (TerminalNode)ctx.children.get(0);
            OpNode on = (OpNode)visitTerminal(symbolNode);
            en.setSymbol(on.getContent());
            en.setFirst((ExprNode)visit(ctx.children.get(1)));
            return en;
        }
        //must be a binary operation
        else{
            TerminalNode opNode = (TerminalNode)ctx.children.get(1);
            OpNode on = (OpNode)visitTerminal(opNode);
            //must be a comparison
            //TODO leave this instanceof condition to a BinaryOp class which can handle types better
            if(on instanceof CompSymNode) {
                CompNode cn = new CompNode();
                //TODO rename symbol variable to op in all classes (or the superclass)
                cn.setSymbol(on.getContent());
                cn.setFirst(visit(ctx.children.get(0)));
                cn.setSecond(visit(ctx.children.get(2)));
                return cn;
            }
            //must be a boolean binary operation
            else{
                ExprBoolNode en = new ExprBoolNode();
                en.setSymbol(on.getContent());
                en.setFirst((ExprNode)visit(ctx.children.get(0)));
                en.setSecond((ExprNode)visit(ctx.children.get(2)));
                return en;
            }
        }
    }

    @Override
    public ASTNode visitCompNum(QLParser.CompNumContext ctx) {
        // return visitChildren(ctx);
        CompNode cn = new CompNode();
        cn.setFirst(visit(ctx.children.get(0)));
        TerminalNode symbolNode = (TerminalNode)ctx.children.get(1);
        CompSymNode csn = (CompSymNode)visitTerminal(symbolNode);
        cn.setSymbol(csn.getContent());
        cn.setSecond(visit(ctx.children.get(2)));
        return cn;
    }

    @Override
    public ASTNode visitCompStr(QLParser.CompStrContext ctx) {
        // return visitChildren(ctx);
        CompNode cn = new CompNode();
        cn.setFirst(visit(ctx.children.get(0)));
        TerminalNode symbolNode = (TerminalNode)ctx.children.get(1);
        CompSymNode csn = (CompSymNode)visitTerminal(symbolNode);
        cn.setSymbol(csn.getContent());
        cn.setSecond(visit(ctx.children.get(2)));
        return cn;
    }

    @Override
    public ASTNode visitValBool(QLParser.ValBoolContext ctx) {
        // return visitChildren(ctx);
        return visit(ctx.children.get(0));
    }

    //Note: Every numerical expression is an operation.
    @Override
    public ASTNode visitExprNum(QLParser.ExprNumContext ctx) {
        // return visitChildren(ctx, new ExprNumNode());

        //Must be a value or id
        if(ctx.children.size()==1) {
            return visit(ctx.children.get(0));
        }
        //Must be a negation
        else if(ctx.children.size()==2){
            ExprNumNode en = new ExprNumNode();
            TerminalNode symbolNode = (TerminalNode)ctx.children.get(0);
            OpNode on = (OpNode)visitTerminal(symbolNode);
            en.setSymbol(on.getContent());
            en.setFirst((ExprNode)visit(ctx.children.get(1)));
            return en;
        }
        //Must be parenthesis
        else if(ctx.children.get(0) instanceof TerminalNode){
            ExprNumNode en = new ExprNumNode();
            TerminalNode symbolNode = (TerminalNode)ctx.children.get(0);
            OpNode on = (OpNode)visitTerminal(symbolNode);
            en.setSymbol(on.getContent());
            en.setFirst((ExprNode)visit(ctx.children.get(1)));
            return en;
        }
        //must be a binary operation
        else{
            ExprNumNode en = new ExprNumNode();
            en.setFirst((ExprNode)visit(ctx.children.get(0)));
            TerminalNode symbolNode = (TerminalNode)ctx.children.get(1);
            OpNode on = (OpNode)visitTerminal(symbolNode);
            en.setSymbol(on.getContent());
            en.setSecond((ExprNode)visit(ctx.children.get(2)));
            return en;
        }
    }

    @Override
    public ASTNode visitValNum(QLParser.ValNumContext ctx) {
        // return visitChildren(ctx);
        return visit(ctx.children.get(0));
    }

    @Override
    public ASTNode visitExprStr(QLParser.ExprStrContext ctx) {
        // return visitChildren(ctx, new ExprStrNode());

        //Must be a value or id
        if(ctx.children.size()==1) {
            return visit(ctx.children.get(0));
        }
        //Must be parenthesis
        else if(ctx.children.get(0) instanceof TerminalNode){
            ExprStrNode en = new ExprStrNode();
            TerminalNode symbolNode = (TerminalNode)ctx.children.get(0);
            OpNode on = (OpNode)visitTerminal(symbolNode);
            en.setSymbol(on.getContent());
            en.setFirst((ExprNode)visit(ctx.children.get(1)));
            return en;
        }
        //must be the binary operation, addition
        else{
            ExprStrNode en = new ExprStrNode();
            en.setFirst((ExprNode)visit(ctx.children.get(0)));
            TerminalNode symbolNode = (TerminalNode)ctx.children.get(1);
            OpNode on = (OpNode) visitTerminal(symbolNode);
            en.setSymbol(on.getContent());
            en.setSecond((ExprNode)visit(ctx.children.get(2)));
            return en;
        }
    }

    //A valStr can only contain an actual string assigned as STRLIT, or an ID which refers to a variable.
    @Override
    public ASTNode visitValStr(QLParser.ValStrContext ctx) {
        // return visitChildren(ctx);
        return visit(ctx.children.get(0));
    }
    
}
