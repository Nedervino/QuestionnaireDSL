package ql.validator;

import org.junit.Before;
import org.junit.Test;
import ql.BaseQlTest;
import ql.ast.Form;
import ql.validator.checkers.QuestionDuplicationChecker;

import static org.junit.Assert.*;

public class QuestionDuplicationCheckerTest extends BaseQlTest {

    @Test
    public void shouldIssueWarningForDuplicateLabel() {
        Form form = createForm("src/input/ql/incorrect/validator/duplicateQuestionLabels.ql");
        QuestionDuplicationChecker questionDuplicationChecker = new QuestionDuplicationChecker(form);

        boolean passesTests = questionDuplicationChecker.passesTests();
        assertTrue(passesTests);
        assertEquals(1, questionDuplicationChecker.getWarnings().size());
        assertEquals(0, questionDuplicationChecker.getErrors().size());
        assertEquals("Duplicate question label \"Question 1?\" used on multiple locations", questionDuplicationChecker.getWarnings().get(0).getMessage());
    }

    @Test
    public void shouldIssueErrorForDuplicateIDWithDifferentType() {
        Form form = createForm("src/input/ql/incorrect/validator/duplicateQuestionIDsDifferentTypes.ql");
        QuestionDuplicationChecker questionDuplicationChecker = new QuestionDuplicationChecker(form);

        boolean passesTests = questionDuplicationChecker.passesTests();
        assertFalse(passesTests);
        assertEquals(0, questionDuplicationChecker.getWarnings().size());
        assertEquals(1, questionDuplicationChecker.getErrors().size());
        assertEquals("Question with identifier \"q1\" declared on multiple locations", questionDuplicationChecker.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldIssueNothingForDuplicateIDWithSameType() {
        Form form = createForm("src/input/ql/incorrect/validator/duplicateQuestionIDsSameTypes.ql");
        QuestionDuplicationChecker questionDuplicationChecker = new QuestionDuplicationChecker(form);

        boolean passesTests = questionDuplicationChecker.passesTests();
        assertTrue(passesTests);
        assertEquals(0, questionDuplicationChecker.getWarnings().size());
        assertEquals(0, questionDuplicationChecker.getErrors().size());
    }

    @Test
    public void shouldIssueNothingForRegularForm() {
        Form form = createForm("src/input/ql/correct/simple.ql");
        QuestionDuplicationChecker questionDuplicationChecker = new QuestionDuplicationChecker(form);

        boolean passesTests = questionDuplicationChecker.passesTests();
        assertTrue(passesTests);
        assertEquals(0, questionDuplicationChecker.getWarnings().size());
        assertEquals(0, questionDuplicationChecker.getErrors().size());
    }

}