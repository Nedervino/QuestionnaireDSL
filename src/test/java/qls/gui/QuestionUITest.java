package qls.gui;

import org.junit.Test;
import ql.BaseQlTest;
import ql.ast.Form;
import qls.BaseQlsTest;
import qls.ast.Stylesheet;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
        Form form = createForm("src/input/ql/correct/gui/allComputedQuestionTypes.ql");
        Stylesheet stylesheet = createStylesheet("");
        QLSFormUI formUI = new QLSFormUIFactory(stylesheet).getFormUI(form);
        formUI.display();
    }

}
