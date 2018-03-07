package ql.validator;

import ql.ast.Form;


/**
 * Performs validation for the AST by checking invalid references, expressions, duplications, and cyclic dependencies
 */
public class Validator {

    private QuestionDuplicationChecker questionDuplicationChecker;
    private ExpressionChecker expressionChecker;
    private CyclicDependencyChecker cyclicDependencyChecker;
    private UndeclaredVariableChecker undeclaredVariableChecker;
    private SymbolTable symbolTable;

    public Validator() {
        questionDuplicationChecker = new QuestionDuplicationChecker();
        expressionChecker = new ExpressionChecker();
        cyclicDependencyChecker = new CyclicDependencyChecker();
        undeclaredVariableChecker = new UndeclaredVariableChecker();
        symbolTable = new SymbolTable();
    }

    public boolean passesTypeChecks(Form form) {

        //Check for duplicate question identifiers and labels
        if (!questionDuplicationChecker.passesTests(form, symbolTable)) {
            return false;
        }

        //Check for references to variables which were never declared.
        if(!undeclaredVariableChecker.passesTests(form, symbolTable)) {
            return false;
        }

        //Check for reference to undefined questions, non-boolean conditionals, and invalid operand types
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
