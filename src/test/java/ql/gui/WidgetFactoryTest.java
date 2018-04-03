package ql.gui;

import org.junit.Before;
import org.junit.Test;
import ql.BaseQlTest;
import ql.ast.Form;
import ql.ast.statements.Question;
import ql.ast.types.BooleanType;
import ql.ast.types.MoneyType;
import ql.environment.Environment;
import ql.environment.FormEnvironment;
import ql.gui.widgets.RadioWidget;
import ql.gui.widgets.SpinboxWidget;
import ql.gui.widgets.Widget;
import ql.utilities.IOHandler;
import qls.ast.Stylesheet;
import qls.parser.StylesheetBuilder;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class WidgetFactoryTest extends BaseQlTest {

    private Environment environment;

    @Before
    public void setUp() {

        Form form = createForm("src/input/ql/correct/if.ql");
        environment = new FormEnvironment(form);

        StylesheetBuilder stylesheetBuilder = new StylesheetBuilder();
        String qlsFileName = "src/input/qls/correct/form1.qls";
        File qlsFile = IOHandler.loadFile(qlsFileName);
        Stylesheet stylesheet = stylesheetBuilder.createStylesheet(qlsFile);

    }

    @Test
    public void createRadioWidget() {
        WidgetFactory widgetFactory = new WidgetFactory();
        Question question = new Question("hasBoughtHouse", "", new BooleanType(null), null);
        Widget widget = widgetFactory.createWidget(question, environment);

        assertEquals(widget.getClass(), RadioWidget.class);
    }

    @Test
    public void createSpinboxWidget() {
        //TODO combine form and stylesheet such that SpinBoxWidget setting is actually set
        WidgetFactory widgetFactory = new WidgetFactory();
        Question question = new Question("sellingPrice", "", new MoneyType(null), null);
        Widget widget = widgetFactory.createWidget(question, environment);

        assertEquals(widget.getClass(), SpinboxWidget.class);
    }

}