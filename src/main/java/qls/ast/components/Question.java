package qls.ast.components;

import ql.ast.SourceLocation;
import qls.ast.widgets.WidgetType;

public class Question extends Component {

    String questionId;
    WidgetType widgetType;

    public Question(String questionId, WidgetType widgetType, SourceLocation sourceLocation) {
        super(sourceLocation);
        this.questionId = questionId;
        this.widgetType = widgetType;
    }

}
