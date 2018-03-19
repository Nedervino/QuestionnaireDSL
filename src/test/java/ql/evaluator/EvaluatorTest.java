package ql.evaluator;

import org.junit.Before;
import org.junit.Test;
import ql.Helper;
import ql.ast.Form;
import ql.evaluator.values.Evaluatable;
import ql.parser.FormBuilder;
import ql.validator.ExpressionChecker;
import ql.validator.QuestionDuplicationChecker;
import ql.validator.SymbolTable;
import ql.validator.issuetracker.Error;
import ql.validator.issuetracker.IssueTracker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class EvaluatorTest {

    FormBuilder formBuilder;
    Helper helper;
    FormEvaluator evaluator;
    IssueTracker issueTracker;

    @Before
    public void setUp() throws Exception {
        formBuilder = new FormBuilder();
        helper = new Helper();
        issueTracker = new IssueTracker();
        evaluator = new Evaluator();
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

        Evaluatable evaluatable = evaluator.getQuestionValue("decimalValue");

        assertEquals("3.999", evaluatable.getValue().toString());
    }

}