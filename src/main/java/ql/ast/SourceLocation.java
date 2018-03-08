package ql.ast;

public class SourceLocation {

    private int line;
    private int column;

    public SourceLocation(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public int getLine() {
        return this.line;
    }

    public int getColumn() {
        return this.column;
    }

    public String toString() {
        return String.format("(%s, %s)", line, column);
    }
}
