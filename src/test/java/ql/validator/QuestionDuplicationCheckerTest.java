package ql.validator;

import issuetracker.IssueTracker;
import org.junit.Before;
import org.junit.Test;
import ql.BaseQlTest;
import ql.ast.Form;
import ql.validator.checkers.QuestionDuplicationChecker;

import static org.junit.Assert.*;

public class QuestionDuplicationCheckerTest extends BaseQlTest {

    private QuestionDuplicationChecker questionDuplicationChecker;
    private IssueTracker issueTracker;

    @Before
    public void setUp() throws Exception {
        issueTracker = IssueTracker.getIssueTracker();
        questionDuplicationChecker = new QuestionDuplicationChecker(issueTracker);
    }

    @Test
    public void shouldIssueWarningForDuplicateLabel() {
        issueTracker.reset();
        Form form = createForm("src/input/ql/incorrect/validator/duplicateQuestionLabels.ql");
        boolean passesTests = questionDuplicationChecker.passesTests(form);
        assertTrue(passesTests);
        assertEquals(1, issueTracker.getWarnings().size());
        assertEquals(0, issueTracker.getErrors().size());
        assertEquals("Duplicate question label \"Question 1?\" used on multiple locations", issueTracker.getWarnings().get(0).getMessage());
    }

    @Test
    public void shouldIssueErrorForDuplicateIDWithDifferentType() {
        issueTracker.reset();
        Form form = createForm("src/input/ql/incorrect/validator/duplicateQuestionIDsDifferentTypes.ql");
        boolean passesTests = questionDuplicationChecker.passesTests(form);
        assertFalse(passesTests);
        assertEquals(0, issueTracker.getWarnings().size());
        assertEquals(1, issueTracker.getErrors().size());
        assertEquals("Question with identifier \"q1\" declared on multiple locations", issueTracker.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldIssueNothingForDuplicateIDWithSameType() {
        issueTracker.reset();
        Form form = createForm("src/input/ql/incorrect/validator/duplicateQuestionIDsSameTypes.ql");
        boolean passesTests = questionDuplicationChecker.passesTests(form);
        assertTrue(passesTests);
        assertEquals(0, issueTracker.getWarnings().size());
        assertEquals(0, issueTracker.getErrors().size());
    }

    @Test
    public void shouldIssueNothingForRegularForm() {
        issueTracker.reset();
        Form form = createForm("src/input/ql/correct/simple.ql");
        boolean passesTests = questionDuplicationChecker.passesTests(form);
        assertTrue(passesTests);
        assertEquals(0, issueTracker.getWarnings().size());
        assertEquals(0, issueTracker.getErrors().size());
    }

}