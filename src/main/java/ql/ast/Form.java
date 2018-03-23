package ql.ast;

import ql.ast.statements.Statement;
import ql.ast.visitors.FormStatementVisitor;

import java.util.ArrayList;
import java.util.List;

public class Form extends ASTNode {

    private final String formId;
    private final List<Statement> statements;

    public Form(String formId, List<Statement> statements, SourceLocation sourceLocation) {
        super(sourceLocation);
        this.formId = formId;
        this.statements = statements;
    }

    public String getFormId() {
        return formId;
    }

    public List<Statement> getStatements() {
        return new ArrayList<>(statements);
    }

    public <T> T accept(FormStatementVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
