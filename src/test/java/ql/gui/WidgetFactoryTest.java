package ql.gui;

import issuetracker.IssueTracker;
import org.junit.Before;
import org.junit.Test;
import ql.BaseQlTest;
import ql.ast.Form;
import ql.ast.statements.Question;
import ql.ast.types.BooleanType;
import ql.ast.types.MoneyType;
import ql.environment.Environment;
import ql.environment.FormEnvironment;
import ql.environment.values.BooleanValue;
import ql.gui.widgets.RadioWidget;
import ql.gui.widgets.SpinboxWidget;
import ql.gui.widgets.Widget;
import qls.ast.Stylesheet;
import qls.parser.StylesheetBuilder;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static org.junit.Assert.assertEquals;

public class WidgetFactoryTest extends BaseQlTest {

    private Environment environment;

    @Before
    public void setUp() {

        Form form = createForm("src/input/ql/correct/if.ql");
        environment = new FormEnvironment(form);

        StylesheetBuilder stylesheetBuilder = new StylesheetBuilder();
        Stylesheet stylesheet = stylesheetBuilder.createStylesheet("src/input/qls/correct/form1.qls");

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
        WidgetFactory widgetFactory = new WidgetFactory();
        Question question = new Question("sellingPrice", "", new MoneyType(null), null);
        Widget widget = widgetFactory.createWidget(question, environment);

        assertEquals(widget.getClass(), SpinboxWidget.class);
    }

}