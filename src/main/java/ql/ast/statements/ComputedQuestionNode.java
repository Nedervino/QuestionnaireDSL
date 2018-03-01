package ql.ast.statements;

import ql.ast.expressions.Expression;
import ql.ast.types.Type;
import ql.ast.visitors.StatementVisitor;

public class ComputedQuestionNode extends QuestionNode {

    private Expression expr;

    public ComputedQuestionNode(String id, String label, Type type, Expression expr) {
        super(id, label, type);
        this.expr = expr;
    }

    public Expression getExpr() {
        return expr;
    }

    @Override
    public <T> T accept(StatementVisitor<T> visitor){
        return visitor.visit(this);
    }
}
