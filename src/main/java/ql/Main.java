package ql;

import ql.ast.FormNode;
import ql.gui.FormView;
import ql.parser.ASTBuilder;
import ql.typechecker.TypeChecker;

import java.io.IOException;

public class Main {
    public static void main (String[] args) {
        String fileName = "src/input/ql/formIf.ql";

        ASTBuilder astBuilder = new ASTBuilder();
        FormNode form = astBuilder.buildAST(fileName);

        TypeChecker typeChecker = new TypeChecker();
        if (!typeChecker.passesTypeChecks(form)) {
            System.err.println("Form not passing type checks.");
        }

        FormView formViewer = new FormView();
        formViewer.start(form);
    }
}