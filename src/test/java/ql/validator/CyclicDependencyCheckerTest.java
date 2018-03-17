package ql.validator;

import org.junit.Before;
import org.junit.Test;
import ql.Helper;
import ql.ast.Form;
import ql.parser.ASTBuilder;
import ql.validator.issuetracker.IssueTracker;

import static org.junit.Assert.*;

public class CyclicDependencyCheckerTest {

    ASTBuilder astBuilder;
    Helper helper;
    CyclicDependencyChecker cyclicDependencyChecker;
    IssueTracker issueTracker;

    @Before
    public void setUp() throws Exception {
        astBuilder = new ASTBuilder();
        helper = new Helper();
        issueTracker = new IssueTracker();
        cyclicDependencyChecker = new CyclicDependencyChecker(issueTracker);
    }

    @Test
    public void shouldIssueErrorForCycleWithinQuestion() {
        issueTracker.reset();
        Form form = helper.buildASTFromFile("src/input/ql/incorrect/cyclicalWithinQuestion.ql", astBuilder);
        boolean passesTests = cyclicDependencyChecker.passesTests(form);
        assertFalse(passesTests);
        assertEquals(issueTracker.getWarnings().size(), 0);
        assertEquals(issueTracker.getErrors().size(), 1);
        assertEquals(issueTracker.getErrors().get(0).getMessage(), "Variable first involved in circular dependency");
    }

    @Test
    public void shouldIssueErrorForCycleBetweenQuestions() {
        issueTracker.reset();
        Form form = helper.buildASTFromFile("src/input/ql/incorrect/cyclicalBetweenQuestions.ql", astBuilder);
        boolean passesTests = cyclicDependencyChecker.passesTests(form);
        assertFalse(passesTests);
        assertEquals(issueTracker.getWarnings().size(), 0);
        assertEquals(issueTracker.getErrors().size(), 2);
        assertEquals(issueTracker.getErrors().get(0).getMessage(), "Variable second involved in circular dependency");
        assertEquals(issueTracker.getErrors().get(1).getMessage(), "Variable first involved in circular dependency");
    }

    @Test
    public void shouldIssueNothingForRegularForm() {
        issueTracker.reset();
        Form form = helper.buildASTFromFile("src/input/ql/correct/simple.ql", astBuilder);
        boolean passesTests = cyclicDependencyChecker.passesTests(form);
        assertTrue(passesTests);
    }

}