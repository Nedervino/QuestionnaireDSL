package gui;

import ql.ast.Form;
import ql.ast.statements.*;
import ql.ast.types.*;
import ql.ast.visitors.FormStatementVisitor;
import ql.ast.visitors.TypeVisitor;
import ql.evaluator.values.ValueBoolean;
import ql.evaluator.values.ValueString;

import javax.swing.*;
import java.awt.event.ActionListener;

public class GUIElementConstructionVisitorForm implements FormStatementVisitor<Void>, TypeVisitor<Void> {

    private FormViewer formViewer;
    private int pointer = 100;
    private QuestionElement newElement;

    public GUIElementConstructionVisitorForm(FormViewer formViewer) {
        this.formViewer = formViewer;
    }

    @Override
    public Void visit(Form node) {
        GUIElement element = new FormElement(node, pointer);
        formViewer.addElement(element);
        pointer += element.getHeight();
        for (Statement statement : node.getStatements()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(IfStatement ifStatement) {
        for (Statement statement : ifStatement.getIfStatements()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(IfElseStatement ifElseStatement) {
        for (Statement statement : ifElseStatement.getIfStatements()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(Question node) {
        newElement = new QuestionElement(node, pointer, formViewer.getEvaluator());
        formViewer.addElement(newElement);
        pointer += newElement.getHeight();
        node.getType().accept(this);
        return null;
    }

    @Override
    public Void visit(ComputedQuestion node) {
        GUIElement element = new ComputedQuestionElement(node, pointer, formViewer.getEvaluator());
        formViewer.addElement(element);
        pointer += element.getHeight();
        return null;
    }

    //TODO use a more dynamic and relative layout. Create a separate pane for each GUIElement. With QuestionElements,
    // stuff the widget and it's label in the pane, so we no longer manually have to track height and such.
    public void createTextWidget(QuestionElement element) {
        JTextField textField = new JTextField();
        textField.setBounds(50, element.getYLocation() + 10, 150, 40);
        textField.setLayout(null);
        formViewer.panel.add(textField);

        ActionListener actionListener = e -> {
            String value = textField.getText();
            element.getEvaluator().setValue(element.getQuestion().getId(), new ValueString(value));
        };
        textField.addActionListener(actionListener);
    }

    public void createCheckWidget(QuestionElement element) {
        JCheckBox checkBox = new JCheckBox();
        checkBox.setBounds(50, element.getYLocation() + 10, 40, 40);
        checkBox.setLayout(null);
        formViewer.panel.add(checkBox);

        ActionListener actionListener = e -> {
            boolean value = checkBox.isSelected();
            element.getEvaluator().setValue(element.getQuestion().getId(), new ValueBoolean(value));
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

    @Override
    public Void visit(ErrorType errorType) {
        return null;
    }
}
