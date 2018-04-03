package qls.gui;

import ql.ast.statements.Question;
import ql.environment.Environment;
import ql.gui.WidgetFactory;
import ql.gui.widgets.*;
import qls.ast.defaultrules.DefaultWidgetRule;
import qls.ast.visitors.WidgetTypeVisitor;
import qls.ast.widgets.*;

public class QLSWidgetFactory extends WidgetFactory {

    public Widget createWidget(Question inputQuestion, Environment inputEnvironment, DefaultWidgetRule widgetRule) {
        final Question question = inputQuestion;
        final Environment environment = inputEnvironment;


        final boolean isEditable = !environment.questionIsComputed(question.getId());


        if (widgetRule == null) return QLSWidgetFactory.super.createWidget(question, environment);

        return widgetRule.getWidgetType().accept(new WidgetTypeVisitor<Widget>() {
            @Override
            public Widget visit(DefaultType widget) {
                return QLSWidgetFactory.super.createWidget(question, environment);
            }

            @Override
            public Widget visit(CheckboxType widget) {
                return new CheckboxWidget(environment, question, isEditable);
            }

            @Override
            public Widget visit(DropdownType widget) {
                return new DropdownWidget(environment, question, isEditable);
            }

            @Override
            public Widget visit(RadioType widget) {
                return new RadioWidget(environment, question, isEditable);
            }

            @Override
            public Widget visit(SliderType widget) {
                return new SliderWidget(environment, question, isEditable);
            }

            @Override
            public Widget visit(SpinboxType widget) {
                return new SpinboxWidget(environment, question, isEditable);
            }

            @Override
            public Widget visit(TextFieldType widget) {
                return new TextFieldWidget(environment, question, isEditable);
            }
        });

    }


}
