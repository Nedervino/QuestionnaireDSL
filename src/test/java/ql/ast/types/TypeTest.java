package ql.ast.types;

import org.junit.Before;
import org.junit.Test;
import ql.ast.statements.Question;
import ql.parser.FormBuilder;

import static org.junit.Assert.assertTrue;

public class TypeTest {

    @Test
    public void canParseBooleanType() {
        Question question = (Question) FormBuilder.createStatement("\"test\" testQuestion: boolean");
        assertTrue(question.isOfType("boolean"));
    }

    @Test
    public void canParseDecimalType() {
        Question question = (Question) FormBuilder.createStatement("\"test\" testQuestion: decimal");
        assertTrue(question.isOfType("decimal"));
    }

    @Test
    public void canParseIntegerType() {
        Question question = (Question) FormBuilder.createStatement("\"test\" testQuestion: integer");
        assertTrue(question.isOfType("integer"));
    }

    @Test
    public void canParseMoneyType() {
        Question question = (Question) FormBuilder.createStatement("\"test\" testQuestion: money");
        assertTrue(question.isOfType("money"));
    }

    @Test
    public void canParseStringType() {
        Question question = (Question) FormBuilder.createStatement("\"test\" testQuestion: string");
        assertTrue(question.isOfType("string"));
    }

}