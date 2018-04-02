package qls.gui;

import ql.ast.Form;
import ql.gui.FormUIFactory;
import qls.ast.Stylesheet;

public class QLSFormUIFactory extends FormUIFactory {

    private final Stylesheet stylesheet;

    public QLSFormUIFactory(Stylesheet stylesheet) {
        this.stylesheet = stylesheet;
    }

    @Override
    public QLSFormUI getFormUI(Form form) {
        return new QLSFormUI(super.getFormUI(form).getQuestions(), stylesheet);
    }



}
