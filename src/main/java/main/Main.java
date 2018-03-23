package main;

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

        String qlFileName = "src/input/ql/correct/if.ql";
        String qlFile = new FileScanner().loadFile(qlFileName);

        FormBuilder formBuilder = new FormBuilder();
        Form form = formBuilder.createForm(qlFile);

        String qlsFileName = "src/input/qls/correct/form1.qls";
        String qlsFile = new FileScanner().loadFile(qlsFileName);

        StylesheetBuilder stylesheetBuilder = new StylesheetBuilder();
        // Stylesheet stylesheet = stylesheetBuilder.createForm(qlFile);
        Stylesheet stylesheet = null;

        Validator validator = new Validator();
        if (!validator.passesTypeChecks(form)) {
            System.err.println("Form not passing validation");
            System.exit(1);
        } else {
            System.out.println("Successfully passed all checks");
        }

        FormEvaluator evaluator = new Evaluator();
        evaluator.start(form);

        if (!issueTracker.hasErrors()) {
            FormViewer formViewer = new FormViewer(evaluator);
            formViewer.start(form, stylesheet);
        }
    }

}