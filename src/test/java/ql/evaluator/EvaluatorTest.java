package ql.evaluator;

import issuetracker.Error;
import issuetracker.IssueTracker;
import org.junit.Before;
import org.junit.Test;
import ql.BaseQlTest;
import ql.ast.Form;
import ql.evaluator.values.Value;
import ql.parser.FormBuilder;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class EvaluatorTest extends BaseQlTest {

    private FormBuilder formBuilder;
    private FormEvaluator evaluator;
    private IssueTracker issueTracker;

    @Before
    public void setUp() throws Exception {
        formBuilder = new FormBuilder();
        issueTracker = IssueTracker.getIssueTracker();
        evaluator = new Evaluator();
    }

    @Test
    public void shouldStoreIntegerValueDecimalAsInteger() {
        issueTracker.reset();
        Form form = createForm("src/input/ql/correct/evaluator/integerValueDecimal.ql");

        evaluator.start(form);
        issueTracker.reset();

        Value value = evaluator.getQuestionValue("value");

        assertEquals(4, value.getValue());
    }

    @Test
    public void shouldStoreDecimal() {
        issueTracker.reset();
        Form form = createForm("src/input/ql/correct/evaluator/simpleDecimal.ql");

        evaluator.start(form);
        issueTracker.reset();

        Value value = evaluator.getQuestionValue("value");

        assertEquals(3.999, value.getValue());
    }

    @Test
    public void shouldStoreMoney() {
        issueTracker.reset();
        Form form = createForm("src/input/ql/correct/evaluator/simpleMoney.ql");

        evaluator.start(form);
        issueTracker.reset();

        Value value = evaluator.getQuestionValue("value");

        BigDecimal expected = new BigDecimal(3.99);
        expected = expected.setScale(2, BigDecimal.ROUND_HALF_EVEN);

        assertEquals(expected, value.getValue());
    }

    @Test
    public void shouldNotDivideByZero() {
        issueTracker.reset();
        Form form = createForm("src/input/ql/correct/evaluator/divideByZero.ql");

        evaluator.start(form);

        assertEquals(0, issueTracker.getWarnings().size());
        assertEquals(1, issueTracker.getErrors().size());
        for (Error error : issueTracker.getErrors()) {
            System.out.println(error.getMessage());
            assertEquals("Attempted to divide by zero.", error.getMessage());
        }
    }

    @Test
    public void shouldDownCastDecimalToInteger() {
        issueTracker.reset();
        Form form = createForm("src/input/ql/correct/evaluator/downcastDecimalToInteger.ql");

        evaluator.start(form);

        Value value = evaluator.getQuestionValue("result");

        assertEquals(3, value.getValue());
    }

    @Test
    public void shouldMultiplyDecimals() {
        issueTracker.reset();
        Form form = createForm("src/input/ql/correct/evaluator/decimalMultiplication.ql");

        evaluator.start(form);
        issueTracker.reset();

        Value value = evaluator.getQuestionValue("result");

        assertEquals(13.0, value.getValue());
    }

    @Test
    public void shouldDivideMoneyToDecimal() {
        issueTracker.reset();
        Form form = createForm("src/input/ql/correct/evaluator/moneyDivisionToDecimal.ql");

        evaluator.start(form);

        Value value = evaluator.getQuestionValue("result");
        assertEquals(0.8125, value.getValue());
    }

    @Test
    public void shouldDivideMoneyToMoney() {
        issueTracker.reset();
        Form form = createForm("src/input/ql/correct/evaluator/moneyDivisionToMoney.ql");

        evaluator.start(form);

        Value value = evaluator.getQuestionValue("result");

        BigDecimal expected = new BigDecimal(3.33);
        expected = expected.setScale(2, BigDecimal.ROUND_HALF_EVEN);

        assertEquals(expected, value.getValue());
    }

    @Test
    public void shouldCompareStrings() {
        issueTracker.reset();
        Form form = createForm("src/input/ql/correct/evaluator/stringComparison.ql");

        evaluator.start(form);

        Value value = evaluator.getQuestionValue("result");

        assertEquals(false, value.getValue());
    }

    @Test
    public void shouldCompareDates() {
        issueTracker.reset();
        Form form = createForm("src/input/ql/correct/evaluator/dateComparison.ql");

        evaluator.start(form);

        Value value = evaluator.getQuestionValue("result");

        assertEquals(true, value.getValue());
    }

    @Test
    public void shouldEvaluateBooleans() {
        issueTracker.reset();
        Form form = createForm("src/input/ql/correct/evaluator/booleanExpression.ql");

        evaluator.start(form);

        Value value = evaluator.getQuestionValue("result");

        assertEquals(true, value.getValue());
    }

    @Test
    public void shouldCompareIntegers() {
        issueTracker.reset();
        Form form = createForm("src/input/ql/correct/evaluator/integerComparisonAndOperation.ql");

        evaluator.start(form);

        Value value = evaluator.getQuestionValue("result");
        Value value2 = evaluator.getQuestionValue("result2");

        assertEquals(true, value.getValue());
        assertEquals(true, value2.getValue());
    }

    @Test
    public void shouldFormatDateString() {
        issueTracker.reset();
        Form form = createForm("src/input/ql/correct/evaluator/simpleDate.ql");

        evaluator.start(form);

        Value value = evaluator.getQuestionValue("value");

        assertEquals("01-02-1999", value.toString());
    }

    @Test
    public void shouldEvaluateElse() {
        issueTracker.reset();
        Form form = createForm("src/input/ql/correct/evaluator/ifElseEvaluation.ql");

        evaluator.start(form);

        Value value = evaluator.getQuestionValue("flag");

        assertEquals(true, value);
    }
}