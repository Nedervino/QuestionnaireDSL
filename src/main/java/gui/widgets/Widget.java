package gui.widgets;

import gui.WidgetListener;
import ql.evaluator.values.Evaluatable;

import javax.swing.*;

public interface Widget {

    Evaluatable getValue();

    void setValue();

    JComponent getComponent();

    void setVisible(boolean visible);

    void registerChangeListener(WidgetListener widgetListener);

}