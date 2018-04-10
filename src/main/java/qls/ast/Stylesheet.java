package qls.ast;

import ql.ast.ASTNode;
import ql.ast.SourceLocation;
import qls.ast.visitors.StylesheetPageVisitor;

import java.util.ArrayList;
import java.util.List;

public class Stylesheet extends ASTNode {

    private final String stylesheetId;
    private final List<Page> pages;

    public Stylesheet(String stylesheetId, List<Page> pages, SourceLocation sourceLocation) {
        super(sourceLocation);
        this.stylesheetId = stylesheetId;
        this.pages = pages;
    }

    public String getStylesheetId() {
        return stylesheetId;
    }

    public List<Page> getPages() {
        return new ArrayList<>(this.pages);
    }

    public <T> T accept(StylesheetPageVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
