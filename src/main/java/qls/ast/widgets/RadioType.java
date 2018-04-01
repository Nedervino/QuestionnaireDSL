package qls.ast.widgets;

import ql.ast.SourceLocation;

public class RadioType extends WidgetType {

    private final String yesLabel;
    private final String noLabel;

    public RadioType(SourceLocation sourceLocation) {
        super(sourceLocation);
        yesLabel = "Yes";
        noLabel = "No";
    }

    public RadioType(String yesLabel, String noLabel, SourceLocation sourceLocation) {
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
