package main;

import ql.gui.FormUI;
import ql.gui.FormUIFactory;
import ql.ast.Form;
import ql.parser.FormBuilder;
import ql.validator.Validator;
import qls.ast.Stylesheet;
import qls.parser.StylesheetBuilder;

/**
 * This program parses an input file following QL DSL specification, for which it renders a graphical form
 */
public class Main {

    public static void main(String[] args) {

        //TODO: pass file (non-string) instead of filecontents to formbuilder

        String qlFileName = "src/input/ql/correct/if.ql";
        // String qlFileName = "src/input/ql/correct/gui/dependentValue.ql";
        // String qlFileName = "src/input/ql/correct/gui/allComputedQuestions.ql";
        String qlFile = new FileScanner().loadFile(qlFileName);

        FormBuilder formBuilder = new FormBuilder();
        Form form = formBuilder.createForm(qlFile);

        String qlsFileName = "src/input/ql/correct/if.ql";
        String qlsFile = new FileScanner().loadFile(qlsFileName);

        StylesheetBuilder stylesheetBuilder = new StylesheetBuilder();
        // Stylesheet stylesheet = stylesheetBuilder.createForm(qlFile);
        Stylesheet stylesheet = null;

        if (Validator.passesChecks(form)) {
            System.out.println("Successfully passed all checks");
            FormUI formUI = new FormUIFactory().getFormUI(form);
            formUI.display();
        } else {
            System.err.println("Form not passing validation");
            System.exit(1);
        }
    }

}