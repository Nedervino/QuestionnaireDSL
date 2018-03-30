package ql.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FormUI {

    private final List<QuestionUI> questions;
    private JFrame frame;

    public FormUI(List<QuestionUI> questions) {
        this.questions = questions;
    }

    public List<QuestionUI> getQuestions() {
        return new ArrayList<>(questions);
    }

    public void display() {
        JPanel questionContainer = new JPanel();
        for (QuestionUI question : questions) {
            questionContainer.add(question.getComponent());
            questionContainer.add(Box.createRigidArea(new Dimension(0, 40)));

        }
        // JScrollPane scrollPane = new JScrollPane(questionContainer);
        // questionContainer.setPreferredSize(new Dimension(500,
        //         800));

        initialiseFrame(questionContainer);
    }

    private void initialiseFrame(JPanel panel) {
        frame = new JFrame("Form Viewer");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(600, 800);
        frame.add(panel);
        frame.setVisible(true);
    }
}
