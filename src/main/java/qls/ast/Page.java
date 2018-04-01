package qls.ast;

import ql.ast.ASTNode;
import ql.ast.SourceLocation;
import qls.ast.components.Component;

import java.util.List;

public class Page extends ASTNode {

    private final String identifier;
    private final List<Component> components;
    // private final List<DefaultWidget> defaultWidgets;
    // private final List<DefaulStyle> defaultStyles;

    public Page(String identifier, List<Component> components, SourceLocation sourceLocation) {
        super(sourceLocation);
        this.identifier = identifier;
        this.components = components;
    }

}
