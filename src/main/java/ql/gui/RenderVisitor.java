package ql.gui;

import ql.ast.BaseASTVisitor;
import ql.ast.Form;
import ql.ast.statements.ComputedQuestion;
import ql.ast.statements.Question;

public class RenderVisitor extends BaseASTVisitor<String> {

    FormView formView;

    public RenderVisitor(FormView formView) {
        this.formView = formView;
    }

    @Override
    public String visit(Form form) {
        //Identify the form name
        String formName = form.getFormId();

        formView.addElement(formName, new GUIElement());
        return super.visit(form);
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
