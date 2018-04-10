package ql.environment;

import org.junit.Test;
import ql.BaseQlTest;
import ql.ast.Form;
import ql.environment.values.BooleanValue;
import ql.environment.values.DateValue;
import ql.environment.values.IntegerValue;
import ql.environment.values.Value;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FormEnvironmentTest extends BaseQlTest {

    private Environment environment;

    @Test
    public void shouldEvaluateAllAncestorsForVisibility() {
        Form form = createForm("src/input/ql/correct/gui/nestedIf.ql");
        environment = new FormEnvironment(form);
        environment.evaluate();

        assertTrue(environment.questionIsVisible("result"));
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
    public void shouldKeepStateAfterSettingAndEvaluation() {
        Form form = createForm("src/input/ql/correct/gui/dependentValue.ql");
        environment = new FormEnvironment(form);
        environment.setValue("q1", new IntegerValue(40));
        environment.setValue("q2", new IntegerValue(10));
        assertEquals(50, environment.getQuestionValue("q3").getValue());
        environment.evaluate();
        assertEquals(50, environment.getQuestionValue("q3").getValue());
    }

    @Test
    public void shouldEvaluateForwardReference() {
        Form form = createForm("src/input/ql/incorrect/validator/forwardReferenceQuestion.ql");
        environment = new FormEnvironment(form);
        environment.evaluate();
        assertEquals(6, environment.getQuestionValue("first").getValue());
        assertEquals(4, environment.getQuestionValue("second").getValue());
        assertEquals(2, environment.getQuestionValue("third").getValue());
        assertEquals(0, environment.getQuestionValue("fourth").getValue());
        environment.setValue("fourth", new IntegerValue(2));
        assertEquals(8, environment.getQuestionValue("first").getValue());
        assertEquals(6, environment.getQuestionValue("second").getValue());
        assertEquals(4, environment.getQuestionValue("third").getValue());
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
        try {
            environment.evaluate();
        } catch (IllegalArgumentException e) {
            assertEquals("Attempted to divide by zero.", e.getMessage());
        }
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
        Value value3 = environment.getQuestionValue("result3");
        assertEquals(true, value.getValue());
        assertEquals(true, value2.getValue());
        assertEquals(true, value3.getValue());
    }

    @Test
    public void shouldFormatDateString() {
        Form form = createForm("src/input/ql/correct/environment/simpleDate.ql");
        environment = new FormEnvironment(form);
        environment.evaluate();

        DateValue value = (DateValue) environment.getQuestionValue("value");

        assertEquals("01-02-1999", value.getDisplayValue());
    }

    @Test
    public void shouldEvaluateIfElseStatements() {
        Form form = createForm("src/input/ql/correct/environment/ifElseDeclaration.ql");
        environment = new FormEnvironment(form);
        environment.evaluate();

        BooleanValue value = (BooleanValue) environment.getQuestionValue("flag");
        assertEquals(3, environment.getQuestions().size());
        assertEquals(true, value.getValue());
    }
}