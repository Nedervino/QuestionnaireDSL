package ql;


/*TODO AST functionality
    Write methods in these classes which help in identifying certain elements. For example:
        Instead of calling ctx.children.get(2) to get the block of an ifExpr during visitation,
        write a method in the IfExpr class which returns the block.
    Declaration -> IntDecl, StringDecl, etc.
        Instead of having one child nodes in the tree, we create classes which are much closer to the leaves. For example:
            instead of an expression which has an expression, an addition terminal, and an expression,
            and the first expression has a exprNum child, which has a valNum child, which has an ID(terminal) child,
            we just have an addition node with two children, ID and whatever the other expression resolved to.
            we can still use inheritance using a BinaryOperation class as a parent of addition, but no one child parent node
            for BinaryExpression which has addition as the child.
 */

//For the implementation it seems easiest to me start with getting rid of one child nodes by just visiting the only child and returning the leaves,
// like some sort of recursive function. Multi-child nodes are only reduced in special cases, but will most certainly all become their own class.
//This is the next task. By creating classes with getters for each of their contents we're able to navigate the tree without having knowledge of the
//CST's structure and child order.


public class ASTVisitor  extends QLBaseVisitor<ASTNode> {

    @Override public ASTNode visitForm(QLParser.FormContext ctx) { return visitChildren(ctx); }

    @Override public ASTNode visitBlock(QLParser.BlockContext ctx) { return visitChildren(ctx); }

    @Override public ASTNode visitStatement(QLParser.StatementContext ctx) { return visitChildren(ctx); }

    @Override public ASTNode visitInput(QLParser.InputContext ctx) { return visitChildren(ctx); }

    @Override public ASTNode visitDeclaration(QLParser.DeclarationContext ctx) { return visitChildren(ctx); }

    @Override public ASTNode visitOutput(QLParser.OutputContext ctx) { return visitChildren(ctx); }

    @Override public ASTNode visitAssignment(QLParser.AssignmentContext ctx) { return visitChildren(ctx); }

    @Override public ASTNode visitExprIf(QLParser.ExprIfContext ctx) { return visitChildren(ctx); }

    @Override public ASTNode visitElseBlock(QLParser.ElseBlockContext ctx) { return visitChildren(ctx); }

    @Override public ASTNode visitExpr(QLParser.ExprContext ctx) { return visitChildren(ctx); }

    @Override public ASTNode visitExprBool(QLParser.ExprBoolContext ctx) { return visitChildren(ctx); }

    @Override public ASTNode visitCompNum(QLParser.CompNumContext ctx) { return visitChildren(ctx); }

    @Override public ASTNode visitCompNumSym(QLParser.CompNumSymContext ctx) { return visitChildren(ctx); }

    @Override public ASTNode visitCompStr(QLParser.CompStrContext ctx) { return visitChildren(ctx); }

    @Override public ASTNode visitValBool(QLParser.ValBoolContext ctx) { return visitChildren(ctx); }

    @Override public ASTNode visitExprNum(QLParser.ExprNumContext ctx) { return visitChildren(ctx); }

    @Override public ASTNode visitValNum(QLParser.ValNumContext ctx) { return visitChildren(ctx); }

    @Override public ASTNode visitExprStr(QLParser.ExprStrContext ctx) { return visitChildren(ctx); }

    @Override public ASTNode visitValStr(QLParser.ValStrContext ctx) { return visitChildren(ctx); }
    
}
