package qls.ast;

import qls.ast.components.Component;

import java.util.List;

public class Page extends ASTNode {

    private String identifier;
    private List<Component> components;

    public Page(String identifier, List<Component> components, SourceLocation sourceLocation) {
        super(sourceLocation);
        this.identifier = identifier;
        this.components = components;
    }

}
