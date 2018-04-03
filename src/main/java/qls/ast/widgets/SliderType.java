package qls.ast.widgets;

import ql.ast.SourceLocation;
import qls.ast.visitors.WidgetTypeVisitor;

public class SliderType extends WidgetType {

    int start;
    int end;
    int step;

    public SliderType(int start, int end, int step, SourceLocation sourceLocation) {
        super(sourceLocation);
        this.start = start;
        this.end = end;
        this.step = step;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getStep() {
        return step;
    }

    @Override
    public <T> T accept(WidgetTypeVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
