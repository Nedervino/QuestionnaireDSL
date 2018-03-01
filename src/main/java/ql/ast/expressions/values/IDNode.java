package ql.ast.expressions.values;

import ql.ast.ASTVisitor;
import ql.ast.expressions.Expression;
import ql.ast.visitors.ExpressionVisitor;

public class IDNode extends Expression {

    private String content;

    public IDNode(String content) {
        this.setContent(content);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor){
        return visitor.visit(this);
    }
}
