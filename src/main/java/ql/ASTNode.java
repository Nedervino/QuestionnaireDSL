package ql;

import java.util.LinkedList;

public class ASTNode {

    ASTNode parent;
    LinkedList<ASTNode> children;

    public ASTNode(){
        children = new LinkedList();
    }

}
