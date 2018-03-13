package qls.ast;

import ql.ast.ASTNode;
import ql.ast.SourceLocation;

import java.util.List;

public class Stylesheet extends ASTNode {

    private String stylesheetId;
    private List<Page> pages;

    public Stylesheet(String stylesheetId, List<Page> page, SourceLocation sourceLocation) {
        super(sourceLocation);
        this.stylesheetId = this.stylesheetId;
        this.pages = this.pages;
    }

    public String getStylesheetId() {
        return stylesheetId;
    }

    public List<Page> getPages() {
        return this.pages;
    }

    // public <T> T accept(StylesheetVisitor<T> visitor) {
    //     return visitor.visit(this);
    // }

}
