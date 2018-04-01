package ql.validator.checkers;

import issuetracker.Error;
import issuetracker.Warning;

import java.util.List;

public interface Checker<T> {

    //TODO: Handle allow for stylesheet parameter

    boolean passesTests(T ast);

    List<Error> getErrors();

    List<Warning> getWarnings();

    void logErrors();

    void logWarnings();

}