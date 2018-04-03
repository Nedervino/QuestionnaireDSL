package qls;

import ql.ast.Form;
import ql.gui.FormUI;
import ql.gui.FormUIFactory;
import ql.parser.FormBuilder;
import ql.utilities.IOHandler;
import ql.validator.FormValidator;
import qls.ast.Stylesheet;
import qls.gui.QLSFormUIFactory;
import qls.parser.StylesheetBuilder;
import qls.validator.StylesheetValidator;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * This program parses a form input file following QL DSL specification and stylesheet input file following QLS DSL specification,
 * rendering a graphical questionnaire based on the two inputs
 */
public class Main {

    public static void main(String[] args) {

        //TODO: pass file (non-string) instead of filecontents to formbuilder, shorten main method

        outputInDialog();

        String qlFileName = "src/input/qls/correct/taxOfficeExample.ql";
        String qlFile = IOHandler.loadFile(qlFileName);

        // String qlFile = IOHandler.loadFileUsingDialog(".ql");

        Form form = FormBuilder.createForm(qlFile);

        String qlsFileName = "src/input/qls/correct/taxOfficeExample.qls";
        String qlsFile = IOHandler.loadFile(qlsFileName);

        Stylesheet stylesheet = StylesheetBuilder.createStylesheet(qlsFile);

        if (!FormValidator.passesChecks(form)) {
            System.out.println("Form not passing validation. See error logs for more details");
            System.exit(1);
        }

        if (!StylesheetValidator.passesChecks(stylesheet)) {
            System.out.println("Stylesheet not passing validation. See error logs for more details");
            // System.exit(1);
        }

        FormUI formUI = new QLSFormUIFactory(stylesheet).getFormUI(form);
        formUI.display();
    }

    public static void outputInDialog() {
        PrintStream printStream = new PrintStream(new ByteArrayOutputStream()) {
            public void println(String message) {
                JOptionPane.showMessageDialog(null, message);
            }
        };
        System.setOut(printStream);
    }

}
