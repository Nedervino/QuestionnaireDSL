package qls;

import ql.ast.Form;
import ql.gui.FormUI;
import ql.gui.FormUIFactory;
import ql.main.FileScanner;
import ql.parser.FormBuilder;
import ql.validator.FormValidator;
import qls.ast.Stylesheet;
import qls.parser.StylesheetBuilder;
import qls.validator.StylesheetValidator;

public class Main {

    public static void main(String[] args) {

        //TODO: Apply same package structure as for ql package.
        //TODO: pass file (non-string) instead of filecontents to formbuilder

        String qlFileName = "src/input/qls/correct/taxOfficeExample.ql";
        String qlFile = new FileScanner().loadFile(qlFileName);

        FormBuilder formBuilder = new FormBuilder();
        Form form = formBuilder.createForm(qlFile);

        String qlsFileName = "src/input/qls/correct/taxOfficeExample.qls";
        String qlsFile = new FileScanner().loadFile(qlsFileName);

        StylesheetBuilder stylesheetBuilder = new StylesheetBuilder();
        Stylesheet stylesheet = stylesheetBuilder.createStylesheet(qlsFile);

        if (!FormValidator.passesChecks(form)) {
            System.err.println("Form not passing validation");
            System.exit(1);
        }
        System.out.println("Successfully passed all form validations");

        if (!StylesheetValidator.passesChecks(stylesheet)) {
            System.err.println("Stylesheet not passing validation");
            System.exit(1);
        }
        System.out.println("Successfully passed all stylesheet validations");

        FormUI formUI = new FormUIFactory().getFormUI(form);
        formUI.display();
    }

}