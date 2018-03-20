package gui.widgets;

import ql.evaluator.values.Evaluatable;

import javax.swing.*;

public interface Widget {

    Evaluatable getValue();

    JComponent getComponent();

    void setEditable(boolean editable);

    void setVisible(boolean visible);

}
