package ql.validator;

import org.junit.Before;
import org.junit.Test;
import ql.BaseQlTest;
import ql.ast.Form;
import ql.validator.checkers.CyclicDependencyChecker;

import static org.junit.Assert.*;

public class CyclicDependencyCheckerTest extends BaseQlTest {

    private CyclicDependencyChecker cyclicDependencyChecker;

    @Before
    public void setUp() throws Exception {
        cyclicDependencyChecker = new CyclicDependencyChecker();
    }

    @Test
    public void shouldIssueErrorForCycleWithinQuestion() {
        Form form = createForm("src/input/ql/incorrect/validator/cyclicalWithinQuestion.ql");
        boolean passesTests = cyclicDependencyChecker.passesTests(form);
        assertFalse(passesTests);
        assertEquals(cyclicDependencyChecker.getWarnings().size(), 0);
        assertEquals(cyclicDependencyChecker.getErrors().size(), 1);
        assertEquals(cyclicDependencyChecker.getErrors().get(0).getMessage(), "Variable first involved in circular dependency");
    }

    @Test
    public void shouldIssueErrorForCycleBetweenQuestions() {
        Form form = createForm("src/input/ql/incorrect/validator/cyclicalBetweenQuestions.ql");
        boolean passesTests = cyclicDependencyChecker.passesTests(form);
        assertFalse(passesTests);
        assertEquals(cyclicDependencyChecker.getWarnings().size(), 0);
        assertEquals(cyclicDependencyChecker.getErrors().size(), 2);
        assertEquals(cyclicDependencyChecker.getErrors().get(0).getMessage(), "Variable second involved in circular dependency");
        assertEquals(cyclicDependencyChecker.getErrors().get(1).getMessage(), "Variable first involved in circular dependency");
    }

    @Test
    public void shouldIssueNothingForRegularForm() {
        Form form = createForm("src/input/ql/correct/simple.ql");
        boolean passesTests = cyclicDependencyChecker.passesTests(form);
        assertTrue(passesTests);
    }

}