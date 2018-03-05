package ql.ast;

import ql.ast.statements.Statement;
import ql.ast.visitors.FormVisitor;

import java.util.List;

public class Form extends ASTNode {

    private String formId;
    private List<Statement> statements;

    public Form(String formId, List<Statement> statements) {
        this.formId = formId;
        this.statements = statements;
    }

    public String getFormId() {
        return formId;
    }

    public List<Statement> getStatements() {
        return this.statements;
    }

    public <T> T accept(FormVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
