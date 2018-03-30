package ql.gui;

import ql.gui.widgets.*;
import ql.ast.statements.Question;
import ql.ast.types.*;
import ql.ast.visitors.TypeVisitor;
import ql.evaluator.FormEvaluator;

public class WidgetFactory implements TypeVisitor<Widget> {

    private FormEvaluator evaluator;
    private Question question;
    private boolean isEditable;

    public Widget createWidget(Question question, FormEvaluator evaluator) {
        isEditable = evaluator.questionIsComputed(question.getId());
        this.evaluator = evaluator;
        this.question = question;
        return question.getType().accept(this);
    }

    public boolean isEnabled(FormEvaluator evaluator) {
        return evaluator.questionIsEnabled(question.getId());
    }

    @Override
    public Widget visit(BooleanType booleanType) {
        return new RadioWidget(evaluator, question, isEditable);
    }

    @Override
    public Widget visit(DecimalType decimalType) {
        return new SliderWidget(evaluator, question, isEditable);
    }

    @Override
    public Widget visit(IntegerType integerType) {
        return new SpinboxWidget(evaluator, question, isEditable);
    }

    @Override
    public Widget visit(MoneyType moneyType) {
        return new TextFieldWidget(evaluator, question, isEditable);
    }

    @Override
    public Widget visit(StringType stringType) {
        return new TextFieldWidget(evaluator, question, isEditable);
    }

    @Override
    public Widget visit(DateType dateType) {
        return new TextFieldWidget(evaluator, question, isEditable);
    }

    @Override
    public Widget visit(ErrorType errorType) {
        return null;
    }
}
