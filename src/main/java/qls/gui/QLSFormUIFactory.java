package qls.gui;

import ql.ast.Form;
import ql.environment.Environment;
import ql.environment.FormEnvironment;
import ql.gui.FormUIFactory;
import qls.ast.Stylesheet;
import qls.gui.uicomponents.QLSFormUI;

public class QLSFormUIFactory extends FormUIFactory {

    private final Stylesheet stylesheet;

    public QLSFormUIFactory(Stylesheet stylesheet) {
        this.stylesheet = stylesheet;
    }

    @Override
    public QLSFormUI getFormUI(Form form) {
        Environment environment = new FormEnvironment(form);
        environment.evaluate();
        return new QLSFormUI(super.getFormUI(form).getQuestions(), stylesheet, environment);
    }

}
