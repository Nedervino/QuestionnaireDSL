package ql.ast;


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

    public T visitBoolean(ValBoolNode node){
        return visitChildren(node);
    }

    public T visitDeclaration(DeclarationNode node){
        return visitChildren(node);
    }

    public T visitExprBool(ExprBoolNode node){
        return visitChildren(node);
    }

    public T visitExprIf(ExprIfNode node){
        return visitChildren(node);
    }

    public T visitExpr(ExprNode node){
        return visitChildren(node);
    }

    public T visitExprNum(ExprNumNode node){
        return visitChildren(node);
    }

    public T visitExprStr(ExprStrNode node){
        return visitChildren(node);
    }

    public T visitID(IDNode node){
        return visitChildren(node);
    }

    public T visitInput(InputNode node){
        return visitChildren(node);
    }

    public T visitInt(ValIntNode node){
        return visitChildren(node);
    }

    public T visitOutput(OutputNode node){
        return visitChildren(node);
    }

    public T visitStrLit(StrLitNode node){
        return visitChildren(node);
    }

    public T visitStr(StrNode node){
        return visitChildren(node);
    }

    public T visitType(TypeNode node){
        return visitChildren(node);
    }

    public T visitOp(OpNode node) {
        return visitChildren(node);
    }

    public T visitComp(CompNode node) {
        return visitChildren(node);
    }

    public T visitForm(FormNode node) {
        return visitChildren(node);
    }

    public T visitBool(ValBoolNode node) {
        return visitChildren(node);
    }
}
