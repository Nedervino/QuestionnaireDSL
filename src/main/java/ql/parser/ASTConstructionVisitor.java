package ql.parser;

import org.antlr.v4.runtime.ParserRuleContext;
import ql.QLBaseVisitor;
import ql.QLParser;
import ql.ast.ASTNode;
import ql.ast.Form;
import ql.ast.SourceLocation;
import ql.ast.expressions.Expression;
import ql.ast.expressions.Variable;
import ql.ast.expressions.binary.*;
import ql.ast.expressions.literals.*;
import ql.ast.expressions.unary.ArithmeticNegation;
import ql.ast.expressions.unary.LogicalNegation;
import ql.ast.statements.*;
import ql.ast.types.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


/**
 * Constructs a Form AST from ANTLR CST
 */
public class ASTConstructionVisitor extends QLBaseVisitor<ASTNode> {

    @Override
    public ASTNode visitForm(QLParser.FormContext ctx) {
        String formId = ctx.IDENTIFIER().getText();
        List<Statement> statements = new ArrayList();
        ctx.block().statement().forEach(statementContext -> statements.add((Statement) visit(statementContext)));

        return new Form(formId, statements, getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitQuestion(QLParser.QuestionContext ctx) {
        String id = ctx.declaration().IDENTIFIER().getText();

        //Strip quotation marks
        String label = ctx.STRINGLITERAL().getText().substring(1, ctx.STRINGLITERAL().getText().length() - 1);
        Type type = (Type) visit(ctx.declaration().type());

        return new Question(id, label, type, getSourceLocation(ctx));
    }


    @Override
    public ASTNode visitComputedQuestion(QLParser.ComputedQuestionContext ctx) {
        String id = ctx.declaration().IDENTIFIER().getText();

        //Strip quotation marks
        String label = ctx.STRINGLITERAL().getText().substring(1, ctx.STRINGLITERAL().getText().length() - 1);
        Type type = (Type) visit(ctx.declaration().type());
        Expression expression = (Expression) visit(ctx.expression());

        return new ComputedQuestion(id, label, type, expression, getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitIfStatement(QLParser.IfStatementContext ctx) {
        Expression condition = (Expression) visit(ctx.expression());
        List<Statement> statements = new ArrayList<>();
        ctx.block().statement().forEach(statementContext -> statements.add((Statement) visit(statementContext)));

        if (ctx.elseBlock() != null) {
            List<Statement> elseStatements = new ArrayList<>();
            ctx.elseBlock().block().statement().forEach(statementContext -> elseStatements.add((Statement) visit(statementContext)));
            return new IfElseStatement(condition, statements, elseStatements, getSourceLocation(ctx));
        } else {
            return new IfStatement(condition, statements, getSourceLocation(ctx));
        }
    }

    @Override
    public ASTNode visitNestedExpression(QLParser.NestedExpressionContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public ASTNode visitUnaryExpression(QLParser.UnaryExpressionContext ctx) {
        String operator = ctx.unaryOperation().UNARY().getText();
        switch (operator) {
            case "-":
                return new ArithmeticNegation((Expression) visit(ctx.unaryOperation().expression()), getSourceLocation(ctx));
            case "!":
                return new LogicalNegation((Expression) visit(ctx.unaryOperation().expression()), getSourceLocation(ctx));
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
                return new Addition(left, right, getSourceLocation(ctx));
            case "-":
                return new Subtraction(left, right, getSourceLocation(ctx));
            case "*":
                return new Multiplication(left, right, getSourceLocation(ctx));
            case "/":
                return new Division(left, right, getSourceLocation(ctx));
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
                return new LogicalAnd(left, right, getSourceLocation(ctx));
            case "||":
                return new LogicalOr(left, right, getSourceLocation(ctx));
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
                return new LessThan(left, right, getSourceLocation(ctx));
            case "<=":
                return new LessThanEqual(left, right, getSourceLocation(ctx));
            case ">":
                return new GreaterThan(left, right, getSourceLocation(ctx));
            case ">=":
                return new GreaterThanEqual(left, right, getSourceLocation(ctx));
            case "==":
                return new Equal(left, right, getSourceLocation(ctx));
            case "!=":
                return new NotEqual(left, right, getSourceLocation(ctx));
            default:
                throw new IllegalArgumentException(String.format("Invalid relational operator: %s", operator));
        }
    }

    @Override
    public ASTNode visitBooleanLiteral(QLParser.BooleanLiteralContext ctx) {
        return new BooleanLiteral(ctx.BOOLEANLITERAL().getText(), getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitStringLiteral(QLParser.StringLiteralContext ctx) {
        return new StringLiteral(ctx.STRINGLITERAL().getText(), getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitIntegerLiteral(QLParser.IntegerLiteralContext ctx) {
        return new IntegerLiteral(ctx.INTEGERLITERAL().getText(), getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitDecimalLiteral(QLParser.DecimalLiteralContext ctx) {
        return new DecimalLiteral(ctx.DECIMALLITERAL().getText(), getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitMoneyLiteral(QLParser.MoneyLiteralContext ctx) {
        return new MoneyLiteral(ctx.MONEYLITERAL().getText(), getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitDateLiteral(QLParser.DateLiteralContext ctx) {
        try {
            return new DateLiteral(ctx.getText(), getSourceLocation(ctx));
        } catch (ParseException e) {
            throw new IllegalArgumentException(String.format("Invalid date: %s", ctx.getText()));
        }
    }

    @Override
    public ASTNode visitBooleanType(QLParser.BooleanTypeContext ctx) {
        return new BooleanType(getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitIntegerType(QLParser.IntegerTypeContext ctx) {
        return new IntegerType(getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitStringType(QLParser.StringTypeContext ctx) {
        return new StringType(getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitDecimalType(QLParser.DecimalTypeContext ctx) {
        return new DecimalType(getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitDateType(QLParser.DateTypeContext ctx) {
        return new DateType(getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitMoneyType(QLParser.MoneyTypeContext ctx) {
        return new MoneyType(getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitVariable(QLParser.VariableContext ctx) {
        return new Variable(ctx.IDENTIFIER().getText(), getSourceLocation(ctx));
    }

    public SourceLocation getSourceLocation(ParserRuleContext ctx) {
        return new SourceLocation(ctx.start.getLine(), ctx.start.getCharPositionInLine());
    }
}
