package ql.parser;

import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ql.BaseQlTest;

public class FormBuilderTest extends BaseQlTest{

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test(expected = ParseCancellationException.class)
    public void throwsParseCancellationExceptionForMissingQuote() {
        createForm("src/input/ql/incorrect/missingClosingQuote.ql");
    }

    @Test(expected = ParseCancellationException.class)
    public void throwsParseCancellationExceptionForMissingCurlyBracket() {
        createForm("src/input/ql/incorrect/missingClosingCurlyBracket.ql");
    }

    @Test
    public void canParseSimpleForm() {
        createForm("src/input/ql/correct/simple.ql");
    }

    @Test
    public void canParseIfBlocks() {
        createForm("src/input/ql/correct/if.ql");
    }

    @Test
    public void canParseIfElseBlocks() {
        createForm("src/input/ql/correct/ifElse.ql");
    }

    @Test
    public void canParseMultipleNests() {
        createForm("src/input/ql/correct/nestedExpressions.ql");
    }

    @Test
    public void canParseSingleLineComments() {
        createForm("src/input/ql/correct/singleLineComment.ql");
    }

    @Test
    public void canParseMultiLineComments() {
        createForm("src/input/ql/correct/multiLineComment.ql");
    }

    @Test
    public void canParseLogicalOperators() {
        createForm("src/input/ql/correct/logicalExpressions.ql");
    }

    @Test
    public void canParseArithmeticOperators() {
        createForm("src/input/ql/correct/arithmeticExpressions.ql");
    }

    @Test
    public void canParseComparisonOperators() {
        createForm("src/input/ql/correct/comparisonExpressions.ql");
    }

}