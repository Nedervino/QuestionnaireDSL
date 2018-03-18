package ql.ast.expressions.literals;

import org.junit.Before;
import org.junit.Test;
import ql.QLParser;
import ql.parser.FormBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.assertEquals;

public class LiteralTest {

    FormBuilder formBuilder;

    @Before
    public void setUp() throws Exception {
        formBuilder = new FormBuilder();
    }

    @Test
    public void canParseBooleanLiteral() {
        final Boolean EXPECTED_RESULT = true;
        QLParser parser = formBuilder.createParser("true");
        BooleanLiteral booleanLiteral = (BooleanLiteral) formBuilder.getExpression(parser);

        assertEquals(EXPECTED_RESULT, booleanLiteral.getValue());
    }


    @Test
    public void canParseIntegerLiteral() {
        final int EXPECTED_RESULT = 123;
        QLParser parser = formBuilder.createParser(Integer.toString(EXPECTED_RESULT));
        IntegerLiteral integerLiteral = (IntegerLiteral) formBuilder.getExpression(parser);

        assertEquals(EXPECTED_RESULT, integerLiteral.getValue());
    }

    @Test
    public void canParseDecimalLiteral() {
        final double DELTA = 1e-15;
        final double EXPECTED_RESULT = 123.45;
        QLParser parser = formBuilder.createParser(Double.toString(EXPECTED_RESULT));
        DecimalLiteral decimalLiteral = (DecimalLiteral) formBuilder.getExpression(parser);

        assertEquals(EXPECTED_RESULT, decimalLiteral.getValue(), DELTA);
    }

    @Test
    public void canParseMoneyLiteral() {
        final BigDecimal EXPECTED_RESULT = new BigDecimal(123.45).setScale(2, RoundingMode.HALF_UP);
        QLParser parser = formBuilder.createParser("123,45");
        MoneyLiteral moneyLiteral = (MoneyLiteral) formBuilder.getExpression(parser);
        BigDecimal displayValue = moneyLiteral.getDisplayValue();
        assertEquals(EXPECTED_RESULT, displayValue);
    }

    @Test
    public void canParseStringLiteral() {
        final String EXPECTED_RESULT = "\"testString\"";
        QLParser parser = formBuilder.createParser(EXPECTED_RESULT);
        StringLiteral stringLiteral = (StringLiteral) formBuilder.getExpression(parser);

        assertEquals(EXPECTED_RESULT, stringLiteral.getValue());
    }

}
