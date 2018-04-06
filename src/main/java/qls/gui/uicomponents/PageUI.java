package qls.gui.uicomponents;

import ql.environment.Environment;
import qls.ast.Page;
import qls.gui.uicomponents.BaseComponentUI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class PageUI extends BaseComponentUI {

    private final Page page;
    private final JPanel panel;

    public PageUI(Page page, Environment environment) {
        this.page = page;

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(getBorderWithHeader());
        GridBagConstraints constraints = getConstraints();

        placeComponents(panel, page.getComponents(), environment, constraints);
    }



    public String getTitle() {
        return page.getPageId();
    }

    @Override
    protected TitledBorder getBorderWithHeader() {
        TitledBorder border = BorderFactory.createTitledBorder("Page " + page.getPageId());
        return border;
    }

    public JComponent getComponent() {
        return panel;
    }

}
