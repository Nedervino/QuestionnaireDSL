package ql.typechecker;

public class TypeCheckNode {

    public static Type getType(String text) {
        Type type;

        switch (text) {
            case "boolean": {
                type = Type.BOOLEAN;
                break;
            }
            case "money": {
                type = Type.MONEY;
                break;
            }
            case "int": {
                type = Type.INT;
                break;
            }
            case "float": {
                type = Type.FLOAT;
                break;
            }
            case "string": {
                type = Type.STRING;
                break;
            }
            default: {
                type = Type.INVALID;
                break;
            }
        }

        return type;
    }

    enum Type {
        INVALID,
        NONE,
        BOOLEAN,
        STRING,
        INT,
        MONEY,
        FLOAT
    }

    ;

    Type type;

    public TypeCheckNode() {

    }

    public TypeCheckNode(Type type) {
        if (type == null) {
            try {
                throw new Exception("Can't assign null keyword as a Node type");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.type = type;
    }
}
