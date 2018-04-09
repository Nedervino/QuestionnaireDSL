package ql.gui;

import ql.ast.statements.Question;
import ql.ast.types.*;
import ql.ast.visitors.TypeVisitor;
import ql.environment.Environment;
import ql.gui.uicomponents.widgets.CheckboxWidget;
import ql.gui.uicomponents.widgets.SpinboxWidget;
import ql.gui.uicomponents.widgets.TextFieldWidget;
import ql.gui.uicomponents.widgets.Widget;

public class WidgetFactory {

    public Widget createWidget(Question inputQuestion, Environment inputEnvironment) {
        final Question question = inputQuestion;
        final Environment environment = inputEnvironment;
        final boolean isEditable = !environment.questionIsComputed(question.getId());

        return question.getType().accept(new TypeVisitor<Widget>() {

            @Override
            public Widget visit(BooleanType booleanType) {
                return new CheckboxWidget(environment, question, isEditable);
            }

            @Override
            public Widget visit(DecimalType decimalType) {
                return new TextFieldWidget(environment, question, isEditable);
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
                throw new IllegalArgumentException();
            }

        });
    }


}
