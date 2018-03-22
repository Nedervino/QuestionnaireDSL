package ql.parser;

import issuetracker.IssueTracker;
import org.junit.Before;
import ql.Helper;

public class ParserTest {

    private FormBuilder formBuilder;
    private Helper helper;
    private IssueTracker issueTracker;

    @Before
    public void setUp() {
        formBuilder = new FormBuilder();
        helper = new Helper();
        issueTracker = IssueTracker.getIssueTracker();
    }
    //
    // @Test
    // public void shouldStoreMoneyWithoutDecimalDigits() {
    //     issueTracker.reset();
    //     helper.buildASTFromFile("src/input/ql/correct/moneyNoDigits.ql", formBuilder);
    //
    //     assertEquals(0, issueTracker.getErrors().size());
    // }
    //
    // @Test
    // public void shouldStoreMoneyWithOneDecimalDigits() {
    //     issueTracker.reset();
    //     helper.buildASTFromFile("src/input/ql/correct/moneyOneDigit.ql", formBuilder);
    //
    //     assertEquals(0, issueTracker.getErrors().size());
    // }
    //
    // @Test
    // public void shouldStoreMoneyTwoDecimalDigits() {
    //     issueTracker.reset();
    //     Form form = helper.buildASTFromFile("src/input/ql/correct/evaluator/simpleMoney.ql", formBuilder);
    //
    //     assertEquals(0, issueTracker.getErrors().size());
    // }
}