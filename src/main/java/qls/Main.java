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

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Main {

    public static void main(String[] args) {

        //TODO: Apply same package structure as for ql package.
        //TODO: pass file (non-string) instead of filecontents to formbuilder

        outputInDialog();

        String qlFileName = "src/input/qls/correct/taxOfficeExample.ql";
        String qlFile = new FileScanner().loadFile(qlFileName);

        FormBuilder formBuilder = new FormBuilder();
        Form form = formBuilder.createForm(qlFile);

        String qlsFileName = "src/input/qls/correct/taxOfficeExample.qls";
        String qlsFile = new FileScanner().loadFile(qlsFileName);

        StylesheetBuilder stylesheetBuilder = new StylesheetBuilder();
        Stylesheet stylesheet = stylesheetBuilder.createStylesheet(qlsFile);

        if (!FormValidator.passesChecks(form)) {
            System.out.println("Form not passing validation. See error logs for more details");
            System.exit(1);
        }

        if (!StylesheetValidator.passesChecks(stylesheet)) {
            System.out.println("Stylesheet not passing validation. See error logs for more details");
            System.exit(1);
        }

        FormUI formUI = new FormUIFactory().getFormUI(form);
        formUI.display();
    }

    public static void outputInDialog() {
        PrintStream printStream = new PrintStream(new ByteArrayOutputStream()) {
            public void println(String message){
                JOptionPane.showMessageDialog(null, message);
            }
        };
        System.setOut(printStream);
    }

}
