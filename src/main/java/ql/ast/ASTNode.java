package ql.ast;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;

public abstract class ASTNode {

    ArrayList<ASTNode> children;

    public ASTNode(){
        children = new ArrayList<ASTNode>();
    }

    //implementations should call 'visitDeclaration' if they are declarations in the ASTVisitor class.
    //To make sure these methods exist, we will declare a method for each node type in the ASTVisitor class.
    public abstract <T> T accept(ASTVisitor<? extends T> visitor);

    ASTNode getChild(int var1){
        return children.get(var1);
    }

    int getChildCount(){
        return children.size();
    }

}
