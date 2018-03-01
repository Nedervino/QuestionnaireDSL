package ql.ast;


import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import ql.QLBaseVisitor;
import ql.QLParser;
import ql.ast.expressions.*;
import ql.ast.expressions.binary.*;
import ql.ast.expressions.unary.NegNode;
import ql.ast.expressions.unary.ParNode;
import ql.ast.expressions.values.IDNode;
import ql.ast.expressions.values.ValNode;
import ql.ast.statements.*;

import java.util.ArrayList;
import java.util.List;


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
                "!=",
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
                return new OpSymHelperNode(content);
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
        String formId = ctx.ID().getText();
        List<Statement> statements = new ArrayList();
        ctx.block().statement().forEach(statementContext -> statements.add((Statement) visit(statementContext)));

        return new FormNode(formId, statements);
    }

    @Override
    public ASTNode visitQuestion(QLParser.QuestionContext ctx) {
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
        DeclarationNode dn = new DeclarationNode();
        IDNode in = (IDNode)visit(ctx.children.get(0));
        dn.setId(in.getContent());
        TypeNode tn = (TypeNode)visit(ctx.children.get(2));
        dn.setType(tn.getContent());
        return dn;
    }

    @Override
    public ASTNode visitComputedQuestion(QLParser.ComputedQuestionContext ctx) {
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
        ExprNode condition = (ExprNode) visit(ctx.expr());
        List<Statement> statements = new ArrayList<>();
        ctx.block().statement().forEach(statementContext -> statements.add((Statement) visit(statementContext)));
        IfStatementNode ifStatementNode = new IfStatementNode(condition, statements);
        return ifStatementNode;
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
            NegNode nn = new NegNode();
            nn.setTerm((ExprNode)visit(ctx.children.get(1)));
            return nn;
        }
        //Must be parenthesis
        //TODO get rid of this instanceof
        else if(ctx.children.get(0) instanceof TerminalNode){
            ParNode pn = new ParNode();
            pn.setTerm((ExprNode)visit(ctx.children.get(1)));
            return pn;
        }
        //must be a binary operation
        else{
            TerminalNode opNode = (TerminalNode)ctx.children.get(1);
            OpSymHelperNode on = (OpSymHelperNode)visitTerminal(opNode);

            String symbol = on.getContent();

            BinOpNode bn = null;

            switch(symbol){
                case "&&":{
                    bn = new AndNode();
                    break;
                }
                case "||":{
                    bn = new OrNode();
                    break;
                }
                case "==":{
                    bn = new EqNode();
                    break;
                }
                case "!=":{
                    bn = new NeqNode();
                    break;
                }
                case "<":{
                    bn = new LtNode();
                    break;
                }
                case ">":{
                    bn = new GtNode();
                    break;
                }
                case "<=":{
                    bn = new LteNode();
                    break;
                }
                case "=>":{
                    bn = new GteNode();
                    break;
                }
                case "+":{
                    bn = new AddNode();
                    break;
                }
                case "*":{
                    bn = new MulNode();
                    break;
                }
                case "/":{
                    bn = new DivNode();
                    break;
                }
            }

            bn.setFirst((ExprNode)visit(ctx.children.get(0)));
            bn.setSecond((ExprNode)visit(ctx.children.get(2)));
            return bn;
        }
    }

    @Override
    public ASTNode visitVal(QLParser.ValContext ctx) {
        return visit(ctx.children.get(0));
    }

}
