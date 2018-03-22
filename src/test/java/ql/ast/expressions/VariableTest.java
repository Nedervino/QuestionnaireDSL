package ql.ast.expressions;

import org.junit.Before;
import org.junit.Test;
import ql.parser.FormBuilder;

import static org.junit.Assert.assertEquals;

public class VariableTest {

    private FormBuilder formBuilder;

    @Before
    public void setUp() throws Exception {
        formBuilder = new FormBuilder();
    }

    @Test
    public void canParseVariable() {
        final String EXPECTED_RESULT = "testVariable";
        Variable variable = (Variable) formBuilder.createExpression(EXPECTED_RESULT);

        assertEquals(EXPECTED_RESULT, variable.toString());
    }
}
