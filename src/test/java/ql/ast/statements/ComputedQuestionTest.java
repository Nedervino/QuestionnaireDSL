package ql.ast.statements;

import org.junit.Before;
import org.junit.Test;
import ql.ast.expressions.literals.*;
import ql.parser.FormBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ComputedQuestionTest {

    @Test
    public void canParseComputedBoolean() {
        ComputedQuestion question = (ComputedQuestion) FormBuilder.createStatement("\"test\" testQuestion: boolean = true");
        BooleanLiteral booleanLiteral = (BooleanLiteral) question.getExpression();
        assertTrue(booleanLiteral.getValue());
    }

    @Test
    public void canParseComputedDecimal() {
        final double DELTA = 1e-15;
        ComputedQuestion question = (ComputedQuestion) FormBuilder.createStatement("\"test\" testQuestion: decimal = 23423.03");
        DecimalLiteral decimalLiteral = (DecimalLiteral) question.getExpression();
        assertEquals(23423.03, decimalLiteral.getValue(), DELTA);
    }

    @Test
    public void canParseComputedInteger() {
        ComputedQuestion question = (ComputedQuestion) FormBuilder.createStatement("\"test\" testQuestion: integer = 23489");
        IntegerLiteral integerLiteral = (IntegerLiteral) question.getExpression();
        assertEquals(23489, integerLiteral.getValue());
    }

    @Test
    public void canParseComputedMoney() {
        final BigDecimal EXPECTED_RESULT = new BigDecimal(123.45).setScale(2, RoundingMode.HALF_UP);
        ComputedQuestion question = (ComputedQuestion) FormBuilder.createStatement("\"test\" testQuestion: money = 123,45");
        MoneyLiteral moneyLiteral = (MoneyLiteral) question.getExpression();
        BigDecimal displayValue = moneyLiteral.getDisplayValue();
        assertEquals(EXPECTED_RESULT, displayValue);
    }

    @Test
    public void canParseComputedString() {
        ComputedQuestion question = (ComputedQuestion) FormBuilder.createStatement("\"test\" testQuestion: string = \"test\"");
        StringLiteral stringLiteral = (StringLiteral) question.getExpression();
        assertEquals("test", stringLiteral.getValue());
    }

}