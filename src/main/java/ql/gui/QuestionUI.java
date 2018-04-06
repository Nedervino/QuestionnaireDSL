package ql.gui;

import ql.ast.statements.Question;
import ql.environment.Environment;
import ql.environment.EnvironmentListener;
import ql.environment.values.Value;
import ql.gui.widgets.Widget;

import javax.swing.*;
import java.awt.*;

public class QuestionUI implements WidgetListener, EnvironmentListener {

    //TODO: Inconsistent with input widgets
    //TODO: Move Question / environment field from widget to QuestionUI

    private final JLabel label;
    private final Widget widget;
    private final JPanel panel;
    private final Environment environment;
    private final Question question;

    public QuestionUI(Environment environment, Question question, Widget widget) {
        this.question = question;
        this.environment = environment;

        environment.registerChangeListener(this);

        label = new JLabel(question.getLabel());
        this.widget = widget;
        widget.registerChangeListener(this);

        panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.WEST);
        panel.add(widget.getComponent(), BorderLayout.EAST);

        panel.setPreferredSize(new Dimension(600, 50));

        setVisible(isVisible());
    }

    public JComponent getComponent() {
        return panel;
    }

    private boolean isVisible() {
        return environment.questionIsVisible(question.getId());
    }

    public void setVisible(boolean visible) {
        label.setVisible(visible);
        widget.setVisible(visible);
    }

    @Override
    public void onInputValueUpdated(Question question, Value value) {
        environment.setValue(question.getId(), value);
    }

    @Override
    public void onEnvironmentUpdated() {
        setVisible(isVisible());
        widget.setValue();
    }

    public class QuestionStyle {

        private Color color;
        private Font font;
        private int fontsize;
        private int width;
        private int height;

        /**
         * Initialise with default style
         */
        public QuestionStyle() {
            color = Color.BLACK;
            font = UIManager.getDefaults().getFont("Verdana");
            fontsize = 15;
            width = 200;
            height = 50;
        }

        public QuestionStyle(Color color, Font font, int fontsize, int width, int height) {
            this.color = color;
            this.font = font;
            this.fontsize = fontsize;
            this.width = width;
            this.height = height;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public Font getFont() {
            return font;
        }

        public void setFont(Font font) {
            this.font = font;
        }

        public int getFontsize() {
            return fontsize;
        }

        public void setFontsize(int fontsize) {
            this.fontsize = fontsize;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

    }
}
