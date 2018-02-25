package ql.ast.statements;

import ql.ast.ASTNode;
import ql.ast.ASTVisitor;
import ql.ast.ExprNode;

public class ComputedQuestionNode extends ASTNode {

    String label;
    String id;
    String type;
    ExprNode expr;

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitComputedQuestion(this);
    }
}
