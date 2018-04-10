package qls.validator.checkers;

import org.junit.Test;
import ql.ast.Form;
import ql.validator.checkers.Checker;
import qls.BaseQlsTest;
import qls.ast.Stylesheet;

import static org.junit.Assert.*;

public class QuestionReferenceCheckerTest extends BaseQlsTest {

    @Test
    public void shouldIssueErrorForReferenceToUndefinedQuestions() {
        Form form = createForm("src/input/qls/incorrect/questionNotDefinedInQL.ql");
        Stylesheet stylesheet = createStylesheet("src/input/qls/incorrect/questionNotDefinedInQL.qls");
        Checker questionReferenceChecker = new QuestionReferenceChecker(form, stylesheet);
        boolean passesTests = questionReferenceChecker.passesTests();
        assertFalse(passesTests);
        assertEquals(0, questionReferenceChecker.getWarnings().size());
        assertEquals(1, questionReferenceChecker.getErrors().size());
        assertEquals("Stylesheet contains reference to non-existing question \"q2\"", questionReferenceChecker.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldIssueErrorForMissingReferencesToQuestions() {
        Form form = createForm("src/input/qls/incorrect/questionNotDefinedInQLS.ql");
        Stylesheet stylesheet = createStylesheet("src/input/qls/incorrect/questionNotDefinedInQLS.qls");
        Checker questionReferenceChecker = new QuestionReferenceChecker(form, stylesheet);
        boolean passesTests = questionReferenceChecker.passesTests();
        assertFalse(passesTests);
        assertEquals(0, questionReferenceChecker.getWarnings().size());
        assertEquals(1, questionReferenceChecker.getErrors().size());
        assertEquals("Stylesheet misses reference to question \"q2\"", questionReferenceChecker.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldIssueErrorForDuplicateReferences() {
        Form form = createForm("src/input/qls/incorrect/multipleReferencesSameQuestion.ql");
        Stylesheet stylesheet = createStylesheet("src/input/qls/incorrect/multipleReferencesSameQuestion.qls");
        Checker questionReferenceChecker = new QuestionReferenceChecker(form, stylesheet);
        boolean passesTests = questionReferenceChecker.passesTests();
        assertFalse(passesTests);
        assertEquals(0, questionReferenceChecker.getWarnings().size());
        assertEquals(1, questionReferenceChecker.getErrors().size());
        assertEquals("Multiple references for question \"q2\"", questionReferenceChecker.getErrors().get(0).getMessage());
    }

}