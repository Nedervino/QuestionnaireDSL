package ql.evaluator;

import issuetracker.Error;
import issuetracker.IssueTracker;
import org.junit.Before;
import org.junit.Test;
import ql.BaseQlTest;
import ql.ast.Form;
import ql.evaluator.values.BooleanValue;
import ql.evaluator.values.Value;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class EvaluatorTest extends BaseQlTest {

    private FormEvaluator evaluator;
    private IssueTracker issueTracker;

    @Before
    public void setUp() throws Exception {
        evaluator = new Evaluator();
        issueTracker = IssueTracker.getIssueTracker();
    }

    @Test
    public void shouldStoreIntegerValueDecimalAsInteger() {
        Form form = createForm("src/input/ql/correct/evaluator/integerValueDecimal.ql");

        evaluator.start(form);

        Value value = evaluator.getQuestionValue("value");

        assertEquals(4, value.getValue());
    }

    @Test
    public void shouldKeepSameStateAfterReevaluation() {
        Form form = createForm("src/input/ql/correct/evaluator/booleanExpression.ql");
        evaluator.start(form);

        assertEquals(4, evaluator.getQuestions().size());

        evaluator.evaluate();
        assertEquals(4, evaluator.getQuestions().size());
    }

    @Test
    public void shouldReturnAllQuestionsInIfElseForm() {
        Form form = createForm("src/input/ql/correct/evaluator/ifElseEvaluation.ql");

        evaluator.start(form);
        assertEquals(3, evaluator.getQuestions().size());
    }

    @Test
    public void shouldStoreDecimal() {
        Form form = createForm("src/input/ql/correct/evaluator/simpleDecimal.ql");

        evaluator.start(form);

        Value value = evaluator.getQuestionValue("value");

        assertEquals(3.999, value.getValue());
    }

    @Test
    public void shouldStoreMoney() {
        Form form = createForm("src/input/ql/correct/evaluator/simpleMoney.ql");

        evaluator.start(form);

        Value value = evaluator.getQuestionValue("value");

        BigDecimal expected = new BigDecimal(3.99);
        expected = expected.setScale(2, BigDecimal.ROUND_HALF_EVEN);

        assertEquals(expected, value.getValue());
    }

    @Test
    public void shouldNotDivideByZero() {
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
        Form form = createForm("src/input/ql/correct/evaluator/downcastDecimalToInteger.ql");

        evaluator.start(form);

        Value value = evaluator.getQuestionValue("result");

        assertEquals(3, value.getValue());
    }

    @Test
    public void shouldMultiplyDecimals() {
        Form form = createForm("src/input/ql/correct/evaluator/decimalMultiplication.ql");

        evaluator.start(form);

        Value value = evaluator.getQuestionValue("result");

        assertEquals(13.0, value.getValue());
    }

    @Test
    public void shouldDivideMoneyToDecimal() {
        Form form = createForm("src/input/ql/correct/evaluator/moneyDivisionToDecimal.ql");

        evaluator.start(form);

        Value value = evaluator.getQuestionValue("result");
        assertEquals(0.8125, value.getValue());
    }

    @Test
    public void shouldDivideMoneyToMoney() {
        Form form = createForm("src/input/ql/correct/evaluator/moneyDivisionToMoney.ql");

        evaluator.start(form);

        Value value = evaluator.getQuestionValue("result");

        BigDecimal expected = new BigDecimal(3.33);
        expected = expected.setScale(2, BigDecimal.ROUND_HALF_EVEN);

        assertEquals(expected, value.getValue());
    }

    @Test
    public void shouldCompareStrings() {
        Form form = createForm("src/input/ql/correct/evaluator/stringComparison.ql");

        evaluator.start(form);

        Value value = evaluator.getQuestionValue("result");

        assertEquals(false, value.getValue());
    }

    @Test
    public void shouldCompareDates() {
        Form form = createForm("src/input/ql/correct/evaluator/dateComparison.ql");

        evaluator.start(form);

        Value value = evaluator.getQuestionValue("result");

        assertEquals(true, value.getValue());
    }

    @Test
    public void shouldEvaluateBooleans() {
        Form form = createForm("src/input/ql/correct/evaluator/booleanExpression.ql");

        evaluator.start(form);

        Value value = evaluator.getQuestionValue("result");

        assertEquals(true, value.getValue());
    }

    @Test
    public void shouldCompareIntegers() {
        Form form = createForm("src/input/ql/correct/evaluator/integerComparisonAndOperation.ql");

        evaluator.start(form);

        Value value = evaluator.getQuestionValue("result");
        Value value2 = evaluator.getQuestionValue("result2");

        assertEquals(true, value.getValue());
        assertEquals(true, value2.getValue());
    }

    @Test
    public void shouldFormatDateString() {
        Form form = createForm("src/input/ql/correct/evaluator/simpleDate.ql");

        evaluator.start(form);

        Value value = evaluator.getQuestionValue("value");

        assertEquals("01-02-1999", value.toString());
    }


    // @Test
    // public void shouldStoreDateString() throws ParseException{
    //     Form form = createForm("src/input/ql/correct/evaluator/simpleDate.ql");
    //
    //     SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    //     Date expected = dateFormat.parse("01-02-1999");
    //
    //     evaluator.start(form);
    //
    //     DateValue value = (DateValue) evaluator.getQuestionValue("value");
    //
    //     assertEquals(expected, value.getValue());
    // }

    @Test
    public void shouldEvaluateElse() {
        Form form = createForm("src/input/ql/correct/evaluator/ifElseEvaluation.ql");

        evaluator.start(form);

        BooleanValue value = (BooleanValue) evaluator.getQuestionValue("flag");

        assertEquals(true, value.getValue());
    }
}