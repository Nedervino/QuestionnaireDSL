package ql.ast.visitors;

import ql.ast.statements.ComputedQuestionNode;
import ql.ast.statements.IfStatementNode;
import ql.ast.statements.QuestionNode;

public interface StatementVisitor<T> {

    T visit(IfStatementNode ifStatement);

    T visit(QuestionNode question);

    T visit(ComputedQuestionNode computedQuestion);
}
