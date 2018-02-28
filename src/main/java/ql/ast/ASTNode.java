package ql.ast;

import java.util.ArrayList;

public abstract class ASTNode {

    //implementations should call 'visitDeclaration' if they are declarations in the ASTVisitor class.
    //To make sure these methods exist, we will declare a method for each node type in the ASTVisitor class.
    public abstract <T> T accept(ASTVisitor<? extends T> visitor);

}
