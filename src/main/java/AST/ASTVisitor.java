package AST;


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


//TODO concretely: write the custom classes for various node types, and update the visit methods for these node types to construct these custom classes.
//TODO Print the AST

import org.antlr.v4.runtime.tree.TerminalNode;
import ql.QLBaseVisitor;
import ql.QLParser;

public class ASTVisitor  extends QLBaseVisitor<ASTNode> {

    public ASTNode visitTerminal(TerminalNode node) {

        String content = node.getText();

        //TODO return null if the string is irrelevant. skip children which are null in the aggregate result function (and any branch which only refers to an irrelevant node?)
        //return default when the content is a semantically irrelevant String, such as "{" or "if"
        String[] irrelevant = new String[]{
                "form",
                "{",
                "}",
                ":",
                "(",
                ")",
                "if",
                "&&",
                "||",
                "==",
                "!",
                "<",
                ">",
                "<=",
                "=>",
                "+",
                "-",
                "*",
                "/",
                "="
        };

        for(String str : irrelevant){
            if(content.equals(str)){
                return null;
            }
        }


        //check whether the terminal is an int or some other numeric value.
        if(isInt(content)){
            return new IntNode(content);
        }
        else if(isMoney(content)){
            return new IntNode(content);
        }
        else if(isFloat(content)){
            return new IntNode(content);
        }

        //Keywords with semantic value
        switch (content) {
            case "true":{
                return new BooleanNode(content);
            }
            case "false":{
                return new BooleanNode(content);
            }
            case "boolean": {
                return new KeywordNode(content);
            }
            case "money": {
                return new KeywordNode(content);
            }
            case "int": {
                return new KeywordNode(content);
            }
            case "float": {
                return new KeywordNode(content);
            }
            case "string": {
                return new KeywordNode(content);
            }
        }

        //String literal check
        if(content.substring(0, 1).equals("\"")) {
            return new StringLitNode(content);
        }

        //Anything else was a var
        return new IDNode(content);

    }

    public static boolean isFloat(String str) {
        try {
            Double.parseDouble(str);
        }
        catch(NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    //TODO Add a distinct function for money.
    public static boolean isMoney(String str){
        return isFloat(str);
    }

    public static boolean isInt(String str){
        try {
            Integer.parseInt(str);
        }
        catch(NumberFormatException nfe) {
            return false;
        }
        return true;
    }


    @Override
    protected ASTNode defaultResult() {
        return new ASTNode();
    }

    //aggregate is the tree which the parent is constructing
    //nextResult should be the child node
    //This method should add the node to the parent's child list
    @Override
    protected ASTNode aggregateResult(ASTNode aggregate, ASTNode nextResult) {
        aggregate.children.add(nextResult);
        return aggregate;
    }

    @Override
    public ASTNode visitForm(QLParser.FormContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitBlock(QLParser.BlockContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitStatement(QLParser.StatementContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitInput(QLParser.InputContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitDeclaration(QLParser.DeclarationContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitOutput(QLParser.OutputContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitAssignment(QLParser.AssignmentContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitExprIf(QLParser.ExprIfContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitElseBlock(QLParser.ElseBlockContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitExpr(QLParser.ExprContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitExprBool(QLParser.ExprBoolContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitCompNum(QLParser.CompNumContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitCompNumSym(QLParser.CompNumSymContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitCompStr(QLParser.CompStrContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitValBool(QLParser.ValBoolContext ctx) {
        // return visitChildren(ctx);
        return visit(ctx.children.get(0));
    }

    @Override
    public ASTNode visitExprNum(QLParser.ExprNumContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitValNum(QLParser.ValNumContext ctx) {
        // return visitChildren(ctx);
        return visit(ctx.children.get(0));
    }

    @Override
    public ASTNode visitExprStr(QLParser.ExprStrContext ctx) {
        return visitChildren(ctx);
    }

    //A valStr can only contain an actual string assigned as STRLIT, or an ID which refers to a variable.
    @Override
    public ASTNode visitValStr(QLParser.ValStrContext ctx) {
        // return visitChildren(ctx);
        return visit(ctx.children.get(0));
    }
    
}
