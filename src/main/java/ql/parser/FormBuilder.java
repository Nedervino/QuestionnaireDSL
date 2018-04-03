package ql.parser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import ql.QLLexer;
import ql.QLParser;
import ql.ast.Form;
import ql.ast.expressions.Expression;
import ql.ast.statements.Statement;
import ql.utilities.IOHandler;

/**
 * This parses a QL form specification using ANTLR, and converts the resulting CST to a Form AST
 */
public class FormBuilder {

    public static Form createForm(byte[] qlFile) {
        String fileContent = new String(qlFile);
        QLParser parser = createParser(fileContent);

        ASTConstructionVisitor astConstructionVisitor = new ASTConstructionVisitor();
        QLParser.FormContext formContext = parser.form();

        return (Form) astConstructionVisitor.visit(formContext);
    }

    private static QLParser createParser(String input) {
        CharStream charStream = CharStreams.fromString(input);
        QLLexer lexer = new QLLexer(charStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokenStream);

        parser.removeErrorListeners();
        ExceptionErrorListener throwErrorListener = new ExceptionErrorListener();
        parser.addErrorListener(throwErrorListener);

        return parser;
    }

    public static Expression createExpression(String input) {
        QLParser parser = createParser(input);
        ASTConstructionVisitor astConstructionVisitor = new ASTConstructionVisitor();
        QLParser.ExpressionContext expressionContext = parser.expression();
        return (Expression) astConstructionVisitor.visit(expressionContext);
    }

    public static Statement createStatement(String input) {
        QLParser parser = createParser(input);
        ASTConstructionVisitor astConstructionVisitor = new ASTConstructionVisitor();
        QLParser.StatementContext statementContext = parser.statement();
        return (Statement) astConstructionVisitor.visit(statementContext);
    }

}
