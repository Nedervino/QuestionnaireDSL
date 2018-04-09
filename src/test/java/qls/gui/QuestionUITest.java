package qls.gui;

import org.junit.Test;
import ql.ast.Form;
import qls.BaseQlsTest;
import qls.ast.Stylesheet;
import qls.gui.uicomponents.QLSFormUI;

public class QuestionUITest extends BaseQlsTest {

    @Test
    public void canDisplayAllNonComputedQuestionTypes() {
        //TODO
        Form form = createForm("src/input/ql/correct/gui/allQuestionTypes.ql");
        Stylesheet stylesheet = createStylesheet("");
        QLSFormUI formUI = new QLSFormUIFactory(stylesheet).getFormUI(form);
        formUI.display();
    }

    @Test
    public void canDisplayAllComputedQuestionTypes() {
        //TODO
        Form form = createForm("src/input/ql/correct/gui/allComputedQuestionTypes.ql");
        Stylesheet stylesheet = createStylesheet("");
        QLSFormUI formUI = new QLSFormUIFactory(stylesheet).getFormUI(form);
        formUI.display();
    }

}
