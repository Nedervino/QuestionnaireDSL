package ql.validator;

import org.junit.Test;
import ql.BaseQlTest;
import ql.ast.Form;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidatorTest extends BaseQlTest {

    @Test
    public void shouldFailForReferenceToUndefinedQuestion() throws Exception {
        Form form = createForm("src/input/ql/incorrect/validator/undefinedQuestion.ql");
        assertFalse(Validator.passesChecks(form));
    }

    @Test
    public void shouldFailForDuplicateQuestions() throws Exception {
        Form form = createForm("src/input/ql/incorrect/validator/duplicateQuestionIdsDifferentTypes.ql");
        assertFalse(Validator.passesChecks(form));
    }

    @Test
    public void shouldFailForNonBooleanConditionals() throws Exception {
        Form form = createForm("src/input/ql/incorrect/validator/nonBooleanCondition.ql");
        assertFalse(Validator.passesChecks(form));
    }

    @Test
    public void shouldFailForExpressionMismatches() throws Exception {
        Form form = createForm("src/input/ql/incorrect/validator/incompatibleBinaryExpressionTypes.ql");
        assertFalse(Validator.passesChecks(form));
    }

    @Test
    public void shouldFailForCycles() throws Exception {
        Form form = createForm("src/input/ql/incorrect/validator/cyclicalBetweenQuestions.ql");
        assertFalse(Validator.passesChecks(form));
    }

    @Test
    public void shouldSucceedForRegularForm() throws Exception {
        Form form = createForm("src/input/ql/correct/gui/allComputedQuestionTypes.ql");
        assertTrue(Validator.passesChecks(form));
    }
}