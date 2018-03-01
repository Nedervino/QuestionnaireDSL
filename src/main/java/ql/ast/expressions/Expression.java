package ql.ast.expressions;

import ql.ast.ASTNode;
import ql.ast.ASTVisitor;
import ql.ast.visitors.ExpressionVisitor;
import ql.ast.visitors.StatementVisitor;

public abstract class Expression extends ASTNode {

    public abstract <T> T accept(ExpressionVisitor<T> visitor);

}
