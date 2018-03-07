package ql.gui;

import java.awt.*;

public abstract class GUIElement {

    int yLoc;
    int height;

    public GUIElement(int pointer){
        this.yLoc = pointer;
        height = 30;
    }

    public abstract void render(Graphics g);

}
