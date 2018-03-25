package qls.ast;

public class SourceLocation {

    private final int line;
    private final int column;

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

    public String getFormattedLocation() {
        return String.format("(%s, %s)", line, column);
    }
}
