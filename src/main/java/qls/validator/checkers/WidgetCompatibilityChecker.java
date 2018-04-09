package qls.validator.checkers;

import issuetracker.Error;
import issuetracker.Warning;
import ql.ast.Form;
import ql.validator.checkers.BaseChecker;
import qls.ast.Stylesheet;

import java.util.List;

public class WidgetCompatibilityChecker extends BaseChecker {

    public WidgetCompatibilityChecker(Form form, Stylesheet stylesheet) {

    }

    @Override
    public boolean passesTests() {
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
