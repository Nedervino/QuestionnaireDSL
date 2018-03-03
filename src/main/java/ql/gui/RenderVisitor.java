package ql.gui;

import ql.ast.ASTVisitor;
import ql.ast.FormNode;
import ql.ast.statements.ComputedQuestionNode;
import ql.ast.statements.QuestionNode;

public class RenderVisitor extends ASTVisitor<String> {

    FormView formView;

    public RenderVisitor(FormView formView) {
        this.formView = formView;
    }

    @Override
    public String visit(FormNode formNode) {
        //Identify the form name
        String formName = formNode.getFormId();

        formView.addElement(formName, new GUIElement());
        return super.visit(formNode);
    }

    //Identify the variable name which belongs to this question
    //Find the declaration part of the question
    @Override
    public String visit(QuestionNode questionNode) {
        String varName = questionNode.getId();

        formView.addElement(varName, new GUIElement());
        return null;
    }

    @Override
    public String visit(ComputedQuestionNode computedQuestionNode) {
        String varName = computedQuestionNode.getId();

        formView.addElement(varName, new GUIElement());
        return null;
    }

}
