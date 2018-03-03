package ql.ast.expressions;

import ql.ast.ASTNode;
import ql.ast.visitors.ExpressionVisitor;

public abstract class Expression extends ASTNode {

    public abstract <T> T accept(ExpressionVisitor<T> visitor);

}
