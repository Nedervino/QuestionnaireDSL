package qls.gui;

import ql.gui.FormUI;
import ql.gui.QuestionUI;
import qls.ast.Page;
import qls.ast.Stylesheet;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QLSFormUI extends FormUI {

    private final List<PageUI> pages;
    private JFrame frame;

    public QLSFormUI(List<QuestionUI> questions, Stylesheet stylesheet) {
        super(questions);

        pages = stylesheet.getPages().stream()
                .map(page -> new PageUI(page))
                .collect(Collectors.toList());
    }

    @Override
    public void display() {

        JTabbedPane tabbedPane = new JTabbedPane();
        for (PageUI page : pages) {
            tabbedPane.add(page.getTitle(), page.getComponent());
        }

        initialiseFrame(tabbedPane);
    }

    private void initialiseFrame(JComponent component) {
        frame = new JFrame("Form Viewer");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setSize(700, 700);
        frame.add(component);
        frame.setVisible(true);
    }
}
