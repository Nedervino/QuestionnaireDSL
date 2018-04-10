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
import ql.gui.uicomponents.widgets.*;
import ql.utilities.IOHandler;
import qls.ast.Stylesheet;
import qls.parser.StylesheetBuilder;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class WidgetFactoryTest extends BaseQlTest {

    private WidgetFactory widgetFactory;
    private Environment environment;

    @Before
    public void setUp() {
        Form form = createForm("src/input/ql/correct/gui/allQuestionTypes.ql");
        environment = new FormEnvironment(form);
        widgetFactory = new WidgetFactory();
    }

    @Test
    public void shouldCreateCheckboxForBoolean() {
        Widget booleanWidget = widgetFactory.createWidget(environment.getQuestion("q1"), environment);
        assertEquals(booleanWidget.getClass(), CheckboxWidget.class);
    }

    @Test
    public void shouldCreateTextFieldForString() {
        Widget stringWidget = widgetFactory.createWidget(environment.getQuestion("q2"), environment);
        assertEquals(stringWidget.getClass(), TextFieldWidget.class);
    }

    @Test
    public void shouldCreateTextFieldForDecimal() {
        Widget decimalWidget = widgetFactory.createWidget(environment.getQuestion("q3"), environment);
        assertEquals(decimalWidget.getClass(), TextFieldWidget.class);
    }

    @Test
    public void shouldCreateSpinboxForInteger() {
        Widget integerWidget = widgetFactory.createWidget(environment.getQuestion("q4"), environment);
        assertEquals(integerWidget.getClass(), SpinboxWidget.class);
    }

    @Test
    public void shouldCreateTextfieldForMoney() {
        Widget moneyWidget = widgetFactory.createWidget(environment.getQuestion("q5"), environment);
        assertEquals(moneyWidget.getClass(), TextFieldWidget.class);
    }

    @Test
    public void shouldCreateTextfieldForDate() {
        Widget dateWidget = widgetFactory.createWidget(environment.getQuestion("q6"), environment);
        assertEquals(dateWidget.getClass(), TextFieldWidget.class);
    }

}