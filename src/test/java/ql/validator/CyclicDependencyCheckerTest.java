package ql.validator;

import org.junit.Before;
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
        assertEquals(cyclicDependencyChecker.getWarnings().size(), 0);
        assertEquals(cyclicDependencyChecker.getErrors().size(), 1);
        assertEquals(cyclicDependencyChecker.getErrors().get(0).getMessage(), "Variable first involved in circular dependency");
    }

    @Test
    public void shouldIssueErrorForCycleBetweenQuestions() {
        Form form = createForm("src/input/ql/incorrect/validator/cyclicalBetweenQuestions.ql");
        CyclicDependencyChecker cyclicDependencyChecker = new CyclicDependencyChecker(form);

        boolean passesTests = cyclicDependencyChecker.passesTests();
        assertFalse(passesTests);
        assertEquals(cyclicDependencyChecker.getWarnings().size(), 0);
        assertEquals(cyclicDependencyChecker.getErrors().size(), 3);
        assertEquals(cyclicDependencyChecker.getErrors().get(0).getMessage(), "Variable third involved in circular dependency");
        assertEquals(cyclicDependencyChecker.getErrors().get(1).getMessage(), "Variable second involved in circular dependency");
        assertEquals(cyclicDependencyChecker.getErrors().get(2).getMessage(), "Variable first involved in circular dependency");
    }

    @Test
    public void shouldIssueNothingForRegularForm() {
        Form form = createForm("src/input/ql/correct/simple.ql");
        CyclicDependencyChecker cyclicDependencyChecker = new CyclicDependencyChecker(form);

        boolean passesTests = cyclicDependencyChecker.passesTests();
        assertTrue(passesTests);
    }

}