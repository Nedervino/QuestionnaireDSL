package gui;

import ql.ast.Form;

import java.awt.*;

public class FormElement extends GUIElement {

    private final Form node;

    public FormElement(Form node, int yLoc) {
        super(yLoc);
        this.node = node;
    }

    @Override
    public void render(Graphics g) {
        g.drawString(node.getFormId(), 30, getYLocation());
    }

}
