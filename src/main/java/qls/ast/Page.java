package qls.ast;

import qls.ast.components.Component;

import java.util.List;

public class Page extends ASTNode {

    private String id;
    private List<Component> components;

    public Page(String id, List<Component> components, SourceLocation sourceLocation) {
        super(sourceLocation);
        this.id = id;
        this.components = components;
    }

}
