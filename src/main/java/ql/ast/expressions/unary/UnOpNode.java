package ql.ast.expressions.unary;

import ql.ast.ASTVisitor;
import ql.ast.expressions.Expression;

public abstract class UnOpNode extends Expression {

    private Expression term;

    public Expression getTerm() {
        return term;
    }

    public void setTerm(Expression first) {
        this.term = term;
    }
}
