package ql.validator;

import org.junit.Test;
import ql.BaseQlTest;
import ql.ast.Form;
import ql.validator.checkers.CyclicDependencyChecker;

import static org.junit.Assert.*;

public class CyclicDependencyCheckerTest extends BaseQlTest {

    @Test
    public void shouldIssueErrorForCycleWithinQuestion() {
        Form form = createForm("src/input/ql/incorrect/validator/cyclicalWithinQuestion.ql");
        CyclicDependencyChecker cyclicDependencyChecker = new CyclicDependencyChecker(form);

        boolean passesTests = cyclicDependencyChecker.passesTests();
        assertFalse(passesTests);
        assertEquals(0, cyclicDependencyChecker.getWarnings().size());
        assertEquals(1, cyclicDependencyChecker.getErrors().size());
        assertEquals("Variable first involved in circular dependency", cyclicDependencyChecker.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldIssueErrorForCycleBetweenQuestions() {
        Form form = createForm("src/input/ql/incorrect/validator/cyclicalBetweenQuestions.ql");
        CyclicDependencyChecker cyclicDependencyChecker = new CyclicDependencyChecker(form);

        boolean passesTests = cyclicDependencyChecker.passesTests();
        assertFalse(passesTests);
        assertEquals(0, cyclicDependencyChecker.getWarnings().size());
        assertEquals(3, cyclicDependencyChecker.getErrors().size());
        assertEquals("Variable third involved in circular dependency", cyclicDependencyChecker.getErrors().get(0).getMessage());
        assertEquals("Variable second involved in circular dependency", cyclicDependencyChecker.getErrors().get(1).getMessage());
        assertEquals("Variable first involved in circular dependency", cyclicDependencyChecker.getErrors().get(2).getMessage());
    }

    @Test
    public void shouldIssueNothingForRegularForm() {
        Form form = createForm("src/input/ql/correct/simple.ql");
        CyclicDependencyChecker cyclicDependencyChecker = new CyclicDependencyChecker(form);

        boolean passesTests = cyclicDependencyChecker.passesTests();
        assertTrue(passesTests);
    }

}