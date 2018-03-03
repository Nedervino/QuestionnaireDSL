package ql.parser;


import ql.QLBaseVisitor;
import ql.QLParser;
import ql.ast.ASTNode;
import ql.ast.FormNode;
import ql.ast.expressions.*;
import ql.ast.expressions.binary.*;
import ql.ast.expressions.literals.BooleanLiteral;
import ql.ast.expressions.literals.IntegerLiteral;
import ql.ast.expressions.literals.StringLiteral;
import ql.ast.expressions.unary.MinusNode;
import ql.ast.expressions.unary.NegNode;
import ql.ast.expressions.IDNode;
import ql.ast.statements.*;
import ql.ast.types.Type;

import java.util.ArrayList;
import java.util.List;


//TODO define classes for binary and unary operations. When constructing the ql.ast, make sure that each ExprNum is replaced with a ExprNumUnary, or ExprNumBinary. Do the same
//for boolean and strings. With booleans, we might have fiddle with the comparisons to make sure they are recognized as boolean expressions.

public class ASTConstructionVisitor extends QLBaseVisitor<ASTNode> {

    // public ASTNode visitTerminal(TerminalNode node) {
    //
    //     String content = node.getText();
    //
    //
    //     String[] irrelevant = new String[]{
    //             "form",
    //             "{",
    //             "}",
    //             ":",
    //             "if",
    //     };
    //
    //     for(String str : irrelevant){
    //         if(content.equals(str)){
    //             return null;
    //         }
    //     }
    //
    //     String[] op = new String[]{
    //             "!",
    //             "(",
    //             ")",
    //             "-",
    //             "&&",
    //             "||",
    //             "==",
    //             "!=",
    //             "<",
    //             ">",
    //             "<=",
    //             "=>",
    //             "+",
    //             "*",
    //             "/"
    //     };
    //
    //     for(String str : op){
    //         if(content.equals(str)){
    //             return new OpSymHelperNode(content);
    //         }
    //     }
    //
    //     //check whether the terminal is an int or some other numeric value.
    //     if(isInt(content)){
    //         return new ValNode(content);
    //     }
    //     else if(isMoney(content)){
    //         return new ValNode(content);
    //     }
    //     else if(isFloat(content)){
    //         return new ValNode(content);
    //     }
    //
    //     //Keywords with semantic value
    //     switch (content) {
    //         case "true":{
    //             return new ValNode(content);
    //         }
    //         case "false":{
    //             return new ValNode(content);
    //         }
    //         case "boolean": {
    //             return new TypeNode(content);
    //         }
    //         case "money": {
    //             return new TypeNode(content);
    //         }
    //         case "int": {
    //             return new TypeNode(content);
    //         }
    //         case "float": {
    //             return new TypeNode(content);
    //         }
    //         case "string": {
    //             return new TypeNode(content);
    //         }
    //     }
    //
    //     //String literal check
    //     if(content.substring(0, 1).equals("\"")) {
    //         return new ValNode(content.substring(1, content.length()-1));
    //     }
    //
    //     //Anything else was a var
    //     return new IDNode(content);
    //
    // }
    //
    // public static boolean isFloat(String str) {
    //     try {
    //         Double.parseDouble(str);
    //     }
    //     catch(NumberFormatException nfe) {
    //         return false;
    //     }
    //     return true;
    // }
    //
    // //TODO Add a distinct function for money.
    // public static boolean isMoney(String str){
    //     return isFloat(str);
    // }
    //
    // public static boolean isInt(String str){
    //     try {
    //         Integer.parseInt(str);
    //     }
    //     catch(NumberFormatException nfe) {
    //         return false;
    //     }
    //     return true;
    // }
    //
    //
    // @Override
    // protected ASTNode defaultResult() {
    //     return null;   //This use of ASTNode should be eliminated, by creating a class for each type of node.
    // }
    //
    // //aggregate is the tree which the parent is constructing
    // //nextResult should be the child node
    // //This method should add the node to the parent's child list
    // @Override
    // protected ASTNode aggregateResult(ASTNode aggregate, ASTNode nextResult) {
    //     if(nextResult==null){
    //         return aggregate;
    //     }
    //     aggregate.children.add(nextResult);
    //     return aggregate;
    // }

    @Override
    public ASTNode visitForm(QLParser.FormContext ctx) {
        String formId = ctx.IDENTIFIER().getText();
        List<Statement> statements = new ArrayList();
        ctx.block().statement().forEach(statementContext -> statements.add((Statement) visit(statementContext)));

        return new FormNode(formId, statements);
    }

