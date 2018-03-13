package ql.gui;

import java.awt.*;

public abstract class GUIElement {

    int yLocation;
    int height;

    public GUIElement(int pointer) {
        this.yLocation = pointer;
        height = 30;
    }

    public abstract void render(Graphics g);

}
