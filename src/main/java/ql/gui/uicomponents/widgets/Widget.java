package ql.gui.uicomponents.widgets;

import ql.environment.values.Value;
import ql.gui.WidgetListener;

import javax.swing.*;

public interface Widget {

    Value getValue();

    void setValue();

    JComponent getComponent();

    void setVisible(boolean visible);

    void setEditable(boolean isEditable);

    void registerChangeListener(WidgetListener widgetListener);

}