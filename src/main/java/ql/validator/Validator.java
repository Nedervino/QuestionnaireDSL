package ql.validator;

import ql.ast.Form;

public class Validator {

    private QuestionDuplicationChecker questionDuplicationChecker;
    private ExpressionChecker expressionChecker;
    private CyclicDependencyChecker cyclicDependencyChecker;
    private SymbolTable symbolTable;

    public Validator() {
        questionDuplicationChecker = new QuestionDuplicationChecker();
        expressionChecker = new ExpressionChecker();
        cyclicDependencyChecker = new CyclicDependencyChecker();
        symbolTable = new SymbolTable();
    }

    public boolean passesTypeChecks(Form form) {

        //Check reference to undefined questions
        //Check duplicate question declarations with different types
        //Check duplicate labels (Print warning instead of exception)
        if (!questionDuplicationChecker.passesTests(form, symbolTable)) {
            return false;
        }

        //Check conditions that are not of the type boolean
        //Check operands of invalid type to operators
        if (!expressionChecker.passesTests(form, symbolTable)) {
            return false;
        }

        //Check cyclic dependencies between questions
        if (!cyclicDependencyChecker.passesTests(form)) {
            return false;
        }

        return true;
    }

}
