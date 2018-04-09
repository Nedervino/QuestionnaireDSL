package qls.gui.uicomponents;

import ql.environment.Environment;
import qls.ast.components.Section;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

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

    @Override
    protected TitledBorder getBorderWithHeader() {
        TitledBorder border = BorderFactory.createTitledBorder("Section " + section.getSectionId());
        return border;
    }

    public JComponent getComponent() {
        return panel;
    }

}
