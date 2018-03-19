package ql.ast.types;

import org.junit.Before;
import org.junit.Test;
import ql.QLParser;
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
        QLParser parser = formBuilder.createParser("\"test\" testQuestion: boolean");
        Question question = (Question) formBuilder.getStatement(parser);

        assertEquals(EXPECTED_RESULT, question.getType().toString());
    }

    @Test
    public void canParseDecimalType() {
        final String EXPECTED_RESULT = "decimal";
        QLParser parser = formBuilder.createParser("\"test\" testQuestion: decimal");
        Question question = (Question) formBuilder.getStatement(parser);

        assertEquals(EXPECTED_RESULT, question.getType().toString());
    }

    @Test
    public void canParseIntegerType() {
        final String EXPECTED_RESULT = "integer";
        QLParser parser = formBuilder.createParser("\"test\" testQuestion: integer");
        Question question = (Question) formBuilder.getStatement(parser);

        assertEquals(EXPECTED_RESULT, question.getType().toString());
    }

    @Test
    public void canParseMoneyType() {
        final String EXPECTED_RESULT = "money";
        QLParser parser = formBuilder.createParser("\"test\" testQuestion: money");
        Question question = (Question) formBuilder.getStatement(parser);

        assertEquals(EXPECTED_RESULT, question.getType().toString());
    }

    @Test
    public void canParseStringType() {
        final String EXPECTED_RESULT = "string";
        QLParser parser = formBuilder.createParser("\"test\" testQuestion: string");
        Question question = (Question) formBuilder.getStatement(parser);

        assertEquals(EXPECTED_RESULT, question.getType().toString());
    }

}