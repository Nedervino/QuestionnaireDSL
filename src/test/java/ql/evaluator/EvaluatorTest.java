package ql.evaluator;

import issuetracker.Error;
import org.junit.Before;
import org.junit.Test;
import ql.Helper;
import ql.ast.Form;
import ql.evaluator.values.Evaluatable;
import ql.parser.FormBuilder;
import issuetracker.IssueTracker;

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
        issueTracker = new IssueTracker();
        evaluator = new Evaluator(issueTracker);
    }

    @Test
    public void shouldStoreIntegerValueDecimalAsInteger() {
        issueTracker.reset();
        Form form = helper.buildASTFromFile("src/input/ql/correct/integerValueDecimal.ql", formBuilder);

        evaluator.start(form);
        issueTracker.reset();

        Evaluatable evaluatable = evaluator.getQuestionValue("decimalValue");

        assertEquals("4", evaluatable.getValue().toString());
    }

    @Test
    public void shouldStoreIntegerValueDecimalAsDecimal() {
        issueTracker.reset();
        Form form = helper.buildASTFromFile("src/input/ql/correct/integerValueDecimal2.ql", formBuilder);

        evaluator.start(form);
        issueTracker.reset();

        Evaluatable evaluatable = evaluator.getQuestionValue("decimalValue");

        assertEquals("4.0", evaluatable.getValue().toString());
    }

    @Test
    public void shouldStoreDecimal() {
        issueTracker.reset();
        Form form = helper.buildASTFromFile("src/input/ql/correct/simpleDecimal.ql", formBuilder);

        evaluator.start(form);
        issueTracker.reset();

        Evaluatable evaluatable = evaluator.getQuestionValue("value");

        assertEquals(3.999, evaluatable.getValue());
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
        //TODO write constructors for the integer class that can take in decimals, check whether this is a bug in the typechecker
    }

}