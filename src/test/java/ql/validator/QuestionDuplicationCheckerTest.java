package ql.validator;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import ql.ast.Form;
import ql.parser.ASTBuilder;
import ql.validator.issuetracker.IssueTracker;

import static org.junit.Assert.*;

public class QuestionDuplicationCheckerTest {

    ASTBuilder astBuilder;
    QuestionDuplicationChecker questionDuplicationChecker;
    IssueTracker issueTracker;

    @Before
    public void setUp() throws Exception {
        astBuilder = new ASTBuilder();
        issueTracker = new IssueTracker();
        questionDuplicationChecker = new QuestionDuplicationChecker(issueTracker);
    }

    @Test
    public void shouldIssueWarningForDuplicateLabel() {
        issueTracker.reset();
        Form form = astBuilder.buildASTFromFile("src/input/ql/incorrect/duplicateQuestionLabels.ql");
        boolean passesTests = questionDuplicationChecker.passesTests(form, new SymbolTable());
        assertTrue(passesTests);
        assertEquals(issueTracker.getWarnings().size(), 1);
        assertEquals(issueTracker.getErrors().size(), 0);
        assertEquals(issueTracker.getWarnings().get(0).getMessage(), "Duplicate question label \"Question 1?\" used on multiple locations");
    }

    @Test
    public void shouldIssueErrorForDuplicateIDWithDifferentType() {
        issueTracker.reset();
        Form form = astBuilder.buildASTFromFile("src/input/ql/incorrect/duplicateQuestionIDsDifferentTypes.ql");
        boolean passesTests = questionDuplicationChecker.passesTests(form, new SymbolTable());
        assertFalse(passesTests);
        assertEquals(issueTracker.getWarnings().size(), 0);
        assertEquals(issueTracker.getErrors().size(), 1);
        assertEquals(issueTracker.getErrors().get(0).getMessage(), "Question with identifier \"q1\" declared on multiple locations");
    }

    @Test
    public void shouldIssueNothingForDuplicateIDWithSameType() {
        issueTracker.reset();
        Form form = astBuilder.buildASTFromFile("src/input/ql/incorrect/duplicateQuestionIDsSameTypes.ql");
        boolean passesTests = questionDuplicationChecker.passesTests(form, new SymbolTable());
        assertTrue(passesTests);
        assertEquals(issueTracker.getWarnings().size(), 0);
        assertEquals(issueTracker.getErrors().size(), 0);
    }

    @Test
    public void shouldIssueNothingForRegularForm() {
        issueTracker.reset();
        Form form = astBuilder.buildASTFromFile("src/input/ql/correct/simple.ql");
        boolean passesTests = questionDuplicationChecker.passesTests(form, new SymbolTable());
        assertTrue(passesTests);
        assertEquals(issueTracker.getWarnings().size(), 0);
        assertEquals(issueTracker.getErrors().size(), 0);
    }

}