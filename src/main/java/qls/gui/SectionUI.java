package qls.gui;

import ql.gui.QuestionUI;
import qls.ast.components.Section;

import javax.swing.*;
import java.util.List;

public class SectionUI {

    private final Section section;
    private final List<QuestionUI> questions;
    private final JPanel panel;

    public SectionUI(Section section, List<QuestionUI> questions) {
        this.section = section;
        this.questions = questions;
        panel = new JPanel();
        for(QuestionUI question : questions) {
            panel.add(question.getComponent());
        }
    }

    public JComponent getComponent() {
        return panel;
    }

}
