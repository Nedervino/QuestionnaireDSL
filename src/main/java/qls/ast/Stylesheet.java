package qls.ast;

import java.util.List;

public class Stylesheet extends ASTNode {

    private String stylesheetId;
    private List<Page> pages;

    public Stylesheet(String stylesheetId, List<Page> pages, SourceLocation sourceLocation) {
        super(sourceLocation);
        this.stylesheetId = stylesheetId;
        this.pages = pages;
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
