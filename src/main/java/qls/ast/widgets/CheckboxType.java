package qls.ast.widgets;

import ql.ast.SourceLocation;

public class CheckboxType extends WidgetType {

    private final String yesLabel;

    public CheckboxType(SourceLocation sourceLocation) {
        super(sourceLocation);
        yesLabel = "Yes";
    }

    public CheckboxType(String yesLabel, SourceLocation sourceLocation) {
        super(sourceLocation);
        this.yesLabel = yesLabel;
    }


    public String getYesLabel() {
        return yesLabel;
    }
}
