package ql.validator.checkers;

import issuetracker.Error;
import issuetracker.Warning;
import ql.ast.Form;
import qls.ast.Stylesheet;

import java.util.List;

public interface Checker {

    //TODO: Handle allow for stylesheet parameter

    boolean passesTests();

    List<Error> getErrors();

    List<Warning> getWarnings();

    void logErrors();

    void logWarnings();

}