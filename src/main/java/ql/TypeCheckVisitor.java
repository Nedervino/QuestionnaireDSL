package ql;

import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.HashMap;

public class TypeCheckVisitor extends QLBaseVisitor<TypeCheckNode> {

    //TODO visualize tree with each of their types to help in debugging

    HashMap<String, TypeCheckNode.Type> varsDeclared;

    public TypeCheckVisitor(){
        varsDeclared = new HashMap<>();
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

    public static boolean isMoney(String str){
        //TODO Add a distinct function for money.
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

    public TypeCheckNode visitTerminal(TerminalNode node) {

        String content = node.getText();

        TypeCheckNode.Type type = null;

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
                //Could replace this line with typechecknode.type.none
                return this.defaultResult();
            }
        }


        //check whether the non-terminal is an int or some other numeric value.
        if(isInt(content)){
            type = TypeCheckNode.Type.INT;
        }
        else if(isMoney(content)){
            type = TypeCheckNode.Type.INT;
        }
        else if(isFloat(content)){
            type = TypeCheckNode.Type.INT;
        }

        //Keywords with semantic value
        switch (content) {
            case "true":{
                type = TypeCheckNode.Type.BOOLEAN;
                break;
            }
            case "false":{
                type = TypeCheckNode.Type.BOOLEAN;
                break;
            }
            case "boolean": {
                type = TypeCheckNode.Type.NONE;
                break;
            }
            case "money": {
                type = TypeCheckNode.Type.NONE;
                break;
            }
            case "int": {
                type = TypeCheckNode.Type.NONE;
                break;
            }
            case "float": {
                type = TypeCheckNode.Type.NONE;
                break;
            }
            case "string": {
                type = TypeCheckNode.Type.NONE;
                break;
            }
        }

        //String literals have no semantic value
        if(content.substring(0, 1).equals("\"")) {
            type = TypeCheckNode.Type.NONE;
        }


        //Anything else was a var
        if(type == null) {
            if(!varsDeclared.containsKey(content)){
                try {
                    throw new Exception("Variable has not been declared yet: " + content);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            type = varsDeclared.get(content);
        }

        TypeCheckNode tcNode = new TypeCheckNode(type);
        return tcNode;
    }

    @Override
    protected TypeCheckNode defaultResult() {
        TypeCheckNode.Type type = TypeCheckNode.Type.NONE;
        TypeCheckNode node = new TypeCheckNode(type);
        return node;
    }

    @Override
    protected TypeCheckNode aggregateResult(TypeCheckNode aggregate, TypeCheckNode nextResult) {

        TypeCheckNode.Type type = TypeCheckNode.Type.NONE;

        if(aggregate.type == null){
            type = nextResult.type;
        }
        else if(aggregate.type == nextResult.type){
            type = nextResult.type;
        }
        else if(aggregate.type == TypeCheckNode.Type.NONE){
             type = nextResult.type;
        }
        else if(nextResult.type == TypeCheckNode.Type.NONE){
            type = aggregate.type;
        }
        else{
            try {
                throw new Exception("Can't operate on two different types: " + String.valueOf(aggregate.type) + " and " + String.valueOf(nextResult.type));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        TypeCheckNode node = new TypeCheckNode(type);


        return node;
    }

    @Override
    public TypeCheckNode visitDeclaration(QLParser.DeclarationContext ctx) {
        //when visiting a declaration, manually visit the children, such that we have the type. We obtain the var name before visiting,
        //and we can assign the type of the var in the varsDeclared set before we visit the terminal node.
        //This way we don't lookup the var before we finished declaring it.


        //varname
        TerminalNode varNameNode = (TerminalNode) ctx.children.get(0);
        String varName = varNameNode.getText();

        //':' char

        //type
        TerminalNode typeNode = (TerminalNode) ctx.children.get(2);
        TypeCheckNode.Type type = TypeCheckNode.getType(typeNode.getText());


        if(varsDeclared.containsKey(varName)){
            try {
                throw new Exception("Variable has been declared before.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        varsDeclared.put(varName, type);

        //TODO most rules will return none. Implement this. So block, ifexpr etc
        return new TypeCheckNode(TypeCheckNode.Type.NONE);
    }


    @Override public TypeCheckNode visitCompNum(QLParser.CompNumContext ctx) {
        return new TypeCheckNode(TypeCheckNode.Type.BOOLEAN);
    }

    @Override public TypeCheckNode visitCompStr(QLParser.CompStrContext ctx) {
        return new TypeCheckNode(TypeCheckNode.Type.BOOLEAN);
    }

    @Override public TypeCheckNode visitForm(QLParser.FormContext ctx) {
        //Only visit the code block for type checking
        return visit(ctx.children.get(2));
    }

    @Override public TypeCheckNode visitStatement(QLParser.StatementContext ctx) {
        return new TypeCheckNode(TypeCheckNode.Type.NONE);
    }

    @Override public TypeCheckNode visitInput(QLParser.InputContext ctx) {
        return new TypeCheckNode(TypeCheckNode.Type.NONE);
    }
}
