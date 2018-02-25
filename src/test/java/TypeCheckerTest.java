import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.junit.Before;
import org.junit.Test;
import ql.parser.ASTBuilder;


public class TypeCheckerTest {

    private ASTBuilder formGenerator;

    @Before
    public void setUp() throws Exception {
        formGenerator = new ASTBuilder();
    }

    // @Rule
    // public final ExpectedException exception = ExpectedException.none();

    // @Test
    // public void throwsParseCancellationExceptionForParseErrors() throws ParseCancellationException {
    //     formGenerator.loadFile("src/input/ql/formError.ql");
    //     exception.expect(ParseCancellationException.class);
    // }

    @Test(expected = ParseCancellationException.class)
    public void throwsParseCancellationExceptionForParseErrors() {
        formGenerator.loadFile("src/input/ql/formError.ql");
        // exception.expect(ParseCancellationException.class);
    }

    @Test
    public void canParseSimpleForm() {
        formGenerator.loadFile("src/input/ql/formSimple.ql");
    }

    @Test
    public void canParseIfBlocks() {
        formGenerator.loadFile("src/input/ql/formIf.ql");
    }

    @Test
    public void canParseIfElseBlocks() {
        formGenerator.loadFile("src/input/ql/formIfElse.ql");
    }


}