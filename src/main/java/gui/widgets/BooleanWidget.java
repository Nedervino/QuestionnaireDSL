package gui.widgets;

import ql.evaluator.FormEvaluator;
import ql.evaluator.values.Evaluatable;

public class BooleanWidget extends BaseWidget {

    public BooleanWidget(FormEvaluator evaluator, Evaluatable value, String identifier) {
        super(evaluator, value, identifier);
    }
}
