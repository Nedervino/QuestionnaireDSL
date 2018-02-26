package ql.ast.statements;


import ql.ast.ASTNode;
import ql.ast.ASTVisitor;
import ql.ast.expressions.ExprNode;

public class AssignmentNode extends ASTNode {

    private String id;
    private String type;
    private ExprNode expr;

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitAssignment(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ExprNode getExpr() {
        return expr;
    }

    public void setExpr(ExprNode expr) {
        this.expr = expr;
    }
}
