package ql.typechecker;

import ql.ast.ASTVisitor;

public class TypeCheckVisitor extends ASTVisitor<TypeCheckNode> {

    // HashMap<String, TypeCheckNode.Type> varsDeclared;
    //
    // public TypeCheckVisitor(){
    //     varsDeclared = new HashMap<>();
    // }
    //
    // @Override
    // protected TypeCheckNode defaultResult() {
    //     TypeCheckNode.Type type = TypeCheckNode.Type.NONE;
    //     TypeCheckNode node = new TypeCheckNode(type);
    //     return node;
    // }
    //
    // @Override
    // protected TypeCheckNode aggregateResult(TypeCheckNode aggregate, TypeCheckNode nextResult) {
    //
    //     TypeCheckNode.Type type = TypeCheckNode.Type.NONE;
    //
    //     if(aggregate.type == null){
    //         type = nextResult.type;
    //     }
    //     else if(aggregate.type == nextResult.type){
    //         type = nextResult.type;
    //     }
    //     else if(aggregate.type == TypeCheckNode.Type.NONE){
    //          type = nextResult.type;
    //     }
    //     else if(nextResult.type == TypeCheckNode.Type.NONE){
    //         type = aggregate.type;
    //     }
    //     else{
    //         try {
    //             throw new Exception("Can't operate on two different types: " + String.valueOf(aggregate.type) + " and " + String.valueOf(nextResult.type));
    //         } catch (Exception e) {
    //             e.printStackTrace();
    //         }
    //     }
    //
    //     TypeCheckNode node = new TypeCheckNode(type);
    //
    //     return node;
    // }
    //
    // //when visiting a declaration, manually visit the children, such that we have the type. We obtain the var name before visiting,
    // //and we can assign the type of the var in the varsDeclared set before we visit the terminal node.
    // //This way we don't lookup the var before we finished declaring it.
    // @Override
    // public TypeCheckNode visitDeclaration(DeclarationNode node) {
    //     TypeCheckNode.Type type = TypeCheckNode.getType(node.getType());
    //
    //     if(varsDeclared.containsKey(node.getId())){
    //         try {
    //             throw new Exception("Variable has been declared before.");
    //         } catch (Exception e) {
    //             e.printStackTrace();
    //         }
    //     }
    //     varsDeclared.put(node.getId(), type);
    //
    //     return new TypeCheckNode(TypeCheckNode.Type.NONE);
    // }
    //
    // @Override public TypeCheckNode visitForm(FormNode node) {
    //     List<Statement> sl = node.getStatements();
    //     for(Statement s : sl){
    //         visit(s);
    //     }
    //     return new TypeCheckNode(TypeCheckNode.Type.NONE);
    // }
    //
    // @Override public TypeCheckNode visitBinOp(BinOpNode node) {
    //     //Rewrite this method to reflect the correct visit for each operation type (add, and, lte etc.)
    //     return new TypeCheckNode(TypeCheckNode.Type.NONE);
    // }
    //
    // @Override public TypeCheckNode visitQuestion(QuestionNode node) {
    //     return new TypeCheckNode(TypeCheckNode.Type.NONE);
    // }
}
