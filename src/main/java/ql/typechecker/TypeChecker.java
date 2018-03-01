package ql.typechecker;

import ql.ast.ASTNode;
import ql.ast.FormNode;

public class TypeChecker {

    public boolean passesTypeChecks(ASTNode form) {

        //Check reference to undefined questions

        //Check duplicate question declarations with different types

        //Check conditions that are not of the type boolean

        //Check operands of invalid type to operators

        //Check cyclic dependencies between questions

        //Check duplicate labels (Print warning instead of exception)

        return true;
    }
}
