package qls.gui.uicomponents;

import ql.environment.Environment;
import ql.gui.uicomponents.QuestionUI;
import qls.ast.components.QuestionReference;
import qls.ast.components.Section;
import qls.gui.uicomponents.BaseComponentUI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SectionUI extends BaseComponentUI {

    private final Section section;
    private final JPanel panel;

    public SectionUI(Section section, Environment environment) {
        this.section = section;
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(getBorderWithHeader());

        GridBagConstraints constraints = getConstraints();

        placeComponents(panel, section.getComponents(), environment, constraints);
    }

    private List<QuestionUI> getQuestionUIs(List<QuestionReference> questionReferences) {
        return new ArrayList<>();
    }

    @Override
    protected TitledBorder getBorderWithHeader() {
        TitledBorder border = BorderFactory.createTitledBorder("Section " + section.getSectionId());
        return border;
    }

    public JComponent getComponent() {
        return panel;
    }

}
