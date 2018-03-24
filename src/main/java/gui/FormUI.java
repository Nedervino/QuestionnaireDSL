package gui;

import javax.swing.*;
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
        JPanel panel = new JPanel();
        for (QuestionUI question : questions) {
            panel.add(question.getComponent());
        }
        initialiseFrame(panel);
    }

    private void initialiseFrame(JPanel panel) {
        frame = new JFrame("Form Viewer");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(600, 900);
        frame.add(panel);
        frame.setVisible(true);
    }
}
