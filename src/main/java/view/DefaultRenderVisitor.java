package view;

import org.antlr.v4.runtime.tree.TerminalNode;
import ql.QLBaseVisitor;
import ql.QLParser;
import ql.TypeCheckNode;

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

    @Override public String visitInput(QLParser.InputContext ctx) {

        //Identify the variable name which belongs to this question
        //Find the declaration part of the question
        String varName = visit(ctx.children.get(1));

        formView.addElement(varName, new GUIElement());
        return null;
    }

    @Override public String visitOutput(QLParser.OutputContext ctx) {

        //An assignment comes in two forms: "declaration" assignment and "an already existing variable" assignment.
        //Using the visitor pattern we let T be a variable name. This way, an output or input only has to sift through which children were none,
        //and pass the correct varname to the formView. We only need to use the leftHand part of the equals sign for assignments,
        //so we can just visit the first of the children.

        //The two calls this pertains to are visitDeclaration and visitTerminal.
        String varName = visit(ctx.children.get(0));


        formView.addElement(varName, new GUIElement());
        return null;
    }

    @Override public String visitDeclaration(QLParser.DeclarationContext ctx) {
        TerminalNode idNode = (TerminalNode) ctx.children.get(0);
        String varName = idNode.getText();
        return varName;
    }

    @Override public String visitTerminal(TerminalNode node) {
        //Since we will only use visitTerminal for the purpose of getting variable names from identifier nodes and form terminal nodes,
        //and we never call a terminal node when it is not the identifier node, we will simply return the text
        //even though this would return keywords or reserved characters when called on non-identifier nodes.
        String varName = node.getText();
        return varName;
    }

    }
