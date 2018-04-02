package ql.gui;

import org.junit.Test;
import ql.BaseQlTest;
import ql.ast.Form;
import ql.environment.Environment;
import ql.environment.FormEnvironment;

import static org.junit.Assert.assertTrue;

public class QuestionVisibilityTest extends BaseQlTest {

    private Environment environment;

    @Test
    public void shouldBeVisible() {
        Form form = createForm("src/input/ql/correct/gui/nestedIf.ql");
        environment = new FormEnvironment(form);
        environment.evaluate();

        assertTrue(environment.questionIsVisible("result"));
    }

}