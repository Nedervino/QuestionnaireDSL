package ql.environment;

import issuetracker.Error;
import issuetracker.IssueTracker;
import org.junit.Before;
import org.junit.Test;
import ql.BaseQlTest;
import ql.ast.Form;
import ql.environment.values.BooleanValue;
import ql.environment.values.DateValue;
import ql.environment.values.Value;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class FormEnvironmentTest extends BaseQlTest {

    private Environment environment;
    private IssueTracker issueTracker;

    @Before
    public void setUp() throws Exception {
        issueTracker = new IssueTracker();
    }

    @Test
    public void shouldStoreIntegerValueDecimalAsInteger() {
        Form form = createForm("src/input/ql/correct/environment/integerValueDecimal.ql");
        environment = new FormEnvironment(form);
        environment.evaluate();

        Value value = environment.getQuestionValue("value");

        assertEquals(4, value.getValue());
    }

    @Test
    public void shouldKeepSameStateAfterReevaluation() {
        Form form = createForm("src/input/ql/correct/environment/booleanExpression.ql");
        environment = new FormEnvironment(form);
        environment.evaluate();

        assertEquals(4, environment.getQuestions().size());

        environment.evaluate();
        assertEquals(4, environment.getQuestions().size());
    }

    @Test
    public void shouldReturnAllQuestionsInIfElseForm() {
        Form form = createForm("src/input/ql/correct/environment/ifElseEvaluation.ql");
        environment = new FormEnvironment(form);
        environment.evaluate();

        assertEquals(3, environment.getQuestions().size());
    }

    @Test
    public void shouldStoreDecimal() {
        Form form = createForm("src/input/ql/correct/environment/simpleDecimal.ql");
        environment = new FormEnvironment(form);
        environment.evaluate();

        Value value = environment.getQuestionValue("value");

        assertEquals(3.999, value.getValue());
    }

    @Test
    public void shouldStoreMoney() {
        Form form = createForm("src/input/ql/correct/environment/simpleMoney.ql");
        environment = new FormEnvironment(form);
        environment.evaluate();

        Value value = environment.getQuestionValue("value");

        BigDecimal expected = new BigDecimal(3.99);
        expected = expected.setScale(2, BigDecimal.ROUND_HALF_EVEN);

        assertEquals(expected, value.getValue());
    }

    @Test
    public void shouldNotDivideByZero() {
        Form form = createForm("src/input/ql/correct/environment/divideByZero.ql");
        environment = new FormEnvironment(form);
        environment.evaluate();

        assertEquals(0, issueTracker.getWarnings().size());
        assertEquals(1, issueTracker.getErrors().size());
        for (Error error : issueTracker.getErrors()) {
            System.out.println(error.getMessage());
            assertEquals("Attempted to divide by zero.", error.getMessage());
        }
    }

    @Test
    public void shouldDownCastDecimalToInteger() {
        Form form = createForm("src/input/ql/correct/environment/downcastDecimalToInteger.ql");
        environment = new FormEnvironment(form);
        environment.evaluate();

        Value value = environment.getQuestionValue("result");

        assertEquals(3, value.getValue());
    }

    @Test
    public void shouldMultiplyDecimals() {
        Form form = createForm("src/input/ql/correct/environment/decimalMultiplication.ql");
        environment = new FormEnvironment(form);
        environment.evaluate();

        Value value = environment.getQuestionValue("result");

        assertEquals(13.0, value.getValue());
    }

    @Test
    public void shouldDivideMoneyToDecimal() {
        Form form = createForm("src/input/ql/correct/environment/moneyDivisionToDecimal.ql");
        environment = new FormEnvironment(form);
        environment.evaluate();

        Value value = environment.getQuestionValue("result");
        assertEquals(0.8125, value.getValue());
    }

    @Test
    public void shouldDivideMoneyToMoney() {
        Form form = createForm("src/input/ql/correct/environment/moneyDivisionToMoney.ql");
        environment = new FormEnvironment(form);
        environment.evaluate();

        Value value = environment.getQuestionValue("result");

        BigDecimal expected = new BigDecimal(3.33);
        expected = expected.setScale(2, BigDecimal.ROUND_HALF_EVEN);

        assertEquals(expected, value.getValue());
    }

    @Test
    public void shouldCompareStrings() {
        Form form = createForm("src/input/ql/correct/environment/stringComparison.ql");
        environment = new FormEnvironment(form);
        environment.evaluate();

        Value value = environment.getQuestionValue("result");

        assertEquals(false, value.getValue());
    }

    @Test
    public void shouldCompareDates() {
        Form form = createForm("src/input/ql/correct/environment/dateComparison.ql");
        environment = new FormEnvironment(form);
        environment.evaluate();

        Value value = environment.getQuestionValue("result");

        assertEquals(true, value.getValue());
    }

    @Test
    public void shouldEvaluateBooleans() {
        Form form = createForm("src/input/ql/correct/environment/booleanExpression.ql");
        environment = new FormEnvironment(form);
        environment.evaluate();

        Value value = environment.getQuestionValue("result");

        assertEquals(true, value.getValue());
    }

    @Test
    public void shouldCompareIntegers() {
        Form form = createForm("src/input/ql/correct/environment/integerComparisonAndOperation.ql");
        environment = new FormEnvironment(form);
        environment.evaluate();

        Value value = environment.getQuestionValue("result");
        Value value2 = environment.getQuestionValue("result2");

        assertEquals(true, value.getValue());
        assertEquals(true, value2.getValue());
    }

    @Test
    public void shouldFormatDateString() {
        Form form = createForm("src/input/ql/correct/environment/simpleDate.ql");
        environment = new FormEnvironment(form);
        environment.evaluate();

        DateValue value = (DateValue) environment.getQuestionValue("value");

        assertEquals("01-02-1999", value.getDisplayValue());
    }


    // @Test
    // public void shouldStoreDateString() throws ParseException{
    //     Form form = createForm("src/input/ql/correct/environment/simpleDate.ql");
    //
    //     SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    //     Date expected = dateFormat.parse("01-02-1999");
    //
    //     environment.start(form);
    //
    //     DateValue value = (DateValue) environment.getQuestionValue("value");
    //
    //     assertEquals(expected, value.getValue());
    // }

    @Test
    public void shouldEvaluateElse() {
        Form form = createForm("src/input/ql/correct/environment/ifElseEvaluation.ql");
        environment = new FormEnvironment(form);
        environment.evaluate();

        BooleanValue value = (BooleanValue) environment.getQuestionValue("flag");

        assertEquals(true, value.getValue());
    }
}