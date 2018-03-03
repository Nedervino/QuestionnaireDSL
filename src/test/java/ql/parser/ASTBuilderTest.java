package ql.parser;

import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ASTBuilderTest {

    private ASTBuilder astBuilder;

    @Before
    public void setUp() throws Exception {
       astBuilder = new ASTBuilder();
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test(expected = ParseCancellationException.class)
    public void throwsParseCancellationExceptionForParseErrors() {
        astBuilder.buildAST("src/input/ql/formError.ql");
        // exception.expect(ParseCancellationException.class);
    }

    @Test
    public void canParseSimpleForm() {
        astBuilder.buildAST("src/input/ql/formSimple.ql");
    }

    @Test
    public void canParseIfBlocks() {
        astBuilder.buildAST("src/input/ql/formIf.ql");
    }

    @Test
    public void canParseIfElseBlocks() {
        astBuilder.buildAST("src/input/ql/formIfElse.ql");
    }


}