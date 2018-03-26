package ql.validator;

import issuetracker.Error;
import issuetracker.IssueTracker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ql.BaseQlTest;
import ql.ast.Form;
import ql.validator.checkers.ExpressionChecker;

import static org.junit.Assert.*;

public class ExpressionCheckerTest extends BaseQlTest {

    private ExpressionChecker expressionChecker;
    private IssueTracker issueTracker;

    @Before
    public void setUp() throws Exception {
        issueTracker = IssueTracker.getIssueTracker();
        issueTracker.reset();
        expressionChecker = new ExpressionChecker(issueTracker);
    }

    @After
    public void tearDown() throws Exception {
        issueTracker.reset();
    }

    @Test
    public void shouldIssueErrorForUndefinedQuestionReference() {
        Form form = createForm("src/input/ql/incorrect/validator/undefinedQuestion.ql");

        boolean passesTests = expressionChecker.passesTests(form);
        assertFalse(passesTests);
        assertEquals(0, issueTracker.getWarnings().size());
        assertEquals(1, issueTracker.getErrors().size());
        assertEquals("Reference to undefined question", issueTracker.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldIssueErrorForNonBooleanCondition() {
        Form form = createForm("src/input/ql/incorrect/validator/nonBooleanCondition.ql");

        boolean passesTests = expressionChecker.passesTests(form);
        assertFalse(passesTests);
        assertEquals(0, issueTracker.getWarnings().size());
        assertEquals(1, issueTracker.getErrors().size());
        assertEquals("Non-boolean conditional", issueTracker.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldIssueTypeErrorsForNonNumericMismatches() {
        Form form = createForm("src/input/ql/incorrect/validator/incompatibleBinaryExpressionTypes.ql");

        boolean passesTests = expressionChecker.passesTests(form);
        assertFalse(passesTests);

        assertEquals(0, issueTracker.getWarnings().size());
        assertEquals(13, issueTracker.getErrors().size());
        for (Error error : issueTracker.getErrors()) {
            assertEquals("Incompatible", error.getMessage().substring(0, 12));
        }
    }

    @Test
    public void shouldIssueErrorForNonNumericInArithmeticExpression() {
        Form form = createForm("src/input/ql/incorrect/validator/nonNumericInArithmeticExpression.ql");

        boolean passesTests = expressionChecker.passesTests(form);
        assertFalse(passesTests);

        assertEquals(0, issueTracker.getWarnings().size());
        assertEquals(3, issueTracker.getErrors().size());
        for (Error error : issueTracker.getErrors()) {
            assertEquals("Type mismatch", error.getMessage().substring(0, 13));
        }
    }

    // @Test
    // public void shouldIssueNoErrorForStringConcatenation() {
    //     assertFalse(true);
    // }

    @Test
    public void shouldIssueNoErrorForNumericExpressionsWithMoneyType() {
        Form form = createForm("src/input/ql/correct/validator/numericExpressionsWithMoneyType.ql");


        boolean passesTests = expressionChecker.passesTests(form);
        assertTrue(passesTests);

        assertEquals(0, issueTracker.getWarnings().size());
        assertEquals(0, issueTracker.getErrors().size());
    }

    @Test
    public void shouldIssueNoErrorForDifferentNumericCombinations() {
        Form form = createForm("src/input/ql/correct/validator/numericCombinations.ql");


        boolean passesTests = expressionChecker.passesTests(form);
        assertTrue(passesTests);

        assertEquals(0, issueTracker.getWarnings().size());
        assertEquals(0, issueTracker.getErrors().size());
    }

    @Test
    public void shouldIssueErrorForNonBooleanInBooleanExpression() {
        Form form = createForm("src/input/ql/incorrect/validator/nonBooleanInBoolean.ql");


        boolean passesTests = expressionChecker.passesTests(form);
        assertFalse(passesTests);

        assertEquals(0, issueTracker.getWarnings().size());
        assertEquals(2, issueTracker.getErrors().size());
        for (Error error : issueTracker.getErrors()) {
            assertEquals("Type mismatch", error.getMessage().substring(0, 13));
        }
    }

    @Test
    public void shouldIssueErrorForNonNumericInComparisonExpression() {
        Form form = createForm("src/input/ql/incorrect/validator/nonNumericInComparison.ql");


        boolean passesTests = expressionChecker.passesTests(form);
        assertFalse(passesTests);

        assertEquals(0, issueTracker.getWarnings().size());
        assertEquals(2, issueTracker.getErrors().size());
        for (Error error : issueTracker.getErrors()) {
            assertEquals("Type mismatch", error.getMessage().substring(0, 13));
        }
    }

}