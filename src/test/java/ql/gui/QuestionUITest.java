package ql.gui;

import org.junit.Test;
import ql.BaseQlTest;
import ql.ast.Form;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static org.junit.Assert.*;

public class QuestionUITest extends BaseQlTest {

    @Test
    public void canDisplayAllNonComputedQuestionTypes() {
        //TODO
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