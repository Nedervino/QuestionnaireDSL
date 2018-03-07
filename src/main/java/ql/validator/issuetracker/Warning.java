package ql.validator.issuetracker;

public class Warning extends Issue {

    public Warning(int line, int column, String message) {
        super(line, column, message);
    }

    @Override
    public String toString() {
        return String.format("Warning:(%d, %d) %s", this.line, this.column, this.message);
    }

}
