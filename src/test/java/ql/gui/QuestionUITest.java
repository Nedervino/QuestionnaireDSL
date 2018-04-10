package ql.gui;

import org.junit.Test;
import ql.BaseQlTest;
import ql.ast.Form;
import ql.gui.uicomponents.FormUI;

public class QuestionUITest extends BaseQlTest {

    @Test
    public void canDisplayAllNonComputedQuestionTypes() {
        Form form = createForm("src/input/ql/correct/gui/allQuestionTypes.ql");
        FormUI formUI = new FormUIFactory().getFormUI(form);
        formUI.display();
    }

    @Test
    public void canDisplayAllComputedQuestionTypes() {
        Form form = createForm("src/input/ql/correct/gui/allComputedQuestionTypes.ql");
        FormUI formUI = new FormUIFactory().getFormUI(form);
        formUI.display();
    }

}