package qls.parser;

import org.antlr.v4.runtime.ParserRuleContext;
import ql.QLParser;
import ql.ast.Form;
import ql.ast.SourceLocation;
import ql.ast.expressions.Variable;
import ql.ast.statements.Statement;
import ql.ast.types.*;
import qls.QLSBaseVisitor;
import qls.QLSParser;
import qls.ast.ASTNode;
import qls.ast.Page;
import qls.ast.Stylesheet;

import java.util.ArrayList;
import java.util.List;

public class ASTConstructionVisitor extends QLSBaseVisitor<ASTNode> {

    @Override
    public ASTNode visitStylesheet(QLSParser.StylesheetContext ctx) {
        String formId = ctx.IDENTIFIER().getText();
        List<Page> pages = new ArrayList();
        // return new Stylesheet(formId, pages, null);
        return null;
    }

    // @Override
    // public ASTNode visitBooleanType(QLSParser.BooleanTypeContext ctx) {
    //     return new BooleanType(getSourceLocation(ctx));
    // }
    //
    // @Override
    // public ASTNode (QLSParser.IntegerTypeContext ctx) {
    //     return new IntegerType(getSourceLocation(ctx));
    // }
    //
    // @Override
    // public ASTNode (QLSParser.StringTypeContext ctx) {
    //     return new StringType(getSourceLocation(ctx));
    // }
    //
    // @Override
    // public ASTNode visitDecimalType(QLSParser.DecimalTypeContext ctx) {
    //     return new DecimalType(getSourceLocation(ctx));
    // }
    //
    // @Override
    // public ASTNode visitDateType(QLSParser.DateTypeContext ctx) {
    //     return new DateType(getSourceLocation(ctx));
    // }
    //
    // @Override
    // public ASTNode visitMoneyType(QLSParser.MoneyTypeContext ctx) {
    //     return new MoneyType(getSourceLocation(ctx));
    // }
    //
    // public SourceLocation getSourceLocation(ParserRuleContext ctx) {
    //     return new SourceLocation(ctx.start.getLine(), ctx.start.getCharPositionInLine());
    // }
}

