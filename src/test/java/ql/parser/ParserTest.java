package ql.parser;

import issuetracker.IssueTracker;
import org.junit.Before;
import ql.BaseQlTest;

public class ParserTest {

    private FormBuilder formBuilder;
    private IssueTracker issueTracker;

    @Before
    public void setUp() {
        formBuilder = new FormBuilder();
        issueTracker = IssueTracker.getIssueTracker();
    }
    //
    // @Test
    // public void shouldStoreMoneyWithoutDecimalDigits() {
    //     issueTracker.reset();
    //     createForm("src/input/ql/correct/moneyNoDigits.ql");
    //
    //     assertEquals(0, issueTracker.getErrors().size());
    // }
    //
    // @Test
    // public void shouldStoreMoneyWithOneDecimalDigits() {
    //     issueTracker.reset();
    //     createForm("src/input/ql/correct/moneyOneDigit.ql");
    //
    //     assertEquals(0, issueTracker.getErrors().size());
    // }
    //
    // @Test
    // public void shouldStoreMoneyTwoDecimalDigits() {
    //     issueTracker.reset();
    //     Form form = createForm("src/input/ql/correct/evaluator/simpleMoney.ql");
    //
    //     assertEquals(0, issueTracker.getErrors().size());
    // }
}