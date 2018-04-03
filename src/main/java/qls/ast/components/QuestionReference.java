package qls.ast.components;

import ql.ast.SourceLocation;
import qls.ast.visitors.ComponentVisitor;
import qls.ast.widgets.WidgetType;

public class QuestionReference extends Component {

    String questionId;
    WidgetType widgetType;

    public QuestionReference(String questionId, WidgetType widgetType, SourceLocation sourceLocation) {
        super(sourceLocation);
        this.questionId = questionId;
        this.widgetType = widgetType;
    }

    @Override
    public <T> T accept(ComponentVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
