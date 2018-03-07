package ql.gui;

import ql.ast.statements.QuestionNode;
import ql.evaluator.Evaluator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuestionElement extends GUIElement implements ActionListener {

    Evaluator evaluator;
    QuestionNode node;
    JTextArea textArea;

    public QuestionElement(QuestionNode node, int yLoc, FormView formView) {
        super(yLoc);
        this.node = node;
        height = 40;

        textArea = new JTextArea("Some text\nSome other text");
        textArea.setBounds(50, yLoc+10, 150, yLoc+40);
        formView.add(textArea);

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(this);
        formView.add(updateButton);
    }

    @Override
    public void render(Graphics g) {
        g.drawString(node.getId(), 50, yLoc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String value = textArea.getText();
        evaluator.update(node, value);
    }
}
