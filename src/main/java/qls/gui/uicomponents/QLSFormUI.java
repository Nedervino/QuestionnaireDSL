package qls.gui.uicomponents;

import ql.environment.Environment;
import ql.gui.uicomponents.FormUI;
import ql.gui.uicomponents.QuestionUI;
import qls.ast.Stylesheet;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

public class QLSFormUI extends FormUI {

    private final List<PageUI> pages;
    private JFrame frame;

    //TODO: Not handling questions
    public QLSFormUI(List<QuestionUI> questions, Stylesheet stylesheet, Environment environment) {
        super(questions);

        pages = stylesheet.getPages().stream()
                .map(page -> new PageUI(page, environment))
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
