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
import qls.ast.properties.ColorProperty;
import qls.ast.properties.FontProperty;
import qls.ast.properties.FontSizeProperty;
import qls.ast.properties.WidthProperty;
import qls.ast.widgets.*;

import java.util.ArrayList;
import java.util.List;

public class ASTConstructionVisitor extends QLSBaseVisitor<ASTNode> {

    @Override
    public ASTNode visitStylesheet(QLSParser.StylesheetContext ctx) {
        String formId = ctx.IDENTIFIER().getText();
        List<Page> pages = new ArrayList<>();
        for (QLSParser.PageContext pageContext : ctx.page()) {
            pages.add((Page) visit(pageContext));
        }
        return new Stylesheet(formId, pages, getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitPage(QLSParser.PageContext ctx) {
        String pageId = ctx.IDENTIFIER().getText();
        List<Component> components = new ArrayList<>();
        // for(QLSParser.SectionContext sectionContext : ctx.section()){
        //     components.add((Component) visit(sectionContext));
        // }
        // for(QLSParser.DefaultRuleContext defaultRuleContext : ctx.defaultRule()){
        //     components.add((Component) visit(defaultRuleContext));
        // }

        return new Page(pageId, components, getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitQuestion(QLSParser.QuestionContext ctx) {
        String questionId = ctx.IDENTIFIER().getText();
        // return new Question(questionId, (Widget) visit(ctx.widget()), getSourceLocation(ctx));
        return null;
    }

    @Override
    public ASTNode visitSection(QLSParser.SectionContext ctx) {
        String sectionIdentifier = ctx.STRINGLITERAL().getText().substring(1, ctx.STRINGLITERAL().getText().length() - 1);
        return super.visitSection(ctx);
    }

    @Override
    public ASTNode visitDefaultRule(QLSParser.DefaultRuleContext ctx) {
        return super.visitDefaultRule(ctx);
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
            return new CheckboxType(ctx.yes.getText(), getSourceLocation(ctx));
        }
        return new CheckboxType(getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitDropdownType(QLSParser.DropdownTypeContext ctx) {
        if (ctx.choiceMap().yes != null && ctx.choiceMap().no != null) {
            return new DropdownType(ctx.choiceMap().yes.getText(), ctx.choiceMap().no.getText(), getSourceLocation(ctx));
        }
        return new DropdownType(getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitRadioType(QLSParser.RadioTypeContext ctx) {
        if (ctx.choiceMap().yes != null && ctx.choiceMap().no != null) {
            return new RadioType(ctx.choiceMap().yes.getText(), ctx.choiceMap().no.getText(), getSourceLocation(ctx));
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
        return new FontProperty(ctx.STRINGLITERAL().getText(), getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitFontSizeProperty(QLSParser.FontSizePropertyContext ctx) {
        return new FontSizeProperty(Integer.parseInt(ctx.INTEGERLITERAL().getText()), getSourceLocation(ctx));
    }

    @Override
    public ASTNode visitWidthProperty(QLSParser.WidthPropertyContext ctx) {
        return new WidthProperty(Integer.parseInt(ctx.INTEGERLITERAL().getText()), getSourceLocation(ctx));
    }

    private SourceLocation getSourceLocation(ParserRuleContext ctx) {
        return new SourceLocation(ctx.start.getLine(), ctx.start.getCharPositionInLine());
    }
}

