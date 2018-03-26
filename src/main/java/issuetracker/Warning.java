package issuetracker;

import ql.ast.SourceLocation;

public class Warning extends Issue {

    public Warning(SourceLocation sourceLocation, String message) {
        super(sourceLocation, message);
    }

    @Override
    public String getFormattedMessage() {
        return String.format("Warning: %s: %s", sourceLocation.getFormattedLocation(), this.getMessage());
    }

}
