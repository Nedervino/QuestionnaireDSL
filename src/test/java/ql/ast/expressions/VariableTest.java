package ql.ast.expressions;

import org.junit.Before;
import org.junit.Test;
import ql.parser.FormBuilder;

import static org.junit.Assert.assertEquals;

public class VariableTest {

    @Test
    public void canParseVariable() {
        final String EXPECTED_RESULT = "testVariable";
        Variable variable = (Variable) FormBuilder.createExpression(EXPECTED_RESULT);

        assertEquals(EXPECTED_RESULT, variable.getName());
    }
}
