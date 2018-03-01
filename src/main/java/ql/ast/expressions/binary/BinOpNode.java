package ql.ast.expressions.binary;

import ql.ast.ASTNode;
import ql.ast.ASTVisitor;
import ql.ast.expressions.Expression;

public abstract class BinOpNode extends Expression {

    private Expression first;
    private Expression second;

    public ASTNode getFirst() {
        return first;
    }

    public ASTNode getSecond() {
        return second;
    }
}
