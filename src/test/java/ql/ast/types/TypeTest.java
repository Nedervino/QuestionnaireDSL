package ql.ast.types;

import org.junit.Before;
import org.junit.Test;
import ql.ast.statements.Question;
import ql.parser.FormBuilder;

import static org.junit.Assert.assertTrue;

public class TypeTest {

    private FormBuilder formBuilder;

    @Before
    public void setUp() throws Exception {
        formBuilder = new FormBuilder();
    }

    @Test
    public void canParseBooleanType() {
        Question question = (Question) formBuilder.createStatement("\"test\" testQuestion: boolean");
        assertTrue(question.getType().isOfType("boolean"));
    }

    @Test
    public void canParseDecimalType() {
        Question question = (Question) formBuilder.createStatement("\"test\" testQuestion: decimal");
        assertTrue(question.getType().isOfType("decimal"));
    }

    @Test
    public void canParseIntegerType() {
        Question question = (Question) formBuilder.createStatement("\"test\" testQuestion: integer");
        assertTrue(question.getType().isOfType("integer"));
    }

    @Test
    public void canParseMoneyType() {
        Question question = (Question) formBuilder.createStatement("\"test\" testQuestion: money");
        assertTrue(question.getType().isOfType("money"));
    }

    @Test
    public void canParseStringType() {
        Question question = (Question) formBuilder.createStatement("\"test\" testQuestion: string");
        assertTrue(question.getType().isOfType("string"));
    }

}