    @Override
    public ASTNode visitQuestion(QLParser.QuestionContext ctx) {
        String id = ctx.declaration().IDENTIFIER().getText();
        String label = ctx.STRINGLITERAL().getText();
        Type type = (Type) visit(ctx.declaration().TYPE());

        return new QuestionNode(id, label, type);
    }

    @Override
    public ASTNode visitComputedQuestion(QLParser.ComputedQuestionContext ctx) {
        String id = ctx.declaration().IDENTIFIER().getText();
        String label = ctx.STRINGLITERAL().getText();
        Type type = (Type) visit(ctx.declaration().TYPE());
        Expression expression = (Expression) visit(ctx.expression());

        return new ComputedQuestionNode(id, label, type, expression);
    }

    @Override
    public ASTNode visitIfStatement(QLParser.IfStatementContext ctx) {
        Expression condition = (Expression) visit(ctx.expression());
        List<Statement> statements = new ArrayList<>();
        ctx.block().statement().forEach(statementContext -> statements.add((Statement) visit(statementContext)));

        if (ctx.elseBlock() != null) {
            List<Statement> elseStatements = new ArrayList<>();
            ctx.elseBlock().block().statement().forEach(statementContext -> elseStatements.add((Statement) visit(statementContext)));
            return new IfElseStatementNode(condition, statements, elseStatements);
        } else {
            return new IfStatementNode(condition, statements);
        }
    }

    @Override
    public ASTNode visitUnaryExpression(QLParser.UnaryExpressionContext ctx) {
        String operator = ctx.unaryOperation().UNARY().getText();
        switch (operator) {
            case "-":
                return new MinusNode((Expression) visit(ctx.unaryOperation().expression()));
            case "!":
                return new NegNode((Expression) visit(ctx.unaryOperation().expression()));
            default:
                throw new IllegalArgumentException(String.format("Invalid unary operator: %s", operator));
        }
    }

    @Override
    public ASTNode visitArithMeticBinary(QLParser.ArithMeticBinaryContext ctx) {
        Expression left = (Expression) visit(ctx.left);
        Expression right = (Expression) visit(ctx.right);

        String operator = ctx.ARITHMETIC().getText();
        switch (operator) {
            case "+":
                return new AddNode(left, right);
            case "-":
                return new SubNode(left, right);
            case "*":
                return new MulNode(left, right);
            case "/":
                return new DivNode(left, right);
            default:
                throw new IllegalArgumentException(String.format("Invalid arithmetic operator: %s", operator));
        }
    }

    @Override
    public ASTNode visitLogicalBinary(QLParser.LogicalBinaryContext ctx) {
        Expression left = (Expression) visit(ctx.left);
        Expression right = (Expression) visit(ctx.right);

        String operator = ctx.LOGICAL().getText();
        switch (operator) {
            case "&&":
                return new AndNode(left, right);
            case "||":
                return new OrNode(left, right);
            default:
                throw new IllegalArgumentException(String.format("Invalid logical operator: %s", operator));
        }
    }

    @Override
    public ASTNode visitRelationalBinary(QLParser.RelationalBinaryContext ctx) {
        Expression left = (Expression) visit(ctx.left);
        Expression right = (Expression) visit(ctx.right);

        String operator = ctx.RELATIONAL().getText();
        switch (operator) {
            case "<":
                return new LtNode(left, right);
            case "<=":
                return new LteNode(left, right);
            case ">":
                return new GtNode(left, right);
            case ">=":
                return new GteNode(left, right);
            case "==":
                return new EqNode(left, right);
            case "!=":
                return new NeqNode(left, right);
            default:
                throw new IllegalArgumentException(String.format("Invalid relational operator: %s", operator));
        }
    }

    // @Override
    // public ASTNode visitNestedExpression(QLParser.NestedExpressionContext ctx) {
    //     return super.visitNestedExpression(ctx);
    // }


    @Override
    public ASTNode visitBooleanLiteral(QLParser.BooleanLiteralContext ctx) {
        return new BooleanLiteral(ctx.getText());
    }

    @Override
    public ASTNode visitStringLiteral(QLParser.StringLiteralContext ctx) {
        return new StringLiteral(ctx.getText());
    }

    @Override
    public ASTNode visitIntegerLiteral(QLParser.IntegerLiteralContext ctx) {
        return new IntegerLiteral(ctx.getText());
    }

    @Override
    public ASTNode visitIdentifier(QLParser.IdentifierContext ctx) {
        return new IDNode(ctx.getText());
    }

}
