package ql.validator.issuetracker;

public abstract class Issue {

    protected final int line;
    protected final int column;
    protected final String message;

    public Issue(int line, int column, String message) {
        this.line = line;
        this.column = column;
        this.message = message;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public String getMessage() {
        return message;
    }

    public abstract String toString();

}