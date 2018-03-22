package ql.ast.visitors;

import ql.ast.Form;
import ql.ast.statements.ComputedQuestion;
import ql.ast.statements.IfElseStatement;
import ql.ast.statements.IfStatement;
import ql.ast.statements.Question;

public interface FormStatementVisitor<T> {

    T visit(Form form);

    T visit(IfStatement ifStatement);

    T visit(IfElseStatement ifElseStatement);

    T visit(Question question);

    T visit(ComputedQuestion computedQuestion);
}
