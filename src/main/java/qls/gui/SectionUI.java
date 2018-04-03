package qls.gui;

import ql.gui.QuestionUI;
import qls.ast.components.QuestionReference;
import qls.ast.components.Section;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.util.ArrayList;
import java.util.List;

public class SectionUI {

    private final Section section;
    private final List<QuestionUI> questions;
    private final JPanel panel;

    public SectionUI(Section section) {
        this.section = section;
        this.questions = getQuestionUIs(section.getQuestionReferences());
        panel = new JPanel();
        panel.setBorder(getBorderWithHeader());
        for(QuestionUI question : questions) {
            panel.add(question.getComponent());
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
