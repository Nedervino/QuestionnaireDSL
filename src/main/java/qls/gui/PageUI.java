package qls.gui;

import qls.ast.Page;
import qls.ast.components.Component;

import javax.swing.*;
import java.util.List;

public class PageUI {

    private final Page page;
    private final JPanel panel;

    public PageUI(Page page) {
        this.page = page;
        panel = new JPanel();
    }

    public JComponent getComponent() {
        return panel;
    }

}
