package main;

import gui.FormUI;
import gui.FormUIFactory;
import gui.v1.FormViewer;
import issuetracker.IssueTracker;
import ql.ast.Form;
import ql.evaluator.Evaluator;
import ql.evaluator.FormEvaluator;
import ql.parser.FormBuilder;
import ql.validator.Validator;
import qls.ast.Stylesheet;
import qls.parser.StylesheetBuilder;

/**
 * This program parses an input file following QL DSL specification, for which it renders a graphical form
 */
public class Main {

    public static void main(String[] args) {

        IssueTracker issueTracker = IssueTracker.getIssueTracker();

        //TODO: pass file (non-string) instead of filecontents to formbuilder

        String qlFileName = "src/input/ql/correct/gui/allComputedQuestionTypes.ql";
        String qlFile = new FileScanner().loadFile(qlFileName);

        FormBuilder formBuilder = new FormBuilder();
        Form form = formBuilder.createForm(qlFile);

        String qlsFileName = "src/input/ql/correct/if.ql";
        String qlsFile = new FileScanner().loadFile(qlsFileName);

        StylesheetBuilder stylesheetBuilder = new StylesheetBuilder();
        // Stylesheet stylesheet = stylesheetBuilder.createForm(qlFile);
        Stylesheet stylesheet = null;

        if (new Validator().passesTypeChecks(form)) {
            System.out.println("Successfully passed all checks");
            FormUI formUI = new FormUIFactory().getFormUI(form);
            formUI.display();
        } else {
            System.err.println("Form not passing validation");
            System.exit(1);
        }

        // if (!issueTracker.hasErrors()) {
        //     FormEvaluator evaluator = new Evaluator();
        //     evaluator.start(form);
        //     FormViewer formViewer = new FormViewer(evaluator);
        //     formViewer.start(form, stylesheet);
        // }
    }

}