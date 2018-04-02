package qls.ast;

import ql.ast.ASTNode;
import ql.ast.SourceLocation;
import qls.ast.components.Component;
import qls.ast.defaultrules.DefaultRule;

import java.util.List;

public class Page extends ASTNode {

    private final String identifier;
    private final List<Component> components;
    private final List<DefaultRule> rules;

    public Page(String identifier, List<Component> components, List<DefaultRule> rules, SourceLocation sourceLocation) {
        super(sourceLocation);
        this.identifier = identifier;
        this.components = components;
        this.rules = rules;
    }

}
