package ql.main;

import ql.ast.Form;
import ql.gui.FormUI;
import ql.gui.FormUIFactory;
import ql.parser.FormBuilder;
import ql.validator.FormValidator;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * This program parses an input file following QL DSL specification, for which it renders a graphical form
 */
public class Main {

    public static void main(String[] args) {

        //TODO: pass file (non-string) instead of filecontents to formbuilder

        PrintStream printStream = new PrintStream(new ByteArrayOutputStream()) {
            public void println(String message){
                JOptionPane.showMessageDialog(null, message);
            }
        };
        System.setOut(printStream);

        // String qlFileName = "src/input/ql/correct/if.ql";
        // String qlFileName = "src/input/ql/correct/ifElse.ql";
        // String qlFileName = "src/input/ql/correct/comparisonExpressions.ql";
        // String qlFileName = "src/input/ql/correct/gui/dependentValue.ql";
        // String qlFileName = "src/input/ql/correct/gui/allComputedQuestionTypes.ql";
        String qlFileName = "src/input/ql/correct/gui/allQuestionTypes.ql";
        String qlFile = new FileScanner().loadFile(qlFileName);

        FormBuilder formBuilder = new FormBuilder();
        Form form = formBuilder.createForm(qlFile);

        if (FormValidator.passesChecks(form)) {
            System.out.println("Successfully passed all checks");
            FormUI formUI = new FormUIFactory().getFormUI(form);
            formUI.display();
        } else {
            System.err.println("Form not passing validation");
            System.exit(1);
        }

    }

}