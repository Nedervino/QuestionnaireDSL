package ql.validator.checkers;

import issuetracker.Error;
import issuetracker.IssueTracker;
import issuetracker.Warning;

import java.util.List;

public abstract class BaseChecker implements Checker {

    protected final IssueTracker issueTracker;

    public BaseChecker() {
        this.issueTracker = new IssueTracker();
    }

    @Override
    public boolean passesTests() {
        return !issueTracker.hasErrors();
    }

    @Override
    public List<Error> getErrors() {
        return issueTracker.getErrors();
    }

    @Override
    public List<Warning> getWarnings() {
        return issueTracker.getWarnings();
    }

    @Override
    public void logErrors() {
        issueTracker.logErrors();
    }

    @Override
    public void logWarnings() {
        issueTracker.logWarnings();
    }
}
