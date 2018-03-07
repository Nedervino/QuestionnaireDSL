package ql.parser;

import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ql.ast.Form;

public class ASTBuilderTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();
    private ASTBuilder astBuilder;

    @Before
    public void setUp() throws Exception {
        astBuilder = new ASTBuilder();
    }

    @Test(expected = ParseCancellationException.class)
    public void throwsParseCancellationExceptionForMissingQuote() {
        astBuilder.buildASTFromFile("src/input/ql/incorrect/missingClosingQuote.ql");
    }

    @Test(expected = ParseCancellationException.class)
    public void throwsParseCancellationExceptionForMissingCurlyBracket() {
        astBuilder.buildASTFromFile("src/input/ql/incorrect/missingClosingCurlyBracket.ql");
    }

    @Test
    public void canParseSimpleForm() {
        Form form = astBuilder.buildASTFromFile("src/input/ql/correct/simple.ql");

    }

    @Test
    public void canParseIfBlocks() {
        astBuilder.buildASTFromFile("src/input/ql/correct/if.ql");
    }

    @Test
    public void canParseIfElseBlocks() {
        astBuilder.buildASTFromFile("src/input/ql/correct/ifElse.ql");
    }

    @Test
    public void canParseMultipleNests() {
        astBuilder.buildASTFromFile("src/input/ql/correct/nestedExpressions.ql");
    }

    @Test
    public void canParseSingleLineComments() {
        astBuilder.buildASTFromFile("src/input/ql/correct/singleLineComment.ql");
    }

    @Test
    public void canParseMultiLineComments() {
        astBuilder.buildASTFromFile("src/input/ql/correct/multiLineComment.ql");
    }

    @Test
    public void canParseLogicalOperators() {
        astBuilder.buildASTFromFile("src/input/ql/correct/logicalExpressions.ql");
    }

    @Test
    public void canParseArithmeticOperators() {
        astBuilder.buildASTFromFile("src/input/ql/correct/arithmeticExpressions.ql");
    }

    @Test
    public void canParseComparisonOperators() {
        astBuilder.buildASTFromFile("src/input/ql/correct/comparisonExpressions.ql");
    }

}