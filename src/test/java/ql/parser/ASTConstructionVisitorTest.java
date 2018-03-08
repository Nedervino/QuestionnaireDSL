package ql.parser;

import org.junit.Before;
import org.junit.Test;
import ql.QLParser;
import ql.ast.Form;
import ql.ast.expressions.Variable;
import ql.ast.expressions.literals.IntegerLiteral;
import ql.ast.statements.IfStatement;
import ql.ast.statements.Question;

import static org.junit.Assert.assertEquals;

public class ASTConstructionVisitorTest {

    ASTBuilder astBuilder;

    @Before
    public void setUp() throws Exception {
        astBuilder = new ASTBuilder();
    }

    @Test
    public void visitNestedExpression() {
        final int EXPECTED_RESULT = 4;
        QLParser parser = astBuilder.createParser("((((4))))");
        IntegerLiteral integerLiteral = (IntegerLiteral) astBuilder.getExpression(parser);

        assertEquals(EXPECTED_RESULT, integerLiteral.getValue());
    }

    @Test
    public void visitForm() {
        Form form = astBuilder.buildASTFromFile("src/input/ql/correct/simple.ql");
        assertEquals("taxOfficeExample", form.getFormId());
        assertEquals(3, form.getStatements().size());
    }

    @Test
    public void visitQuestion() {
        // astBuilder.createParser("form testForm {}");
        Form form = astBuilder.buildASTFromFile("src/input/ql/correct/simple.ql");
        Question question = (Question) form.getStatements().get(0);

        assertEquals("boolean", question.getType().toString());
        assertEquals("hasSoldHouse", question.getId());
        assertEquals("Did you sell a house in 2010?", question.getLabel());
    }

    @Test
    public void visitComputedQuestion() {
        Form form = astBuilder.buildASTFromFile("src/input/ql/correct/simple.ql");
        Question question = (Question) form.getStatements().get(0);

        assertEquals("boolean", question.getType().toString());
        assertEquals("hasSoldHouse", question.getId());
        assertEquals("Did you sell a house in 2010?", question.getLabel());
    }

    @Test
    public void visitIfStatement() {
        QLParser parser = astBuilder.createParser("if (hasSoldHouse) {\n" +
                "    \"What was the selling price?\"\n" +
                "      sellingPrice: money\n" +
                "    \"Private debts for the sold house:\"\n" +
                "      privateDebt: money\n" +
                "    \"Value residue:\"\n" +
                "      valueResidue: money =\n" +
                "        (sellingPrice - privateDebt)\n" +
                "  }");
        IfStatement ifStatement = (IfStatement) astBuilder.getStatement(parser);
        Variable variable = (Variable) ifStatement.getCondition();

        assertEquals(3, ifStatement.getIfStatements().size());
        assertEquals("hasSoldHouse", variable.toString());
    }

    // @Test
    // public void visitUnaryExpression() {
    //     QLParser parser = astBuilder.createParser("!(true)");
    //     LogicalNegation logicalNegation = (LogicalNegation) astBuilder.getExpression(parser);
    //     ArithmeticNegation arithmeticNegation = (ArithmeticNegation) astBuilder.getExpression(parser);
    // }

    // @Test
    // public void visitArithMeticBinary() {
    // }
    //
    // @Test
    // public void visitLogicalBinary() {
    // }
    //
    // @Test
    // public void visitRelationalBinary() {
    // }
    //
    // @Test
    // public void visitBooleanLiteral() {
    // }
    //
    // @Test
    // public void visitStringLiteral() {
    // }
    //
    // @Test
    // public void visitIntegerLiteral() {
    // }
    //
    // @Test
    // public void visitDecimalLiteral() {
    // }
    //
    // @Test
    // public void visitMoneyLiteral() {
    // }
    //
    // @Test
    // public void visitDateLiteral() {
    // }
    //
    // @Test
    // public void visitIdentifier() {
    // }

}