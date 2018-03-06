package ql.ast;

public abstract class ASTNode {

    private int line;
    private int column;

    public ASTNode() {

    }

    public ASTNode(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public int getLine() {
        return this.line;
    }

    public int getColumn() { return this.column;}

}
