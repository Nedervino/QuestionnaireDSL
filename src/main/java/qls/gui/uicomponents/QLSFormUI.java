package qls.gui.uicomponents;

import ql.environment.Environment;
import ql.gui.uicomponents.FormUI;
import qls.ast.Stylesheet;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

public class QLSFormUI extends FormUI {

    private final List<PageUI> pages;

    public QLSFormUI(FormUI form, Stylesheet stylesheet, Environment environment) {
        super(form.getQuestions());

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

}
