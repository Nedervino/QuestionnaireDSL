package ql.ast.statements;

import ql.ast.ASTNode;
import ql.ast.SourceLocation;
import ql.ast.visitors.StatementVisitor;

public abstract class Statement extends ASTNode {

    public Statement(SourceLocation sourceLocation) {
        super(sourceLocation);
    }

    public abstract <T> T accept(StatementVisitor<T> visitor);

}
