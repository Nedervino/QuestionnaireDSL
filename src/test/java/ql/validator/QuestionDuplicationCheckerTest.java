package ql.validator;

import org.junit.Before;
import org.junit.Test;
import ql.BaseQlTest;
import ql.ast.Form;
import ql.validator.checkers.QuestionDuplicationChecker;

import static org.junit.Assert.*;

public class QuestionDuplicationCheckerTest extends BaseQlTest {

    private QuestionDuplicationChecker questionDuplicationChecker;

    @Before
    public void setUp() throws Exception {
        questionDuplicationChecker = new QuestionDuplicationChecker();
    }

    @Test
    public void shouldIssueWarningForDuplicateLabel() {
        Form form = createForm("src/input/ql/incorrect/validator/duplicateQuestionLabels.ql");
        boolean passesTests = questionDuplicationChecker.passesTests(form);
        assertTrue(passesTests);
        assertEquals(1, questionDuplicationChecker.getWarnings().size());
        assertEquals(0, questionDuplicationChecker.getErrors().size());
        assertEquals("Duplicate question label \"Question 1?\" used on multiple locations", questionDuplicationChecker.getWarnings().get(0).getMessage());
    }

    @Test
    public void shouldIssueErrorForDuplicateIDWithDifferentType() {
        Form form = createForm("src/input/ql/incorrect/validator/duplicateQuestionIDsDifferentTypes.ql");
        boolean passesTests = questionDuplicationChecker.passesTests(form);
        assertFalse(passesTests);
        assertEquals(0, questionDuplicationChecker.getWarnings().size());
        assertEquals(1, questionDuplicationChecker.getErrors().size());
        assertEquals("Question with identifier \"q1\" declared on multiple locations", questionDuplicationChecker.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldIssueNothingForDuplicateIDWithSameType() {
        Form form = createForm("src/input/ql/incorrect/validator/duplicateQuestionIDsSameTypes.ql");
        boolean passesTests = questionDuplicationChecker.passesTests(form);
        assertTrue(passesTests);
        assertEquals(0, questionDuplicationChecker.getWarnings().size());
        assertEquals(0, questionDuplicationChecker.getErrors().size());
    }

    @Test
    public void shouldIssueNothingForRegularForm() {
        Form form = createForm("src/input/ql/correct/simple.ql");
        boolean passesTests = questionDuplicationChecker.passesTests(form);
        assertTrue(passesTests);
        assertEquals(0, questionDuplicationChecker.getWarnings().size());
        assertEquals(0, questionDuplicationChecker.getErrors().size());
    }

}