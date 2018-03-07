package ql.ast.types;

import org.junit.Before;
import org.junit.Test;
import ql.QLParser;
import ql.ast.statements.Question;
import ql.parser.ASTBuilder;

import static org.junit.Assert.assertEquals;

public class TypeTest {

    ASTBuilder astBuilder;

    @Before
    public void setUp() throws Exception {
        astBuilder = new ASTBuilder();
    }

    @Test
    public void canParseBooleanType() {
        final String EXPECTED_RESULT = "boolean";
        QLParser parser = astBuilder.createParser("\"test\" testQuestion: boolean");
        Question question = (Question) astBuilder.getStatement(parser);

        assertEquals(question.getType().toString(), EXPECTED_RESULT);
    }

    @Test
    public void canParseDecimalType() {
        final String EXPECTED_RESULT = "decimal";
        QLParser parser = astBuilder.createParser("\"test\" testQuestion: decimal");
        Question question = (Question) astBuilder.getStatement(parser);

        assertEquals(question.getType().toString(), EXPECTED_RESULT);
    }

    @Test
    public void canParseIntegerType() {
        final String EXPECTED_RESULT = "integer";
        QLParser parser = astBuilder.createParser("\"test\" testQuestion: integer");
        Question question = (Question) astBuilder.getStatement(parser);

        assertEquals(question.getType().toString(), EXPECTED_RESULT);
    }

    @Test
    public void canParseMoneyType() {
        final String EXPECTED_RESULT = "money";
        QLParser parser = astBuilder.createParser("\"test\" testQuestion: money");
        Question question = (Question) astBuilder.getStatement(parser);

        assertEquals(question.getType().toString(), EXPECTED_RESULT);
    }

    @Test
    public void canParseStringType() {
        final String EXPECTED_RESULT = "string";
        QLParser parser = astBuilder.createParser("\"test\" testQuestion: string");
        Question question = (Question) astBuilder.getStatement(parser);

        assertEquals(question.getType().toString(), EXPECTED_RESULT);
    }

}