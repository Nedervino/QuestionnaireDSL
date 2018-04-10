package qls.gui;

import ql.ast.statements.Question;
import ql.environment.Environment;
import ql.gui.WidgetFactory;
import ql.gui.uicomponents.QuestionStyle;
import ql.gui.uicomponents.widgets.*;
import qls.ast.defaultrules.DefaultRule;
import qls.ast.defaultrules.DefaultStyleRule;
import qls.ast.properties.*;
import qls.ast.visitors.PropertyVisitor;
import qls.ast.visitors.WidgetTypeVisitor;
import qls.ast.widgets.*;

public class QLSWidgetFactory extends WidgetFactory {

    public Widget createWidget(Question inputQuestion, Environment inputEnvironment, WidgetType widgetType, DefaultStyleRule rule) {
        final Question question = inputQuestion;
        final Environment environment = inputEnvironment;
        final boolean isEditable = !environment.questionIsComputed(question.getId());

        QuestionStyle style = createStyle(rule);

        if (widgetType == null) return QLSWidgetFactory.super.createWidget(question, environment);

        return widgetType.accept(new WidgetTypeVisitor<Widget>() {
            @Override
            public Widget visit(DefaultType widget) {
                return QLSWidgetFactory.super.createWidget(question, environment);
            }

            @Override
            public Widget visit(CheckboxType widget) {
                if (widget.getYesLabel() == null) {
                    return new CheckboxWidget(environment, question, isEditable);
                }
                return new CheckboxWidget(environment, question, isEditable, widget.getYesLabel(), style);
            }

            @Override
            public Widget visit(DropdownType widget) {
                if (widget.getYesLabel() == null) {
                    return new DropdownWidget(environment, question, isEditable);
                }
                return new DropdownWidget(environment, question, isEditable, widget.getYesLabel(), widget.getNoLabel(), style);
            }

            @Override
            public Widget visit(RadioType widget) {
                if (widget.getYesLabel() == null) {
                    return new RadioWidget(environment, question, isEditable);
                }
                return new RadioWidget(environment, question, isEditable, widget.getYesLabel(), widget.getNoLabel(), style);
            }

            @Override
            public Widget visit(SliderType widget) {
                return new SliderWidget(environment, question, isEditable, widget.getStart(), widget.getEnd(), widget.getStep(), style);
            }

            @Override
            public Widget visit(SpinboxType widget) {
                return new SpinboxWidget(environment, question, isEditable, style);
            }

            @Override
            public Widget visit(TextFieldType widget) {
                return new TextFieldWidget(environment, question, isEditable, style);
            }
        });
    }

    public QuestionStyle createStyle(DefaultStyleRule rule) {
        QuestionStyle style = new QuestionStyle();
        if(rule != null) {
            rule.getStyleProperties().forEach(property -> property.accept(new PropertyVisitor<Void>() {

                @Override
                public Void visit(ColorProperty property) {
                    style.setColor(property.getColor());
                    return null;
                }

                @Override
                public Void visit(FontProperty property) {
                    style.setFont(property.getFont());
                    return null;
                }

                @Override
                public Void visit(FontSizeProperty property) {
                    style.setFontsize(property.getValue());
                    return null;
                }

                @Override
                public Void visit(WidthProperty property) {
                    style.setWidth(property.getValue());
                    return null;
                }

                @Override
                public Void visit(HeightProperty property) {
                    style.setHeight(property.getValue());
                    return null;
                }
            }));
        }
        return style;
    }


}
