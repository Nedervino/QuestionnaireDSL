package ql.ast.types;

import ql.ast.ASTNode;
import ql.ast.visitors.TypeVisitor;

public abstract class Type extends ASTNode {

    public abstract String toString ();

    public abstract <T> T accept(TypeVisitor<T> visitor);

}
