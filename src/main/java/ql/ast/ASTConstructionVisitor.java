package ql.ast;


import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import ql.QLBaseVisitor;
import ql.QLParser;
import ql.ast.expressions.ExprNode;
import ql.ast.expressions.BinOpNode;
import ql.ast.expressions.OpSymNode;
import ql.ast.expressions.UnOpNode;
import ql.ast.values.*;
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

        String[] op = new String[]{
                "!",
                "(",
                ")",
                "-",
                "&&",
                "||",
                "==",
                "<",
                ">",
                "<=",
                "=>",
                "+",
                "*",
                "/"
        };

        for(String str : op){
            if(content.equals(str)){
                return new OpSymNode(content);
            }
        }

        //check whether the terminal is an int or some other numeric value.
        if(isInt(content)){
            return new ValNode(content);
        }
        else if(isMoney(content)){
            return new ValNode(content);
        }
        else if(isFloat(content)){
            return new ValNode(content);
        }

        //Keywords with semantic value
        switch (content) {
            case "true":{
                return new ValNode(content);
            }
            case "false":{
                return new ValNode(content);
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
            return new ValNode(content.substring(1, content.length()-1));
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
        ValNode ln = (ValNode)visitTerminal(labelNode);
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
        ValNode vn = ((ValNode)visitTerminal(labelNode));
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
        //Must be a value or id
        if(ctx.children.size()==1) {
            return visit(ctx.children.get(0));
        }
        //Must be a negation
        else if(ctx.children.size()==2){
            UnOpNode un = new UnOpNode();
            TerminalNode symbolNode = (TerminalNode)ctx.children.get(0);
            OpSymNode on = (OpSymNode)visitTerminal(symbolNode);
            un.setSymbol(on.getContent());
            un.setTerm((ExprNode)visit(ctx.children.get(1)));
            return un;
        }
        //Must be parenthesis
        else if(ctx.children.get(0) instanceof TerminalNode){
            UnOpNode un = new UnOpNode();
            TerminalNode symbolNode = (TerminalNode)ctx.children.get(0);
            OpSymNode on = (OpSymNode)visitTerminal(symbolNode);
            un.setSymbol(on.getContent());
            un.setTerm((ExprNode)visit(ctx.children.get(1)));
            return un;
        }
        //must be a binary operation
        else{
            BinOpNode bn = new BinOpNode();
            TerminalNode opNode = (TerminalNode)ctx.children.get(1);
            OpSymNode on = (OpSymNode)visitTerminal(opNode);
            bn.setSymbol(on.getContent());
            bn.setTerm((ExprNode)visit(ctx.children.get(0)));
            bn.setSecond((ExprNode)visit(ctx.children.get(2)));
            return bn;
        }
    }

    @Override
    public ASTNode visitVal(QLParser.ValContext ctx) {
        // return visitChildren(ctx);
        return visit(ctx.children.get(0));
    }

}
