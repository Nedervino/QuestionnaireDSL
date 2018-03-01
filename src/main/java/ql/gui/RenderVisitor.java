package ql.gui;

import ql.ast.ASTVisitor;
import ql.ast.FormNode;
import ql.ast.statements.ComputedQuestionNode;
import ql.ast.statements.QuestionNode;

public class RenderVisitor extends ASTVisitor<String> {

        FormView formView;

        public RenderVisitor(FormView formView){
            this.formView = formView;
        }

        @Override public String visitForm(FormNode node) {
            //Identify the form name
            String formName = node.getFormId();

            formView.addElement(formName, new GUIElement());
            return super.visitForm(node);
        }

        //Identify the variable name which belongs to this question
        //Find the declaration part of the question
        @Override public String visitQuestion(QuestionNode node) {
            String varName = node.getId();

            formView.addElement(varName, new GUIElement());
            return null;
        }

        @Override public String visitComputedQuestion(ComputedQuestionNode node) {
            String varName = node.getId();

            formView.addElement(varName, new GUIElement());
            return null;
        }

}
