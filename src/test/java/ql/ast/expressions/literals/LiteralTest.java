package ql.ast.expressions.literals;

import org.junit.Before;
import org.junit.Test;
import ql.QLParser;
import ql.parser.ASTBuilder;

import static org.junit.Assert.assertEquals;

public class LiteralTest {

    ASTBuilder astBuilder;

    @Before
    public void setUp() throws Exception {
        astBuilder = new ASTBuilder();
    }

    @Test
    public void canParseBooleanLiteral() {
        final Boolean EXPECTED_RESULT = true;
        QLParser parser = astBuilder.createParser("true");
        BooleanLiteral booleanLiteral = (BooleanLiteral) astBuilder.getExpression(parser);

        assertEquals(EXPECTED_RESULT, booleanLiteral.getValue());
    }


    @Test
    public void canParseIntegerLiteral() {
        final int EXPECTED_RESULT = 123;
        QLParser parser = astBuilder.createParser(Integer.toString(EXPECTED_RESULT));
        IntegerLiteral integerLiteral = (IntegerLiteral) astBuilder.getExpression(parser);

        assertEquals(EXPECTED_RESULT, integerLiteral.getValue());
    }

    @Test
    public void canParseDecimalLiteral() {
        final double DELTA = 1e-15;
        final double EXPECTED_RESULT = 123.45;
        QLParser parser = astBuilder.createParser(Double.toString(EXPECTED_RESULT));
        DecimalLiteral decimalLiteral = (DecimalLiteral) astBuilder.getExpression(parser);

        assertEquals(EXPECTED_RESULT, decimalLiteral.getValue(), DELTA);
    }

    @Test
    public void canParseMoneyLiteral() {
        final double DELTA = 1e-15;
        final double EXPECTED_RESULT = 123.45;
        QLParser parser = astBuilder.createParser("123,45");
        MoneyLiteral moneyLiteral = (MoneyLiteral) astBuilder.getExpression(parser);

        assertEquals(EXPECTED_RESULT, moneyLiteral.getValue(), DELTA);
    }

    @Test
    public void canParseStringLiteral() {
        final String EXPECTED_RESULT = "\"testString\"";
        QLParser parser = astBuilder.createParser(EXPECTED_RESULT);
        StringLiteral stringLiteral = (StringLiteral) astBuilder.getExpression(parser);

        assertEquals(EXPECTED_RESULT, stringLiteral.getValue());
    }

}
