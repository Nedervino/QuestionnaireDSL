package ql.ast;


import ql.ast.expressions.ExprNode;
import ql.ast.expressions.BinOpNode;
import ql.ast.expressions.OpSymNode;
import ql.ast.expressions.UnOpNode;
import ql.ast.values.*;
import ql.ast.statements.*;

public class ASTVisitor<T> {

    public T visit(ASTNode node) {
        return node.accept(this);
    }

    public T visitChildren(ASTNode node) {
        T result = this.defaultResult();
        int n = node.getChildCount();

        for(int i = 0; i < n; ++i) {
            ASTNode c = node.getChild(i);
            T childResult = c.accept(this);
            result = this.aggregateResult(result, childResult);
        }

        return result;
    }

    protected T aggregateResult(T aggregate, T nextResult) {
        return nextResult;
    }

    protected T defaultResult() {
        return null;
    }

    public T visitAssignment(AssignmentNode node){
        return visitChildren(node);
    }

    public T visitDeclaration(DeclarationNode node){
        return visitChildren(node);
    }

    public T visitIfStatement(IfStatementNode node){
        return visitChildren(node);
    }

    public T visitExpr(ExprNode node){
        return visitChildren(node);
    }

    public T visitID(IDNode node){
        return visitChildren(node);
    }

    public T visitQuestion(QuestionNode node){
        return visitChildren(node);
    }

    public T visitComputedQuestion(ComputedQuestionNode node){
        return visitChildren(node);
    }

    public T visitType(TypeNode node){
        return visitChildren(node);
    }

    public T visitOpSym(OpSymNode node) {
        return visitChildren(node);
    }

    public T visitForm(FormNode node) {
        return visitChildren(node);
    }

    public T visitBinOp(BinOpNode node) {
        return visitChildren(node);
    }

    public T visitUnOp(UnOpNode node) {
        return visitChildren(node);
    }

    public T visitVal(ValNode node) {
        return visitChildren(node);
    }
}
