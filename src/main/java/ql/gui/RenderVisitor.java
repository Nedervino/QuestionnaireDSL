package ql.gui;

import ql.ast.Form;
import ql.ast.statements.ComputedQuestion;
import ql.ast.statements.IfElseStatement;
import ql.ast.statements.IfStatement;
import ql.ast.statements.Question;
import ql.ast.visitors.FormVisitor;
import ql.ast.visitors.StatementVisitor;

public class RenderVisitor implements FormVisitor<String>, StatementVisitor<String> {

    FormView formView;

    public RenderVisitor(FormView formView) {
        this.formView = formView;
    }

    @Override
    public String visit(Form form) {
        //Identify the form name
        String formName = form.getFormId();

        formView.addElement(formName, new GUIElement());
        // visit(form);
        return null;
    }


    @Override
    public String visit(IfStatement ifStatement) {
        return null;
    }

    @Override
    public String visit(IfElseStatement ifElseStatement) {
        return null;
    }

    //Identify the variable name which belongs to this question
    //Find the declaration part of the question
    @Override
    public String visit(Question question) {
        String varName = question.getId();

        formView.addElement(varName, new GUIElement());
        return null;
    }

    @Override
    public String visit(ComputedQuestion computedQuestionNode) {
        String varName = computedQuestionNode.getId();

        formView.addElement(varName, new GUIElement());
        return null;
    }

}
