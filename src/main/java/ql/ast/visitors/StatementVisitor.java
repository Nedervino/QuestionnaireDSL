package ql.ast.visitors;

import ql.ast.statements.ComputedQuestion;
import ql.ast.statements.IfStatement;
import ql.ast.statements.Question;

public interface StatementVisitor<T> {

    T visit(IfStatement ifStatement);

    T visit(Question question);

    T visit(ComputedQuestion computedQuestion);
}
