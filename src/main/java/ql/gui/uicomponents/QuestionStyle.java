package ql.gui.uicomponents;

import java.awt.*;

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
        color = Color.RED;
        fontsize = 20;
        font = new Font("Comic Sans MS", Font.PLAIN, fontsize);
        width = 200;
        height = 100;
    }

    public QuestionStyle(Color color, Font font, int fontsize, int width, int height) {
        this.color = color;
        this.fontsize = fontsize;
        this.font = font.deriveFont(fontsize);
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
        this.font = font.deriveFont(fontsize);
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