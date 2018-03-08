package ql.validator;

import ql.ast.Form;
import ql.validator.issuetracker.IssueTracker;

import java.util.logging.Logger;


/**
 * Performs validation for the AST by checking invalid references, expressions, duplications, and cyclic dependencies
 */
public class Validator {

    private final static Logger LOGGER = Logger.getLogger(Validator.class.getName());
    private final IssueTracker issueTracker;
    private final QuestionDuplicationChecker questionDuplicationChecker;
    private final ExpressionChecker expressionChecker;
    private final CyclicDependencyChecker cyclicDependencyChecker;
    private final UndeclaredVariableChecker undeclaredVariableChecker;
    private final SymbolTable symbolTable;


    public Validator() {
        issueTracker = new IssueTracker();
        questionDuplicationChecker = new QuestionDuplicationChecker(issueTracker);
        expressionChecker = new ExpressionChecker(issueTracker);
        cyclicDependencyChecker = new CyclicDependencyChecker(issueTracker);
        undeclaredVariableChecker = new UndeclaredVariableChecker(issueTracker);
        symbolTable = new SymbolTable();
    }

    public boolean passesTypeChecks(Form form) {

        //Check for duplicate question identifiers and labels
        if (!questionDuplicationChecker.passesTests(form, symbolTable)) {
            issueTracker.getErrors().forEach(issue -> LOGGER.severe(issue.toString()));
            return false;
        }

        //Check for references to variables which were never declared.
        if (!undeclaredVariableChecker.passesTests(form, symbolTable)) {
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

        issueTracker.getWarnings().forEach(issue -> LOGGER.warning(issue.toString()));

        return true;
    }

}
