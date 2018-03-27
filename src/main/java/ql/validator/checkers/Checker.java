package ql.validator.checkers;

import issuetracker.Error;
import issuetracker.Warning;
import ql.ast.Form;

import java.util.List;

public interface Checker {

    boolean passesTests(Form form);

    List<Error> getErrors();

    List<Warning> getWarnings();

    void logErrors();

    void logWarnings();

}