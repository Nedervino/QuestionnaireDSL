package ql.gui;

import ql.ast.FormNode;

import java.awt.*;

public class FormElement extends GUIElement {

    FormNode node;

    public FormElement(FormNode node, int yLoc) {
        super(yLoc);
        this.node = node;
    }

    @Override
    public void render(Graphics g) {
        g.drawString(node.getId(), 30, yLoc);
        yLoc +=30;
    }

}
