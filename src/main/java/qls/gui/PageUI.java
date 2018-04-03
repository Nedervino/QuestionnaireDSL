package qls.gui;

import ql.environment.Environment;
import ql.gui.QuestionUI;
import qls.ast.Page;
import qls.ast.components.Component;
import qls.ast.components.QuestionReference;
import qls.ast.components.Section;
import qls.ast.visitors.ComponentVisitor;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class PageUI {

    private final Page page;
    private final JPanel panel;

    public PageUI(Page page, Environment environment) {
        this.page = page;

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(getBorderWithHeader());
        GridBagConstraints constraints = getConstraints();

        for (Component component : page.getComponents()) {
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

    public String getTitle() {
        return page.getPageId();
    }

    private TitledBorder getBorderWithHeader() {
        TitledBorder border = BorderFactory.createTitledBorder("Page " + page.getPageId());
        return border;
    }

    public JComponent getComponent() {
        return panel;
    }

}
