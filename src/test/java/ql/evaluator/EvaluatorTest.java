package ql.evaluator;

import issuetracker.Error;
import issuetracker.IssueTracker;
import org.junit.Before;
import org.junit.Test;
import ql.Helper;
import ql.ast.Form;
import ql.evaluator.values.Evaluatable;
import ql.parser.FormBuilder;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class EvaluatorTest {

    private FormBuilder formBuilder;
    private Helper helper;
    private FormEvaluator evaluator;
    private IssueTracker issueTracker;

    @Before
    public void setUp() throws Exception {
        formBuilder = new FormBuilder();
        helper = new Helper();
        issueTracker = IssueTracker.getIssueTracker();
        evaluator = new Evaluator();
    }

    @Test
    public void shouldStoreIntegerValueDecimalAsInteger() {
        issueTracker.reset();
        Form form = helper.buildASTFromFile("src/input/ql/correct/evaluator/integerValueDecimal.ql", formBuilder);

        evaluator.start(form);
        issueTracker.reset();

        Evaluatable evaluatable = evaluator.getQuestionValue("value");

        assertEquals(4, evaluatable.getValue());
    }

    @Test
    public void shouldStoreDecimal() {
        issueTracker.reset();
        Form form = helper.buildASTFromFile("src/input/ql/correct/evaluator/simpleDecimal.ql", formBuilder);

        evaluator.start(form);
        issueTracker.reset();

        Evaluatable evaluatable = evaluator.getQuestionValue("value");

        assertEquals(3.999, evaluatable.getValue());
    }

    @Test
    public void shouldStoreMoney() {
        issueTracker.reset();
        Form form = helper.buildASTFromFile("src/input/ql/correct/evaluator/simpleMoney.ql", formBuilder);

        evaluator.start(form);
        issueTracker.reset();

        Evaluatable evaluatable = evaluator.getQuestionValue("value");

        BigDecimal expected = new BigDecimal(3.99);
        expected = expected.setScale(2, BigDecimal.ROUND_HALF_EVEN);

        assertEquals(expected, evaluatable.getValue());
    }

    @Test
    public void shouldNotDivideByZero() {
        issueTracker.reset();
        Form form = helper.buildASTFromFile("src/input/ql/correct/evaluator/divideByZero.ql", formBuilder);

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
        Form form = helper.buildASTFromFile("src/input/ql/correct/evaluator/downcastDecimalToInteger.ql", formBuilder);

        evaluator.start(form);

        Evaluatable evaluatable = evaluator.getQuestionValue("result");

        assertEquals(3, evaluatable.getValue());
    }

    @Test
    public void shouldMultiplyDecimals() {
        issueTracker.reset();
        Form form = helper.buildASTFromFile("src/input/ql/correct/evaluator/decimalMultiplication.ql", formBuilder);

        evaluator.start(form);
        issueTracker.reset();

        Evaluatable evaluatable = evaluator.getQuestionValue("result");

        assertEquals(13.0, evaluatable.getValue());
    }

    @Test
    public void shouldDivideMoneyToDecimal() {
        issueTracker.reset();
        Form form = helper.buildASTFromFile("src/input/ql/correct/evaluator/moneyDivisionToDecimal.ql", formBuilder);

        evaluator.start(form);

        Evaluatable evaluatable = evaluator.getQuestionValue("result");
        assertEquals(0.8125, evaluatable.getValue());
    }

    @Test
    public void shouldDivideMoneyToMoney() {
        issueTracker.reset();
        Form form = helper.buildASTFromFile("src/input/ql/correct/evaluator/moneyDivisionToMoney.ql", formBuilder);

        evaluator.start(form);

        Evaluatable evaluatable = evaluator.getQuestionValue("result");

        BigDecimal expected = new BigDecimal(3.33);
        expected = expected.setScale(2, BigDecimal.ROUND_HALF_EVEN);

        assertEquals(expected, evaluatable.getValue());
    }
}