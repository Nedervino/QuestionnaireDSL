package ql.gui.widgets;

import ql.ast.statements.Question;
import ql.ast.types.*;
import ql.ast.visitors.TypeVisitor;
import ql.environment.Environment;
import ql.environment.values.*;

public abstract class BaseWidget implements Widget {

    protected final Environment environment;
    protected final Question question;
    protected final boolean isEditable;

    protected BaseWidget(Environment environment, Question question, boolean isEditable) {
        this.environment = environment;
        this.question = question;
        this.isEditable = isEditable;
    }

    public Value parseValue(String input) {
        return question.getType().accept(new TypeVisitor<Value>() {
            @Override
            public Value visit(BooleanType booleanType) {
                return new BooleanValue(input);
            }

            @Override
            public Value visit(DecimalType decimalType) {
                return new DecimalValue(input);
            }

            @Override
            public Value visit(IntegerType integerType) {
                return new IntegerValue(input);
            }

            @Override
            public Value visit(MoneyType moneyType) {
                return new MoneyValue(input);
            }

            @Override
            public Value visit(StringType stringType) {
                return new StringValue(input);
            }

            @Override
            public Value visit(DateType dateType) {
                return new DateValue(input);
            }

            @Override
            public Value visit(ErrorType errorType) {
                throw new IllegalArgumentException();
            }
        });
    }
}