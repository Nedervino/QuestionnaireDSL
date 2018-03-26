package ql.validator;

import issuetracker.IssueTracker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ql.BaseQlTest;
import ql.ast.Form;
import ql.validator.checkers.CyclicDependencyChecker;

import static org.junit.Assert.*;

public class CyclicDependencyCheckerTest extends BaseQlTest {

    private CyclicDependencyChecker cyclicDependencyChecker;
    private IssueTracker issueTracker;

    @Before
    public void setUp() throws Exception {
        issueTracker = IssueTracker.getIssueTracker();
        issueTracker.reset();
        cyclicDependencyChecker = new CyclicDependencyChecker(issueTracker);
    }

    @After
    public void tearDown() throws Exception {
        issueTracker.reset();
    }

    @Test
    public void shouldIssueErrorForCycleWithinQuestion() {
        Form form = createForm("src/input/ql/incorrect/validator/cyclicalWithinQuestion.ql");
        boolean passesTests = cyclicDependencyChecker.passesTests(form);
        assertFalse(passesTests);
        assertEquals(issueTracker.getWarnings().size(), 0);
        assertEquals(issueTracker.getErrors().size(), 1);
        assertEquals(issueTracker.getErrors().get(0).getMessage(), "Variable first involved in circular dependency");
    }

    @Test
    public void shouldIssueErrorForCycleBetweenQuestions() {
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
        Form form = createForm("src/input/ql/correct/simple.ql");
        boolean passesTests = cyclicDependencyChecker.passesTests(form);
        assertTrue(passesTests);
    }

}