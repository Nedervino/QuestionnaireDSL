package ql.gui;

import org.antlr.v4.runtime.tree.TerminalNode;
import ql.QLBaseVisitor;
import ql.QLParser;


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

    //An assignment comes in two forms: "declaration" assignment and "an already existing variable" assignment.
    //Using the visitor pattern we let T be a variable name. This way, an output or input only has to sift through which children were none,
    //and pass the correct varname to the formView. We only need to use the leftHand part of the equals sign for assignments,
    //so we can just visit the first of the children.

    //The two calls this pertains to are {@link #visitDeclaration(ctx) visitDeclaration} and {@link #visitTerminal(ctx) visitTerminal}.
    @Override public String visitAssignment(QLParser.AssignmentContext ctx) {

        return visit(ctx.children.get(0));
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
