package ql.parser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import ql.QLLexer;
import ql.QLParser;
import ql.ast.Form;
import ql.ast.expressions.Expression;
import ql.ast.statements.Statement;

/**
 * This parses a QL input file using ANTLR, and creates a custom AST
 */
public class FormBuilder {

    public Form buildASTFromString(String formContent) {
        QLParser parser = createParser(formContent);

        ASTConstructionVisitor astConstructionVisitor = new ASTConstructionVisitor();
        QLParser.FormContext formContext = parser.form();

        return (Form) astConstructionVisitor.visit(formContext);
    }

    public QLParser createParser(String input) {
        CharStream charStream = CharStreams.fromString(input);
        QLLexer lexer = new QLLexer(charStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokenStream);

        parser.removeErrorListeners();
        ExceptionErrorListener throwErrorListener = new ExceptionErrorListener();
        parser.addErrorListener(throwErrorListener);

        return parser;
    }

    public Expression getExpression(QLParser parser) {
        ASTConstructionVisitor astConstructionVisitor = new ASTConstructionVisitor();
        QLParser.ExpressionContext expressionContext = parser.expression();
        return (Expression) astConstructionVisitor.visit(expressionContext);
    }

    public Statement getStatement(QLParser parser) {
        ASTConstructionVisitor astConstructionVisitor = new ASTConstructionVisitor();
        QLParser.StatementContext statementContext = parser.statement();
        return (Statement) astConstructionVisitor.visit(statementContext);
    }

}
