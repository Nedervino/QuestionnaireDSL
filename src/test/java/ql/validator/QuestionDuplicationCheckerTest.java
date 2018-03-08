package ql.validator;

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
        assertEquals(1, issueTracker.getWarnings().size());
        assertEquals(0, issueTracker.getErrors().size());
        assertEquals("Duplicate question label \"Question 1?\" used on multiple locations", issueTracker.getWarnings().get(0).getMessage());
    }

    @Test
    public void shouldIssueErrorForDuplicateIDWithDifferentType() {
        issueTracker.reset();
        Form form = astBuilder.buildASTFromFile("src/input/ql/incorrect/duplicateQuestionIDsDifferentTypes.ql");
        boolean passesTests = questionDuplicationChecker.passesTests(form, new SymbolTable());
        assertFalse(passesTests);
        assertEquals(0, issueTracker.getWarnings().size());
        assertEquals(1, issueTracker.getErrors().size());
        assertEquals("Question with identifier \"q1\" declared on multiple locations", issueTracker.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldIssueNothingForDuplicateIDWithSameType() {
        issueTracker.reset();
        Form form = astBuilder.buildASTFromFile("src/input/ql/incorrect/duplicateQuestionIDsSameTypes.ql");
        boolean passesTests = questionDuplicationChecker.passesTests(form, new SymbolTable());
        assertTrue(passesTests);
        assertEquals(0, issueTracker.getWarnings().size());
        assertEquals(0, issueTracker.getErrors().size());
    }

    @Test
    public void shouldIssueNothingForRegularForm() {
        issueTracker.reset();
        Form form = astBuilder.buildASTFromFile("src/input/ql/correct/simple.ql");
        boolean passesTests = questionDuplicationChecker.passesTests(form, new SymbolTable());
        assertTrue(passesTests);
        assertEquals(0, issueTracker.getWarnings().size());
        assertEquals(0, issueTracker.getErrors().size());
    }

}