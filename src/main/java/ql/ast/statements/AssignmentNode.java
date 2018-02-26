package ql.ast.statements;


import ql.ast.ASTNode;
import ql.ast.ASTVisitor;
import ql.ast.expressions.ExprNode;

public class AssignmentNode extends ASTNode {

    public String id;
    public String type;
    public ExprNode expr;

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitAssignment(this);
    }

}
