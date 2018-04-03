package ql.parser;

import org.junit.Test;
import ql.BaseQlTest;
import ql.ast.Form;
import ql.ast.expressions.Variable;
import ql.ast.expressions.literals.IntegerLiteral;
import ql.ast.statements.IfStatement;
import ql.ast.statements.Question;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ASTConstructionVisitorTest extends BaseQlTest {

    @Test
    public void visitNestedExpression() {
        final int EXPECTED_RESULT = 4;
        IntegerLiteral integerLiteral = (IntegerLiteral) FormBuilder.createExpression("((((4))))");

        assertEquals(EXPECTED_RESULT, integerLiteral.getValue());
    }

    @Test
    public void visitForm() {
        Form form = createForm("src/input/ql/correct/simple.ql");
        assertEquals("taxOfficeExample", form.getFormId());
        assertEquals(3, form.getStatements().size());
    }

    @Test
    public void visitQuestion() {
        Form form = createForm("src/input/ql/correct/simple.ql");
        Question question = (Question) form.getStatements().get(0);

        assertTrue(question.isOfType("boolean"));
        assertEquals("hasSoldHouse", question.getId());
        assertEquals("Did you sell a house in 2010?", question.getLabel());
    }

    @Test
    public void visitComputedQuestion() {
        Form form = createForm("src/input/ql/correct/simple.ql");
        Question question = (Question) form.getStatements().get(0);

        assertTrue(question.isOfType("boolean"));
        assertEquals("hasSoldHouse", question.getId());
        assertEquals("Did you sell a house in 2010?", question.getLabel());
    }

    @Test
    public void visitIfStatement() {
        IfStatement ifStatement = (IfStatement) FormBuilder.createStatement("if (hasSoldHouse) {\n" +
                "    \"What was the selling price?\"\n" +
                "      sellingPrice: money\n" +
                "    \"Private debts for the sold house:\"\n" +
                "      privateDebt: money\n" +
                "    \"Value residue:\"\n" +
                "      valueResidue: money =\n" +
                "        (sellingPrice - privateDebt)\n" +
                "  }");
        Variable variable = (Variable) ifStatement.getCondition();

        assertEquals(3, ifStatement.getIfStatements().size());
        assertEquals("hasSoldHouse", variable.getName());
    }

    // @Test
    // public void visitUnaryExpression() {
    //     Negation negation = (Negation) FormBuilder.createExpression("!(true)");
    //     Negative arithmeticNegation = (Negative) FormBuilder.createExpression(parser);
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