package qls.ast.components;

import ql.ast.SourceLocation;
import ql.gui.widgets.Widget;

public class Question extends Component {

    String questionId;
    Widget widget;

    public Question(String questionId, Widget widget, SourceLocation sourceLocation) {
        super(sourceLocation);
        this.questionId = questionId;
        this.widget = widget;
    }

}
