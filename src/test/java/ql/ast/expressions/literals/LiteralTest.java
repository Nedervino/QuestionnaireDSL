package ql.ast.expressions.literals;

import org.junit.Test;
import ql.parser.FormBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.assertEquals;

public class LiteralTest {

    @Test
    public void canParseBooleanLiteral() {
        BooleanLiteral booleanLiteral = (BooleanLiteral) FormBuilder.createExpression("true");
        assertEquals(true, booleanLiteral.getValue());
    }


    @Test
    public void canParseIntegerLiteral() {
        final int EXPECTED_RESULT = 123;
        IntegerLiteral integerLiteral = (IntegerLiteral) FormBuilder.createExpression(Integer.toString(EXPECTED_RESULT));

        assertEquals(EXPECTED_RESULT, integerLiteral.getValue());
    }

    @Test
    public void canParseDecimalLiteral() {
        final double DELTA = 1e-15;
        final double EXPECTED_RESULT = 123.45;
        DecimalLiteral decimalLiteral = (DecimalLiteral) FormBuilder.createExpression(Double.toString(EXPECTED_RESULT));

        assertEquals(EXPECTED_RESULT, decimalLiteral.getValue(), DELTA);
    }

    @Test
    public void canParseMoneyLiteral() {
        final BigDecimal EXPECTED_RESULT = new BigDecimal(123.45).setScale(2, RoundingMode.HALF_UP);
        MoneyLiteral moneyLiteral = (MoneyLiteral) FormBuilder.createExpression("123,45");
        BigDecimal displayValue = moneyLiteral.getDisplayValue();
        assertEquals(EXPECTED_RESULT, displayValue);
    }

    @Test
    public void canParseStringLiteral() {
        final String INPUT = "\"testString\"";
        final String EXPECTED_RESULT = INPUT.substring(1, INPUT.length() - 1);
        StringLiteral stringLiteral = (StringLiteral) FormBuilder.createExpression(INPUT);

        assertEquals(EXPECTED_RESULT, stringLiteral.getValue());
    }

}
