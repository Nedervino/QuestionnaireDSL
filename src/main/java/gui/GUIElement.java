package gui;

import java.awt.*;

public abstract class GUIElement {

    private int yLocation;
    private int height;

    public GUIElement(int pointer) {
        this.yLocation = pointer;
        this.height = 30;
    }

    public int getYLocation() {
        return yLocation;
    }

    public void setYLocation(int yLocation) {
        this.yLocation = yLocation;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public abstract void render(Graphics g);

}
