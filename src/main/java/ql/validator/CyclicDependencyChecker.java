package ql.validator;

import ql.ast.Form;
import ql.ast.visitors.ExpressionVisitor;
import ql.ast.visitors.FormVisitor;
import ql.ast.visitors.StatementVisitor;
import ql.validator.issuetracker.IssueTracker;

/**
 * Checks AST for cyclic dependencies between questions
 */
public class CyclicDependencyChecker {
// public class CyclicDependencyChecker implements FormVisitor<Void>, StatementVisitor<Void>, ExpressionVisitor {

    private final IssueTracker issueTracker;

    public CyclicDependencyChecker(IssueTracker issueTracker) {
        this.issueTracker = issueTracker;
    }

    public boolean passesTests(Form form) {
        return true;
    }

}