package qls.ast;

import ql.ast.SourceLocation;
import qls.ast.components.Component;
import ql.ast.ASTNode;

import java.util.List;

public class Page extends ASTNode {

    private final String identifier;
    private final List<Component> components;

    public Page(String identifier, List<Component> components, SourceLocation sourceLocation) {
        super(sourceLocation);
        this.identifier = identifier;
        this.components = components;
    }

}
