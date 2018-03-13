package ql.validator;

import org.junit.Before;
import org.junit.Test;
import ql.ast.Form;
import ql.parser.ASTBuilder;
import ql.validator.issuetracker.Error;
import ql.validator.issuetracker.IssueTracker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ExpressionCheckerTest {

    ASTBuilder astBuilder;
    ExpressionChecker expressionChecker;
    IssueTracker issueTracker;

    @Before
    public void setUp() throws Exception {
        astBuilder = new ASTBuilder();
        issueTracker = new IssueTracker();
        expressionChecker = new ExpressionChecker(issueTracker);
    }

    @Test
    public void shouldIssueErrorForUndefinedQuestionReference() {
        issueTracker.reset();
        Form form = astBuilder.buildASTFromFile("src/input/ql/incorrect/undefinedQuestion.ql");

        //Initialize symbolTable;
        SymbolTable symbolTable = new SymbolTable();
        new QuestionDuplicationChecker(issueTracker).passesTests(form, symbolTable);
        issueTracker.reset();

        boolean passesTests = expressionChecker.passesTests(form, symbolTable);
        assertFalse(passesTests);
        assertEquals(0, issueTracker.getWarnings().size());
        assertEquals(1, issueTracker.getErrors().size());
        assertEquals("Reference to undefined question", issueTracker.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldIssueErrorForNonBooleanCondition() {
        Form form = astBuilder.buildASTFromFile("src/input/ql/incorrect/nonBooleanCondition.ql");

        //Initialize symbolTable;
        SymbolTable symbolTable = new SymbolTable();
        new QuestionDuplicationChecker(issueTracker).passesTests(form, symbolTable);
        issueTracker.reset();

        boolean passesTests = expressionChecker.passesTests(form, symbolTable);
        assertFalse(passesTests);
        assertEquals(0, issueTracker.getWarnings().size());
        assertEquals(1, issueTracker.getErrors().size());
        assertEquals("Non-boolean conditional", issueTracker.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldIssueTypeErrorsForNonNumericMismatches() {
        Form form = astBuilder.buildASTFromFile("src/input/ql/incorrect/incompatibleBinaryExpressionTypes.ql");

        //Initialize symbolTable;
        SymbolTable symbolTable = new SymbolTable();
        new QuestionDuplicationChecker(issueTracker).passesTests(form, symbolTable);
        issueTracker.reset();

        boolean passesTests = expressionChecker.passesTests(form, symbolTable);
        assertFalse(passesTests);

        assertEquals(0, issueTracker.getWarnings().size());
        assertEquals(15, issueTracker.getErrors().size());
        for (Error error : issueTracker.getErrors()) {
            assertEquals("Incompatible", error.getMessage().substring(0, 12));
        }
    }

    @Test
    public void shouldIssueErrorForNonNumericInArithmeticExpression() {
        assertFalse(true);
    }

    @Test
    public void shouldIssueNoErrorForStringConcatenation() {
        assertFalse(true);
    }

    @Test
    public void shouldIssueNoErrorForNumericExpressionsWithMoneyType() {
        assertFalse(true);
    }

    @Test
    public void shouldIssueErrorForNonBooleanInBooleanExpression() {
        assertFalse(true);
    }

    @Test
    public void shouldIssueErrorForNonNumericInComparisonExpression() {
        assertFalse(true);
    }

}