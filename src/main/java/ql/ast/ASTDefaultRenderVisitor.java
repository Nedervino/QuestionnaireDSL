package ql.ast;

public class ASTDefaultRenderVisitor extends ASTVisitor<String> {

        // FormView formView;
        //
        // public ASTDefaultRenderVisitor(FormView formView){
        //     this.formView = formView;
        // }
        //
        // @Override public String visitForm(ASTNode node) {
        //     //Identify the form name
        //     String formName = visit(node.children.get(1));
        //
        //     formView.addElement(formName, new GUIElement());
        //     return super.visitForm(node);
        // }
        //
        // //Identify the variable name which belongs to this question
        // //Find the declaration part of the question
        // @Override public String visitQuestion(QLParser.QuestionContext ctx) {
        //     String varName = visit(ctx.children.get(1));
        //
        //     formView.addElement(varName, new GUIElement());
        //     return null;
        // }
        //
        // @Override public String visitComputedQuestion(QLParser.ComputedQuestionContext ctx) {
        //     String varName = visit(ctx.children.get(1));
        //
        //     formView.addElement(varName, new GUIElement());
        //     return null;
        // }
        //
        // //An assignment comes in two forms: "declaration" assignment and "an already existing variable" assignment.
        // //Using the visitor pattern we let T be a variable name. This way, an output or input only has to sift through which children were none,
        // //and pass the correct varname to the formView. We only need to use the leftHand part of the equals sign for assignments,
        // //so we can just visit the first of the children.
        //
        // //The two calls this pertains to are {@link #visitDeclaration(ctx) visitDeclaration} and {@link #visitTerminal(ctx) visitTerminal}.
        // @Override public String visitAssignment(QLParser.AssignmentContext ctx) {
        //
        //     return visit(ctx.children.get(0));
        // }
        //
        //
        // @Override public String visitDeclaration(QLParser.DeclarationContext ctx) {
        //     TerminalNode idNode = (TerminalNode) ctx.children.get(0);
        //     String varName = idNode.getText();
        //     return varName;
        // }

}
