package qls.validator.checkers;

import issuetracker.Error;
import issuetracker.Warning;
import ql.ast.Form;
import ql.validator.checkers.Checker;
import qls.ast.Stylesheet;

import java.util.List;

public class QuestionReferenceChecker implements Checker<Stylesheet>{
    @Override
    public boolean passesTests(Stylesheet stylesheet) {
        return false;
    }

    @Override
    public List<Error> getErrors() {
        return null;
    }

    @Override
    public List<Warning> getWarnings() {
        return null;
    }

    @Override
    public void logErrors() {

    }

    @Override
    public void logWarnings() {

    }
}
