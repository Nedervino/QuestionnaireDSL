package ql.validator.issuetracker;

public class Error extends Issue {

    public Error(int line, int column, String message) {
        super(line, column, message);
    }

    @Override
    public String toString() {
        return String.format("Error:(%d, %d) %s", this.line, this.column, this.message);
    }

}
