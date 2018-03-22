package ql.ast.types;

import org.junit.Before;
import org.junit.Test;
import ql.ast.statements.Question;
import ql.parser.FormBuilder;

import static org.junit.Assert.assertEquals;

public class TypeTest {

    private FormBuilder formBuilder;

    @Before
    public void setUp() throws Exception {
        formBuilder = new FormBuilder();
    }

    @Test
    public void canParseBooleanType() {
        final String EXPECTED_RESULT = "boolean";
        Question question = (Question) formBuilder.createStatement("\"test\" testQuestion: boolean");

        assertEquals(EXPECTED_RESULT, question.getType().toString());
    }

    @Test
    public void canParseDecimalType() {
        final String EXPECTED_RESULT = "decimal";
        Question question = (Question) formBuilder.createStatement("\"test\" testQuestion: decimal");

        assertEquals(EXPECTED_RESULT, question.getType().toString());
    }

    @Test
    public void canParseIntegerType() {
        final String EXPECTED_RESULT = "integer";
        Question question = (Question) formBuilder.createStatement("\"test\" testQuestion: integer");

        assertEquals(EXPECTED_RESULT, question.getType().toString());
    }

    @Test
    public void canParseMoneyType() {
        final String EXPECTED_RESULT = "money";
        Question question = (Question) formBuilder.createStatement("\"test\" testQuestion: money");

        assertEquals(EXPECTED_RESULT, question.getType().toString());
    }

    @Test
    public void canParseStringType() {
        final String EXPECTED_RESULT = "string";
        Question question = (Question) formBuilder.createStatement("\"test\" testQuestion: string");

        assertEquals(EXPECTED_RESULT, question.getType().toString());
    }

}