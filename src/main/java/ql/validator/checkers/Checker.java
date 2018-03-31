package ql.validator.checkers;

import issuetracker.Error;
import issuetracker.Warning;

import java.util.List;

public interface Checker<T> {

    boolean passesTests(T ast);

    List<Error> getErrors();

    List<Warning> getWarnings();

    void logErrors();

    void logWarnings();

}