package ql.ast.expressions;

import org.junit.Before;
import org.junit.Test;
import ql.QLParser;
import ql.parser.ASTBuilder;

import static org.junit.Assert.assertEquals;

public class VariableTest {


    ASTBuilder astBuilder;

    @Before
    public void setUp() throws Exception {
        astBuilder = new ASTBuilder();
    }

    @Test
    public void canParseVariable() {
        final String EXPECTED_RESULT = "testVariable";
        QLParser parser = astBuilder.createParser(EXPECTED_RESULT);
        Variable variable = (Variable) astBuilder.getExpression(parser);

        assertEquals(EXPECTED_RESULT, variable.toString());
    }
}
