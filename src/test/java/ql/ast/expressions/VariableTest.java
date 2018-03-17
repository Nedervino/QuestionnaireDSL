package ql.ast.expressions;

import org.junit.Before;
import org.junit.Test;
import ql.QLParser;
import ql.parser.FormBuilder;

import static org.junit.Assert.assertEquals;

public class VariableTest {


    FormBuilder formBuilder;

    @Before
    public void setUp() throws Exception {
        formBuilder = new FormBuilder();
    }

    @Test
    public void canParseVariable() {
        final String EXPECTED_RESULT = "testVariable";
        QLParser parser = formBuilder.createParser(EXPECTED_RESULT);
        Variable variable = (Variable) formBuilder.getExpression(parser);

        assertEquals(EXPECTED_RESULT, variable.toString());
    }
}
