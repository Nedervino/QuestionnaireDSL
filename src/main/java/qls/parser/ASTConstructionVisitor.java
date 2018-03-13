package qls.parser;

import ql.QLParser;
import ql.ast.Form;
import ql.ast.statements.Statement;
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
    
}

