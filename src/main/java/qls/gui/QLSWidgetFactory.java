package qls.gui;

import ql.ast.statements.Question;
import ql.environment.Environment;
import ql.gui.WidgetFactory;
import ql.gui.uicomponents.widgets.*;
import qls.ast.defaultrules.DefaultRule;
import qls.ast.visitors.WidgetTypeVisitor;
import qls.ast.widgets.*;

public class QLSWidgetFactory extends WidgetFactory {
    //
    // public Widget createWidget(Question inputQuestion, Environment inputEnvironment) {
    //     return super.createWidget(inputQuestion, inputEnvironment);
    //
    // }

    // public Widget createWidget(Question inputQuestion, Environment inputEnvironment, DefaultRule defaultRule) {
    //     return createWidget(inputQuestion, inputEnvironment, )
    // }

    public Widget createWidget(Question inputQuestion, Environment inputEnvironment, WidgetType widgetType) {
        final Question question = inputQuestion;
        final Environment environment = inputEnvironment;
        final boolean isEditable = !environment.questionIsComputed(question.getId());

        if (widgetType == null) return QLSWidgetFactory.super.createWidget(question, environment);

        return widgetType.accept(new WidgetTypeVisitor<Widget>() {
            @Override
            public Widget visit(DefaultType widget) {
                return QLSWidgetFactory.super.createWidget(question, environment);
            }

            @Override
            public Widget visit(CheckboxType widget) {
                if(widget.getYesLabel() == null) {
                    return new CheckboxWidget(environment, question, isEditable);
                }
                return new CheckboxWidget(environment, question, isEditable, widget.getYesLabel());
            }

            @Override
            public Widget visit(DropdownType widget) {
                if(widget.getYesLabel() == null) {
                    return new DropdownWidget(environment, question, isEditable);
                }
                return new DropdownWidget(environment, question, isEditable, widget.getYesLabel(), widget.getNoLabel());
            }

            @Override
            public Widget visit(RadioType widget) {
                if(widget.getYesLabel() == null) {
                    return new RadioWidget(environment, question, isEditable);
                }
                return new RadioWidget(environment, question, isEditable, widget.getYesLabel(), widget.getNoLabel());
            }

            @Override
            public Widget visit(SliderType widget) {
                return new SliderWidget(environment, question, isEditable, widget.getStart(), widget.getEnd(), widget.getStep());
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
