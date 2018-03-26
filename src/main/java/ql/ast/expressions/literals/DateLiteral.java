package ql.ast.expressions.literals;

import ql.ast.SourceLocation;
import ql.ast.expressions.Expression;
import ql.ast.visitors.ExpressionVisitor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateLiteral extends Expression {

    private static final String DATE_FORMAT = "dd-MM-yyyy";

    private final Date value;

    public DateLiteral(String value, SourceLocation sourceLocation) throws ParseException {
        super(sourceLocation);
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        this.value = dateFormat.parse(value);
    }

    public Date getValue() {
        return value;
    }

    public static String getDateFormat() {
        return DATE_FORMAT;
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
