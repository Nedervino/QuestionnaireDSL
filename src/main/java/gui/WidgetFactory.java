package gui;

import gui.widgets.CheckboxWidget;
import gui.widgets.RadioWidget;
import gui.widgets.TextFieldWidget;
import gui.widgets.Widget;
import ql.ast.statements.Question;
import ql.ast.types.*;
import ql.ast.visitors.TypeVisitor;
import ql.evaluator.FormEvaluator;

public class WidgetFactory implements TypeVisitor<Widget>{

    private FormEvaluator evaluator;
    private Question question;

    public Widget createWidget(Question question, FormEvaluator evaluator) {
        this.evaluator = evaluator;
        this.question = question;
        return question.getType().accept(this);
    }

    @Override
    public Widget visit(BooleanType booleanType) {
        // return new RadioWidget(evaluator, question);
        return null;
    }

    @Override
    public Widget visit(DecimalType decimalType) {
        // return new TextFieldWidget(evaluator, question);
        return null;
    }

    @Override
    public Widget visit(IntegerType integerType) {
        // return new TextFieldWidget(evaluator, question);
        return null;
    }

    @Override
    public Widget visit(MoneyType moneyType) {
        // return new TextFieldWidget(evaluator, question);
        return null;
    }

    @Override
    public Widget visit(StringType stringType) {
        // return new TextFieldWidget(evaluator, question);
        return null;
    }

    @Override
    public Widget visit(DateType dateType) {
        // return new TextFieldWidget(evaluator, question);
        return null;
    }

    @Override
    public Widget visit(ErrorType errorType) {
        return null;
    }
}
