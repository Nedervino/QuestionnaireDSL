package ql.gui;

import ql.ast.ASTVisitor;
import ql.ast.FormNode;
import ql.ast.statements.ComputedQuestionNode;
import ql.ast.statements.QuestionNode;

public class RenderVisitor extends ASTVisitor {

        FormView formView;
        int pointer = 100;

        public RenderVisitor(FormView formView){
            this.formView = formView;
        }

        @Override public Object visitForm(FormNode node) {
            GUIElement element = new FormElement(node, pointer);
            formView.addElement(element);
            pointer+=element.height;
            super.visitForm(node);
            return null;
        }

        @Override public Object visitQuestion(QuestionNode node) {
            GUIElement element = new QuestionElement(node, pointer, formView);
            formView.addElement(element);
            pointer+=element.height;
            return null;
        }

        @Override public Object visitComputedQuestion(ComputedQuestionNode node) {
            GUIElement element = new ComputedQuestionElement(node, pointer, formView.evaluator);
            formView.addElement(element);
            pointer+=element.height;
            return null;
        }

}
