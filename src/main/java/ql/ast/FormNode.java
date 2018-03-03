package ql.ast;

import ql.ast.statements.Statement;
import ql.ast.visitors.BaseVisitor;

import java.util.List;

public class FormNode extends ASTNode {

    private String formId;
    private List<Statement> statements;

    public FormNode(String formId, List<Statement> statements) {
        this.formId = formId;
        this.statements = statements;
    }

    public String getFormId() {
        return formId;
    }

    public List<Statement> getStatements() {
        return this.statements;
    }

    public <T> T accept(BaseVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
