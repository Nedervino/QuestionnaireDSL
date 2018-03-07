package ql.gui;

import ql.ast.Form;
import ql.ast.statements.ComputedQuestion;
import ql.ast.statements.IfElseStatement;
import ql.ast.statements.IfStatement;
import ql.ast.statements.Question;
import ql.ast.visitors.FormVisitor;
import ql.ast.visitors.StatementVisitor;

public class RenderVisitor implements FormVisitor<Void>, StatementVisitor<Void> {

        FormViewer formViewer;
        int pointer = 100;

        public RenderVisitor(FormViewer formViewer){
            this.formViewer = formViewer;
        }

        @Override public Void visit(Form node) {
            GUIElement element = new FormElement(node, pointer);
            formViewer.addElement(element);
            pointer+=element.height;
            return null;
        }

    @Override
    public Void visit(IfStatement ifStatement) {
        return null;
    }

    @Override
    public Void visit(IfElseStatement ifElseStatement) {
        return null;
    }

    @Override public Void visit(Question node) {
            GUIElement element = new QuestionElement(node, pointer, formViewer, formViewer.evaluator);
            formViewer.addElement(element);
            pointer+=element.height;
            return null;
        }

        @Override public Void visit(ComputedQuestion node) {
            GUIElement element = new ComputedQuestionElement(node, pointer, formViewer.evaluator);
            formViewer.addElement(element);
            pointer+=element.height;
            return null;
        }

}
