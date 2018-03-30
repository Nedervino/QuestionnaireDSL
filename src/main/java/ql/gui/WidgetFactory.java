package ql.gui;

import ql.ast.statements.Question;
import ql.ast.types.*;
import ql.ast.visitors.TypeVisitor;
import ql.environment.Environment;
import ql.gui.widgets.*;

public class WidgetFactory implements TypeVisitor<Widget> {

    private Environment environment;
    private Question question;
    private boolean isEditable;

    public Widget createWidget(Question question, Environment environment) {
        isEditable = environment.questionIsComputed(question.getId());
        this.environment = environment;
        this.question = question;
        return question.getType().accept(this);
    }

    @Override
    public Widget visit(BooleanType booleanType) {
        return new RadioWidget(environment, question, isEditable);
    }

    @Override
    public Widget visit(DecimalType decimalType) {
        return new SliderWidget(environment, question, isEditable);
    }

    @Override
    public Widget visit(IntegerType integerType) {
        return new SpinboxWidget(environment, question, isEditable);
    }

    @Override
    public Widget visit(MoneyType moneyType) {
        return new TextFieldWidget(environment, question, isEditable);
    }

    @Override
    public Widget visit(StringType stringType) {
        return new TextFieldWidget(environment, question, isEditable);
    }

    @Override
    public Widget visit(DateType dateType) {
        return new TextFieldWidget(environment, question, isEditable);
    }

    @Override
    public Widget visit(ErrorType errorType) {
        return null;
    }
}
