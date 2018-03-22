package ql.validator;

import issuetracker.IssueTracker;
import org.junit.Before;
import org.junit.Test;
import ql.BaseQlTest;
import ql.ast.Form;
import ql.parser.FormBuilder;
import ql.validator.checkers.CyclicDependencyChecker;

import static org.junit.Assert.*;

public class CyclicDependencyCheckerTest extends BaseQlTest {

    private FormBuilder formBuilder;
    private CyclicDependencyChecker cyclicDependencyChecker;
    private IssueTracker issueTracker;

    @Before
    public void setUp() throws Exception {
        formBuilder = new FormBuilder();
        issueTracker = IssueTracker.getIssueTracker();
        cyclicDependencyChecker = new CyclicDependencyChecker(issueTracker);
    }

    @Test
    public void shouldIssueErrorForCycleWithinQuestion() {
        issueTracker.reset();
        Form form = createForm("src/input/ql/incorrect/validator/cyclicalWithinQuestion.ql");
        boolean passesTests = cyclicDependencyChecker.passesTests(form);
        assertFalse(passesTests);
        assertEquals(issueTracker.getWarnings().size(), 0);
        assertEquals(issueTracker.getErrors().size(), 1);
        assertEquals(issueTracker.getErrors().get(0).getMessage(), "Variable first involved in circular dependency");
    }

    @Test
    public void shouldIssueErrorForCycleBetweenQuestions() {
        issueTracker.reset();
        Form form = createForm("src/input/ql/incorrect/validator/cyclicalBetweenQuestions.ql");
        boolean passesTests = cyclicDependencyChecker.passesTests(form);
        assertFalse(passesTests);
        assertEquals(issueTracker.getWarnings().size(), 0);
        assertEquals(issueTracker.getErrors().size(), 2);
        assertEquals(issueTracker.getErrors().get(0).getMessage(), "Variable second involved in circular dependency");
        assertEquals(issueTracker.getErrors().get(1).getMessage(), "Variable first involved in circular dependency");
    }

    @Test
    public void shouldIssueNothingForRegularForm() {
        issueTracker.reset();
        Form form = createForm("src/input/ql/correct/simple.ql");
        boolean passesTests = cyclicDependencyChecker.passesTests(form);
        assertTrue(passesTests);
    }

}