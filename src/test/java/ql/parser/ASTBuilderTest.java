package ql.parser;

import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ql.Helper;
import ql.ast.Form;

public class ASTBuilderTest {

    Helper helper;

    @Rule
    public final ExpectedException exception = ExpectedException.none();
    private ASTBuilder astBuilder;

    @Before
    public void setUp() throws Exception {
        astBuilder = new ASTBuilder();
        helper = new Helper();
    }

    @Test(expected = ParseCancellationException.class)
    public void throwsParseCancellationExceptionForMissingQuote() {
        helper.buildASTFromFile("src/input/ql/incorrect/missingClosingQuote.ql", astBuilder);
    }

    @Test(expected = ParseCancellationException.class)
    public void throwsParseCancellationExceptionForMissingCurlyBracket() {
        helper.buildASTFromFile("src/input/ql/incorrect/missingClosingCurlyBracket.ql", astBuilder);
    }

    @Test
    public void canParseSimpleForm() {
        Form form = helper.buildASTFromFile("src/input/ql/correct/simple.ql", astBuilder);

    }

    @Test
    public void canParseIfBlocks() {
        helper.buildASTFromFile("src/input/ql/correct/if.ql", astBuilder);
    }

    @Test
    public void canParseIfElseBlocks() {
        helper.buildASTFromFile("src/input/ql/correct/ifElse.ql", astBuilder);
    }

    @Test
    public void canParseMultipleNests() {
        helper.buildASTFromFile("src/input/ql/correct/nestedExpressions.ql", astBuilder);
    }

    @Test
    public void canParseSingleLineComments() {
        helper.buildASTFromFile("src/input/ql/correct/singleLineComment.ql", astBuilder);
    }

    @Test
    public void canParseMultiLineComments() {
        helper.buildASTFromFile("src/input/ql/correct/multiLineComment.ql", astBuilder);
    }

    @Test
    public void canParseLogicalOperators() {
        helper.buildASTFromFile("src/input/ql/correct/logicalExpressions.ql", astBuilder);
    }

    @Test
    public void canParseArithmeticOperators() {
        helper.buildASTFromFile("src/input/ql/correct/arithmeticExpressions.ql", astBuilder);
    }

    @Test
    public void canParseComparisonOperators() {
        helper.buildASTFromFile("src/input/ql/correct/comparisonExpressions.ql", astBuilder);
    }

}