package ql.ast;


import ql.ast.expressions.*;
import ql.ast.expressions.binary.BinOpNode;
import ql.ast.expressions.binary.OpSymHelperNode;
import ql.ast.expressions.unary.UnOpNode;
import ql.ast.expressions.values.IDNode;
import ql.ast.expressions.values.ValNode;
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

    public T visitDeclaration(DeclarationNode node){
        return visitChildren(node);
    }

    public T visitIfStatement(IfStatementNode node){
        return visitChildren(node);
    }

    public T visitExpr(Expression node){
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

    public T visitOpSym(OpSymHelperNode node) {
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
