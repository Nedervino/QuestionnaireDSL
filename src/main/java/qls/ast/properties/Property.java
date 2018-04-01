package qls.ast.properties;

import ql.ast.ASTNode;
import ql.ast.SourceLocation;
import qls.ast.visitors.PropertyVisitor;

public abstract class Property extends ASTNode {

    protected Property(SourceLocation sourceLocation) {
        super(sourceLocation);
    }

    public abstract <T> T accept(PropertyVisitor<T> visitor);

}
