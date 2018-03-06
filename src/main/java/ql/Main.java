package ql;

import ql.ast.Form;
import ql.gui.FormView;
import ql.parser.ASTBuilder;
import ql.validator.Validator;

/**
 * This program parses an input file following QL DSL specification, for which it renders a graphical form
 */
public class Main {
    public static void main(String[] args) {
        String fileName = "src/input/ql/formIf.ql";

        ASTBuilder astBuilder = new ASTBuilder();
        Form form = astBuilder.buildASTFromFile(fileName);

        Validator validator = new Validator();
        if (!validator.passesTypeChecks(form)) {
            System.err.println("Form not passing validation");
            System.exit(1);
        } else {
            System.out.println("Successfully passed all checks");
        }

        FormView formViewer = new FormView();
        // formViewer.start(form);
    }
}