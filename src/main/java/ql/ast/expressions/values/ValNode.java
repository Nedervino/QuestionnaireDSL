package ql.ast.expressions.values;

import ql.ast.ASTVisitor;
import ql.ast.expressions.Expression;
import ql.ast.visitors.ExpressionVisitor;

public class ValNode extends Expression {

    private String content;

    public ValNode(String content) {
        this.setContent(content);
    }

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitVal(this);
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
