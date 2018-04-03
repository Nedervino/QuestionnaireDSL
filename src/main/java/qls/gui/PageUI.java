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
import java.util.List;

public class PageUI {

    private final Page page;
    private final JPanel panel;

    public PageUI(Page page) {
        this.page = page;
        panel = new JPanel();
        panel.setBorder(getBorderWithHeader());
        for(Component component : page.getComponents()) {
            component.accept(new ComponentVisitor<Void>() {

                @Override
                public Void visit(Section section) {
                    panel.add(new SectionUI(section).getComponent());
                    return null;
                }

                @Override
                public Void visit(QuestionReference questionReference) {
                    // panel.add(new QuestionUI(environment, ))
                    return null;
                }
            });
        }
    }

    private TitledBorder getBorderWithHeader() {
        TitledBorder border = BorderFactory.createTitledBorder(page.getPageId());
        return border;
    }

    public JComponent getComponent() {
        return panel;
    }

}
