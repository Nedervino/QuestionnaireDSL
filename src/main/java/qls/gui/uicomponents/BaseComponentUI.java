package qls.gui.uicomponents;

import ql.ast.statements.Question;
import ql.environment.Environment;
import ql.gui.uicomponents.QuestionUI;
import ql.gui.uicomponents.widgets.Widget;
import qls.ast.components.Component;
import qls.ast.components.QuestionReference;
import qls.ast.components.Section;
import qls.ast.visitors.ComponentVisitor;
import qls.gui.QLSWidgetFactory;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;

public abstract class BaseComponentUI {

    protected void placeComponents(JPanel panel, List<Component> components, Environment environment, GridBagConstraints constraints) {
        for (Component component : components) {
            component.accept(new ComponentVisitor<Void>() {

                @Override
                public Void visit(Section section) {
                    panel.add(new SectionUI(section, environment).getComponent(), constraints);
                    return null;
                }

                @Override
                public Void visit(QuestionReference questionReference) {
                    Question question = environment.getQuestion(questionReference.getQuestionId());
                    Widget widget = new QLSWidgetFactory().createWidget(question, environment, questionReference.getWidgetType());
                    panel.add(new QuestionUI(environment, question, widget).getComponent(), constraints);
                    return null;
                }
            });
        }
    }

    protected GridBagConstraints getConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridx = 0;
        return constraints;
    }

    protected abstract TitledBorder getBorderWithHeader();

}