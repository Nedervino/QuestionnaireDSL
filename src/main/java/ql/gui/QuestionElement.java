package ql.gui;

import ql.ast.statements.Question;
import ql.evaluator.Evaluator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuestionElement extends GUIElement implements ActionListener {

    Evaluator evaluator;
    Question node;
    JTextArea textArea;

    public QuestionElement(Question node, int yLoc, FormViewer formViewer, Evaluator evaluator) {
        super(yLoc);
        this.node = node;
        height = 40;

        textArea = new JTextArea("Some text\nSome other text");
        textArea.setBounds(50, yLoc+10, 150, yLoc+40);
        formViewer.add(textArea);

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(this);
        formViewer.add(updateButton);
        this.evaluator = evaluator;
    }

    @Override
    public void render(Graphics g) {
        g.drawString(node.getId(), 50, yLocation);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String value = textArea.getText();
        evaluator.update(node, value);
    }
}
