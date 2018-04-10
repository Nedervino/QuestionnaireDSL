package qls.gui;

import org.junit.Test;
import ql.ast.Form;
import qls.BaseQlsTest;
import qls.ast.Stylesheet;
import qls.gui.uicomponents.QLSFormUI;

public class QuestionUITest extends BaseQlsTest {

    @Test
    public void canDisplayAllNonComputedQuestionTypesWithoutWidgetRules() {
        Form form = createForm("src/input/ql/correct/gui/allQuestionTypes.ql");
        Stylesheet stylesheet = createStylesheet("src/input/qls/correct/allQuestionTypes.qls");
        QLSFormUI formUI = new QLSFormUIFactory(stylesheet).getFormUI(form);
        formUI.display();
    }

    @Test
    public void canDisplayAllComputedQuestionTypesWithoutWidgetRules() {
        Form form = createForm("src/input/ql/correct/gui/allComputedQuestionTypes.ql");
        Stylesheet stylesheet = createStylesheet("src/input/qls/correct/allQuestionTypes.qls");
        QLSFormUI formUI = new QLSFormUIFactory(stylesheet).getFormUI(form);
        formUI.display();
    }

}
