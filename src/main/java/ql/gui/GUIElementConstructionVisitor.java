package ql.gui;

import ql.ast.Form;
import ql.ast.statements.*;
import ql.ast.types.*;
import ql.ast.visitors.FormVisitor;
import ql.ast.visitors.StatementVisitor;
import ql.ast.visitors.TypeVisitor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIElementConstructionVisitor implements FormVisitor<Void>, StatementVisitor<Void>, TypeVisitor<Void> {

        FormViewer formViewer;
        int pointer = 100;
        QuestionElement newElement;

        public GUIElementConstructionVisitor(FormViewer formViewer){
            this.formViewer = formViewer;
        }

        @Override public Void visit(Form node) {
            GUIElement element = new FormElement(node, pointer);
            formViewer.addElement(element);
            pointer+=element.height;
            for(Statement statement : node.getStatements()){
                statement.accept(this);
            }
            return null;
        }

    @Override
    public Void visit(IfStatement ifStatement) {
        for(Statement statement : ifStatement.getIfStatements()){
            statement.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(IfElseStatement ifElseStatement) {
        for(Statement statement : ifElseStatement.getIfStatements()) {
            statement.accept(this);
        }
        return null;
    }

    @Override public Void visit(Question node) {
        newElement = new QuestionElement(node, pointer, formViewer.evaluator);
        formViewer.addElement(newElement);
        pointer+=newElement.height;
        node.getType().accept(this);
        return null;
    }

    @Override public Void visit(ComputedQuestion node) {
        GUIElement element = new ComputedQuestionElement(node, pointer, formViewer.evaluator);
        formViewer.addElement(element);
        pointer+=element.height;
        return null;
    }

    //TODO use a more dynamic and relative layout. Create a separate pane for each GUIElement. With QuestionElements,
    // stuff the widget and it's label in the pane, so we no longer manually have to track height and such.
    public void createTextWidget(QuestionElement element){
        JTextField textField = new JTextField();
        textField.setBounds(50, element.yLocation+10, 150, 40);
        textField.setLayout(null);
        formViewer.add(textField);

        ActionListener actionListener = e -> {
            String value = textField.getText();
            element.evaluator.update(element.node, value);
        };
        textField.addActionListener(actionListener);
    }

    public void createCheckWidget(QuestionElement element){
        JCheckBox checkBox = new JCheckBox();
        checkBox.setBounds(50, element.yLocation+10, 40, 40);
        checkBox.setLayout(null);
        formViewer.add(checkBox);

        ActionListener actionListener = e -> {
            boolean value = checkBox.isSelected();
            element.evaluator.update(element.node, value);
        };
        checkBox.addActionListener(actionListener);
    }

    @Override
    public Void visit(BooleanType booleanType) {
            createCheckWidget(newElement);
            return null;
    }

    @Override
    public Void visit(DecimalType decimalType) {
        createTextWidget(newElement);
        return null;
    }

    @Override
    public Void visit(IntegerType integerType) {
        createTextWidget(newElement);
        return null;
    }

    @Override
    public Void visit(MoneyType moneyType) {
        createTextWidget(newElement);
        return null;
    }

    @Override
    public Void visit(StringType stringType) {
        createTextWidget(newElement);
        return null;
    }

    @Override
    public Void visit(DateType dateType) {
        createTextWidget(newElement);
        return null;
    }
}
