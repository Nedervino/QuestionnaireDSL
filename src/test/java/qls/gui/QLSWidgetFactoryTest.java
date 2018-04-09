package qls.gui;

import org.junit.Test;
import ql.ast.Form;
import qls.BaseQlsTest;
import qls.ast.Stylesheet;
import qls.gui.uicomponents.QLSFormUI;

public class QLSWidgetFactoryTest extends BaseQlsTest {


    @Test
    public void canCreateMultipleWidgetsPerType() {
        Form form = createForm("src/input/qls/correct/typeWidgetCombinations.ql");
        Stylesheet stylesheet = createStylesheet("src/input/qls/correct/typeWidgetCombinations.qls");
        QLSFormUI formUI = new QLSFormUIFactory(stylesheet).getFormUI(form);
        formUI.display();
    }

    @Test
    public void canCreateMultipleComputedWidgetsPerType() {
        Form form = createForm("src/input/qls/correct/typeWidgetCombinationsComputed.ql");
        Stylesheet stylesheet = createStylesheet("src/input/qls/correct/typeWidgetCombinations.qls");
        QLSFormUI formUI = new QLSFormUIFactory(stylesheet).getFormUI(form);
        formUI.display();
    }

}