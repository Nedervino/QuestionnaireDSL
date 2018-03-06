package ql.parser;

import ql.QLBaseVisitor;
import ql.QLParser;
import ql.ast.ASTNode;
import ql.ast.Form;
import ql.ast.expressions.Expression;
import ql.ast.expressions.Variable;
import ql.ast.expressions.binary.*;
import ql.ast.expressions.literals.*;
import ql.ast.expressions.unary.ArithmeticNegation;
import ql.ast.expressions.unary.LogicalNegation;
import ql.ast.statements.*;
import ql.ast.types.Type;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


//TODO define classes for binary and unary operations. When constructing the ql.ast, make sure that each ExprNum is replaced with a ExprNumUnary, or ExprNumBinary. Do the same
//for boolean and strings. With booleans, we might have fiddle with the comparisons to make sure they are recognized as boolean expressions.

public class ASTConstructionVisitor extends QLBaseVisitor<ASTNode> {

    @Override
    public ASTNode visitForm(QLParser.FormContext ctx) {
        String formId = ctx.IDENTIFIER().getText();
        List<Statement> statements = new ArrayList();
        ctx.block().statement().forEach(statementContext -> statements.add((Statement) visit(statementContext)));

        return new Form(formId, statements);
    }

    @Override
    public ASTNode visitQuestion(QLParser.QuestionContext ctx) {
        String id = ctx.declaration().IDENTIFIER().getText();
        String label = ctx.STRINGLITERAL().getText();
        Type type = (Type) visit(ctx.declaration().TYPE());

        return new Question(id, label, type);
    }

    @Override
    public ASTNode visitComputedQuestion(QLParser.ComputedQuestionContext ctx) {
        String id = ctx.declaration().IDENTIFIER().getText();
        String label = ctx.STRINGLITERAL().getText();
        Type type = (Type) visit(ctx.declaration().TYPE());
        Expression expression = (Expression) visit(ctx.expression());

        return new ComputedQuestion(id, label, type, expression);
    }

    @Override
    public ASTNode visitIfStatement(QLParser.IfStatementContext ctx) {
        Expression condition = (Expression) visit(ctx.expression());
        List<Statement> statements = new ArrayList<>();
        ctx.block().statement().forEach(statementContext -> statements.add((Statement) visit(statementContext)));

        if (ctx.elseBlock() != null) {
            List<Statement> elseStatements = new ArrayList<>();
            ctx.elseBlock().block().statement().forEach(statementContext -> elseStatements.add((Statement) visit(statementContext)));
            return new IfElseStatement(condition, statements, elseStatements);
        } else {
            return new IfStatement(condition, statements);
        }
    }

    @Override
    public ASTNode visitUnaryExpression(QLParser.UnaryExpressionContext ctx) {
        String operator = ctx.unaryOperation().UNARY().getText();
        switch (operator) {
            case "-":
                return new ArithmeticNegation((Expression) visit(ctx.unaryOperation().expression()));
            case "!":
                return new LogicalNegation((Expression) visit(ctx.unaryOperation().expression()));
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
                return new Addition(left, right);
            case "-":
                return new Subtraction(left, right);
            case "*":
                return new Multiplication(left, right);
            case "/":
                return new Division(left, right);
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
                return new LogicalAnd(left, right);
            case "||":
                return new LogicalOr(left, right);
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
                return new LessThan(left, right);
            case "<=":
                return new LessThanEqual(left, right);
            case ">":
                return new GreaterThan(left, right);
            case ">=":
                return new GreaterThanEqual(left, right);
            case "==":
                return new Equal(left, right);
            case "!=":
                return new NotEqual(left, right);
            default:
                throw new IllegalArgumentException(String.format("Invalid relational operator: %s", operator));
        }
    }

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
    public ASTNode visitDecimalLiteral(QLParser.DecimalLiteralContext ctx) {
        return new DecimalLiteral(ctx.getText());
    }

    @Override
    public ASTNode visitMoneyLiteral(QLParser.MoneyLiteralContext ctx) {
        return new MoneyLiteral(ctx.getText());
    }

    @Override
    public ASTNode visitDateLiteral(QLParser.DateLiteralContext ctx) {
        try {
            return new DateLiteral(ctx.getText());
        } catch  (ParseException e) {
            throw new IllegalArgumentException(String.format("Invalid date: %s", ctx.getText()));
        }
    }

    @Override
    public ASTNode visitIdentifier(QLParser.IdentifierContext ctx) {
        return new Variable(ctx.getText());
    }

}
