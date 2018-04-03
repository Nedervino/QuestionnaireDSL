package qls.gui;

import ql.environment.Environment;
import ql.gui.QuestionUI;
import qls.ast.components.Component;
import qls.ast.components.QuestionReference;
import qls.ast.components.Section;
import qls.ast.visitors.ComponentVisitor;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SectionUI {

    private final Section section;
    private final JPanel panel;

    public SectionUI(Section section, Environment environment) {
        this.section = section;
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(getBorderWithHeader());
        GridBagConstraints constraints = getConstraints();

        panel.setBorder(getBorderWithHeader());
        for (Component component : section.getComponents()) {
            component.accept(new ComponentVisitor<Void>() {

                @Override
                public Void visit(Section section) {
                    panel.add(new SectionUI(section, environment).getComponent(), constraints);
                    return null;
                }

                @Override
                public Void visit(QuestionReference questionReference) {
                    panel.add(new QuestionUI(environment, environment.getQuestion(questionReference.getQuestionId())).getComponent(), constraints);
                    return null;
                }
            });
        }
    }

    private GridBagConstraints getConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridx = 0;
        return constraints;
    }

    private List<QuestionUI> getQuestionUIs(List<QuestionReference> questionReferences) {
        return new ArrayList<>();
    }

    private TitledBorder getBorderWithHeader() {
        TitledBorder border = BorderFactory.createTitledBorder("Section " + section.getSectionId());
        return border;
    }

    public JComponent getComponent() {
        return panel;
    }

}
