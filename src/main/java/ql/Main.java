package ql;

import ql.ast.FormNode;
import ql.gui.FormView;
import ql.parser.ASTBuilder;
import ql.validator.Validator;

public class Main {
    public static void main(String[] args) {
        String fileName = "src/input/ql/formIf.ql";

        ASTBuilder astBuilder = new ASTBuilder();
        FormNode form = astBuilder.buildASTFromFile(fileName);

        Validator validator = new Validator();
        if (!validator.passesTypeChecks(form)) {
            System.err.println("Form not passing type checks.");
        }

        FormView formViewer = new FormView();
        formViewer.start(form);
    }
}