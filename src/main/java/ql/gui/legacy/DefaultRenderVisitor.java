package ql.gui.legacy;

import org.antlr.v4.runtime.tree.TerminalNode;
import ql.QLBaseVisitor;
import ql.QLParser;
import ql.gui.FormView;
import ql.gui.GUIElement;


public class DefaultRenderVisitor extends QLBaseVisitor<String> {

    FormView formView;

    public DefaultRenderVisitor(FormView formView){
        this.formView = formView;
    }

    @Override public String visitForm(QLParser.FormContext ctx) {
        //Identify the form name
        String formName = visit(ctx.children.get(1));

        formView.addElement(formName, new GUIElement());
        return super.visitForm(ctx);
    }

    //Identify the variable name which belongs to this question
    //Find the declaration part of the question
    @Override public String visitQuestion(QLParser.QuestionContext ctx) {
        String varName = visit(ctx.children.get(1));

        formView.addElement(varName, new GUIElement());
        return null;
    }

    @Override public String visitComputedQuestion(QLParser.ComputedQuestionContext ctx) {
        String varName = visit(ctx.children.get(1));

        formView.addElement(varName, new GUIElement());
        return null;
    }

    @Override public String visitDeclaration(QLParser.DeclarationContext ctx) {
        TerminalNode idNode = (TerminalNode) ctx.children.get(0);
        String varName = idNode.getText();
        return varName;
    }

    //Since we will only use visitTerminal for the purpose of getting variable names from identifier nodes and form terminal nodes,
    //and we never call a terminal node when it is not the identifier node, we will simply return the text
    //even though this would return keywords or reserved characters when called on non-identifier nodes.
    @Override public String visitTerminal(TerminalNode node) {
        String varName = node.getText();
        return varName;
    }

    }
