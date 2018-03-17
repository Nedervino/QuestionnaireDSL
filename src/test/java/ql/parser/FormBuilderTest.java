package ql.parser;

import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ql.Helper;
import ql.ast.Form;

public class FormBuilderTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();
    Helper helper;
    private FormBuilder formBuilder;

    @Before
    public void setUp() throws Exception {
        formBuilder = new FormBuilder();
        helper = new Helper();
    }

    @Test(expected = ParseCancellationException.class)
    public void throwsParseCancellationExceptionForMissingQuote() {
        helper.buildASTFromFile("src/input/ql/incorrect/missingClosingQuote.ql", formBuilder);
    }

    @Test(expected = ParseCancellationException.class)
    public void throwsParseCancellationExceptionForMissingCurlyBracket() {
        helper.buildASTFromFile("src/input/ql/incorrect/missingClosingCurlyBracket.ql", formBuilder);
    }

    @Test
    public void canParseSimpleForm() {
        Form form = helper.buildASTFromFile("src/input/ql/correct/simple.ql", formBuilder);

    }

    @Test
    public void canParseIfBlocks() {
        helper.buildASTFromFile("src/input/ql/correct/if.ql", formBuilder);
    }

    @Test
    public void canParseIfElseBlocks() {
        helper.buildASTFromFile("src/input/ql/correct/ifElse.ql", formBuilder);
    }

    @Test
    public void canParseMultipleNests() {
        helper.buildASTFromFile("src/input/ql/correct/nestedExpressions.ql", formBuilder);
    }

    @Test
    public void canParseSingleLineComments() {
        helper.buildASTFromFile("src/input/ql/correct/singleLineComment.ql", formBuilder);
    }

    @Test
    public void canParseMultiLineComments() {
        helper.buildASTFromFile("src/input/ql/correct/multiLineComment.ql", formBuilder);
    }

    @Test
    public void canParseLogicalOperators() {
        helper.buildASTFromFile("src/input/ql/correct/logicalExpressions.ql", formBuilder);
    }

    @Test
    public void canParseArithmeticOperators() {
        helper.buildASTFromFile("src/input/ql/correct/arithmeticExpressions.ql", formBuilder);
    }

    @Test
    public void canParseComparisonOperators() {
        helper.buildASTFromFile("src/input/ql/correct/comparisonExpressions.ql", formBuilder);
    }

}