package ql.validator.checkers;

import issuetracker.Error;
import issuetracker.IssueTracker;
import issuetracker.Warning;

import java.util.List;

public abstract class BaseChecker implements Checker {

    protected final IssueTracker issueTracker;

    public BaseChecker() {
        this.issueTracker = IssueTracker.getIssueTracker();
    }

    @Override
    public List<Error> getErrors() {
        return issueTracker.getErrors();
    }

    @Override
    public List<Warning> getWarnings() {
        return issueTracker.getWarnings();
    }
}
