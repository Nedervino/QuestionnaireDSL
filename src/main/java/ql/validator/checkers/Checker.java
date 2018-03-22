package ql.validator.checkers;

import ql.ast.Form;

public interface Checker {

    boolean passesTests(Form form);

}