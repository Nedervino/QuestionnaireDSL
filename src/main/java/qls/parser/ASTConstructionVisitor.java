package qls.parser;

import org.antlr.v4.runtime.ParserRuleContext;
import ql.ast.ASTNode;
import ql.ast.SourceLocation;
import ql.ast.types.*;
import qls.QLSBaseVisitor;
import qls.QLSParser;
import qls.ast.Page;
import qls.ast.Stylesheet;
import qls.ast.components.Component;
import qls.ast.components.QuestionReference;
import qls.ast.components.Section;
import qls.ast.defaultrules.DefaultRule;
import qls.ast.defaultrules.DefaultStyleRule;
import qls.ast.defaultrules.DefaultWidgetRule;
import qls.ast.properties.*;
import qls.ast.widgets.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ASTConstructionVisitor extends QLSBaseVisitor<ASTNode> {

    @Override
    public ASTNode visitStylesheet(QLSParser.StylesheetContext ctx) {
        String stylesheetId = ctx.IDENTIFIER().getText();
        List<Page> pages = new ArrayList<>();
        ctx.page().forEach(pageContext -> pages.add((Page) visit(pageContext)));
        return new Stylesheet(stylesheetId, pages, getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitPage(QLSParser.PageContext ctx) {
        String pageId = ctx.IDENTIFIER().getText();
        List<Component> components = ctx.section().stream()
                .map(sectionContext -> (Component) visit(sectionContext))
                .collect(Collectors.toList());
        List<DefaultRule> rules = ctx.defaultRule().stream()
                .map(defaultRuleContext -> (DefaultRule) visit(defaultRuleContext))
                .collect(Collectors.toList());
        return new Page(pageId, components, rules, getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitSection(QLSParser.SectionContext ctx) {
        String sectionId = ctx.STRINGLITERAL().getText().replaceAll("^\"|\"$", "");
        List<Component> components = ctx.component().stream()
                .map(componentContext -> (Component) visit(componentContext))
                .collect(Collectors.toList());
        List<DefaultRule> rules = ctx.defaultRule().stream()
                .map(defaultRuleContext -> (DefaultRule) visit(defaultRuleContext))
                .collect(Collectors.toList());
        return new Section(sectionId, components, rules, getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitQuestion(QLSParser.QuestionContext ctx) {
        String questionId = ctx.IDENTIFIER().getText();
        WidgetType widgetType = (ctx.widget() == null) ? null : (WidgetType) visit(ctx.widget());
        return new QuestionReference(questionId, widgetType, getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitWidgetRule(QLSParser.WidgetRuleContext ctx) {
        Type type = (Type) visit(ctx.type());
        WidgetType widgetType = (WidgetType) visit(ctx.widget());
        return new DefaultWidgetRule(type, widgetType, getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitStyleRule(QLSParser.StyleRuleContext ctx) {
        Type type = (Type) visit(ctx.type());
        List<Property> styleProperties = ctx.style().styleProperty().stream()
                .map(stylePropertyContext -> (Property) visit(stylePropertyContext))
                .collect(Collectors.toList());
        WidgetType widgetType = ctx.style().widget() == null ? null : (WidgetType) visit(ctx.style().widget());
        return new DefaultStyleRule(type, styleProperties, widgetType, getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitWidget(QLSParser.WidgetContext ctx) {
        return visit(ctx.widgetType());
    }

    @Override
    public ASTNode visitBooleanType(QLSParser.BooleanTypeContext ctx) {
        return new BooleanType(getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitIntegerType(QLSParser.IntegerTypeContext ctx) {
        return new IntegerType(getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitDecimalType(QLSParser.DecimalTypeContext ctx) {
        return new DecimalType(getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitDateType(QLSParser.DateTypeContext ctx) {
        return new DateType(getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitStringType(QLSParser.StringTypeContext ctx) {
        return new StringType(getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitMoneyType(QLSParser.MoneyTypeContext ctx) {
        return new MoneyType(getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitCheckboxType(QLSParser.CheckboxTypeContext ctx) {
        if (ctx.yes != null) {
            return new CheckboxType(ctx.yes.getText().replaceAll("^\"|\"$", ""), getSourceLocation(ctx));
        }
        return new CheckboxType(getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitDropdownType(QLSParser.DropdownTypeContext ctx) {
        if (ctx.choiceMap().yes != null && ctx.choiceMap().no != null) {
            String trueLabel = ctx.choiceMap().yes.getText().replaceAll("^\"|\"$", "");
            String falseLabel = ctx.choiceMap().no.getText().replaceAll("^\"|\"$", "");
            return new DropdownType(trueLabel, falseLabel, getSourceLocation(ctx));
        }
        return new DropdownType(getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitRadioType(QLSParser.RadioTypeContext ctx) {
        if (ctx.choiceMap().yes != null && ctx.choiceMap().no != null) {
            String trueLabel = ctx.choiceMap().yes.getText().replaceAll("^\"|\"$", "");
            String falseLabel = ctx.choiceMap().no.getText().replaceAll("^\"|\"$", "");
            return new RadioType(trueLabel, falseLabel, getSourceLocation(ctx));
        }
        return new RadioType(getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitSliderType(QLSParser.SliderTypeContext ctx) {
        int start = Integer.parseInt(ctx.sliderMap().start.getText());
        int end = Integer.parseInt(ctx.sliderMap().end.getText());
        int step = Integer.parseInt(ctx.sliderMap().step.getText());
        return new SliderType(start, end, step, getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitSpinboxType(QLSParser.SpinboxTypeContext ctx) {
        return new SpinboxType(getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitTextfieldType(QLSParser.TextfieldTypeContext ctx) {
        return new TextFieldType(getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitColorProperty(QLSParser.ColorPropertyContext ctx) {
        return new ColorProperty(ctx.HEXCOLOR().getText(), getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitFontProperty(QLSParser.FontPropertyContext ctx) {
        return new FontProperty(ctx.STRINGLITERAL().getText().replaceAll("^\"|\"$", ""), getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitFontSizeProperty(QLSParser.FontSizePropertyContext ctx) {
        return new FontSizeProperty(Integer.parseInt(ctx.INTEGERLITERAL().getText()), getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitWidthProperty(QLSParser.WidthPropertyContext ctx) {
        return new WidthProperty(Integer.parseInt(ctx.INTEGERLITERAL().getText()), getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitHeightProperty(QLSParser.HeightPropertyContext ctx) {
        return new HeightProperty(Integer.parseInt(ctx.INTEGERLITERAL().getText()), getSourceLocation(ctx));
    }

    private SourceLocation getSourceLocation(ParserRuleContext ctx) {
        return new SourceLocation(ctx.start.getLine(), ctx.start.getCharPositionInLine());
    }
}

