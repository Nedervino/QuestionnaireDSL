package qls.gui;

import ql.ast.SourceLocation;
import ql.ast.statements.Question;
import ql.ast.types.BooleanType;
import ql.environment.FormEnvironment;
import ql.gui.QuestionUI;
import qls.ast.components.Component;
import qls.ast.components.QuestionReference;
import qls.ast.components.Section;
import qls.ast.visitors.ComponentVisitor;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.util.ArrayList;
import java.util.List;

public class SectionUI {

    private final Section section;
    private final JPanel panel;

    public SectionUI(Section section) {
        this.section = section;
        panel = new JPanel();
        panel.setBorder(getBorderWithHeader());
        for(Component component : section.getComponents()) {
            component.accept(new ComponentVisitor<Void>() {

                @Override
                public Void visit(Section section) {
                    panel.add(new SectionUI(section).getComponent());
                    return null;
                }

                @Override
                public Void visit(QuestionReference questionReference) {
                    // panel.add(new QuestionUI(new FormEnvironment(), test).getComponent());
                    panel.add(new JLabel(questionReference.getQuestionId()));
                    return null;
                }
            });
        }
    }

    private List<QuestionUI> getQuestionUIs(List<QuestionReference> questionReferences) {
        return new ArrayList<>();
    }

    private TitledBorder getBorderWithHeader() {
        TitledBorder border = BorderFactory.createTitledBorder(section.getSectionId());
        return border;
    }

    public JComponent getComponent() {
        return panel;
    }

}
