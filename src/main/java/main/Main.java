package main;

import ql.ast.Form;
import ql.evaluator.Evaluator;
import ql.gui.FormViewer;
import ql.parser.ASTBuilder;
import ql.validator.Validator;

/**
 * This program parses an input file following QL DSL specification, for which it renders a graphical form
 */
public class Main {

    public static void main(String[] args) {
        String qlFileName = "src/input/ql/correct/if.ql";
        String qlFile = new FileScanner().loadFile(qlFileName);

        ASTBuilder astBuilder = new ASTBuilder();
        Form form = astBuilder.buildASTFromString(qlFile);

        String qlsFileName = "src/input/qls/correct/form1.qls";
        String qlsFile = new FileScanner().loadFile(qlsFileName);

        Validator validator = new Validator();
        if (!validator.passesTypeChecks(form)) {
            System.err.println("Form not passing validation");
            System.exit(1);
        } else {
            System.out.println("Successfully passed all checks");
        }

        Evaluator evaluator = new Evaluator();
        evaluator.start(form);

        FormViewer formViewer = new FormViewer(evaluator);
        formViewer.start(form);
    }

}