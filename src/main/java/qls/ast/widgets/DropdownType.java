package qls.ast.widgets;

import ql.ast.SourceLocation;

public class DropdownType extends WidgetType {


    private final String yesLabel;
    private final String noLabel;

    public DropdownType(SourceLocation sourceLocation) {
        super(sourceLocation);
        yesLabel = "Yes";
        noLabel = "No";
    }

    public DropdownType(String yesLabel, String noLabel, SourceLocation sourceLocation) {
        super(sourceLocation);
        this.yesLabel = yesLabel;
        this.noLabel = noLabel;
    }

    public String getYesLabel() {
        return yesLabel;
    }

    public String getNoLabel() {
        return noLabel;
    }
}
