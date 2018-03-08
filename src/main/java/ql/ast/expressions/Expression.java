package ql.ast.expressions;

import ql.ast.ASTNode;
import ql.ast.SourceLocation;
import ql.ast.visitors.ExpressionVisitor;

public abstract class Expression extends ASTNode {

    public Expression(SourceLocation sourceLocation) {
        super(sourceLocation);
    }

    public abstract <T> T accept(ExpressionVisitor<T> visitor);

}
