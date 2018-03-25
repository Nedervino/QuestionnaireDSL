package issuetracker;

import ql.ast.SourceLocation;

public class Error extends Issue {

    public Error(SourceLocation sourceLocation, String message) {
        super(sourceLocation, message);
    }

    @Override
    public String getFormattedMessage() {
        return String.format("Error: %s: %s", this.getSourceLocation().getFormattedLocation(), this.getMessage());
    }

}
