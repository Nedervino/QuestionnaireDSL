package ql.gui.widgets;

import ql.ast.statements.Question;
import ql.ast.types.*;
import ql.ast.visitors.TypeVisitor;
import ql.environment.Environment;
import ql.environment.values.*;
import ql.gui.WidgetListener;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TextFieldWidget extends BaseWidget {

    private final JFormattedTextField textField;

    public TextFieldWidget(Environment environment, Question question, boolean isEditable) {
        super(environment, question, isEditable);
        textField = createTextField(question);
        textField.setPreferredSize(new Dimension(200, 50));
        setValue();
        setEditable(isEditable);
    }

    @Override
    public void setVisible(boolean visible) {
        textField.setVisible(visible);
    }

    @Override
    public void setEditable(boolean isEditable) {
        textField.setEditable(isEditable);
    }

    @Override
    public void setValue() {
        Value value = environment.getQuestionValue(question.getId());
        //TODO: check for equality with previous value
        if (value != null) {
            textField.setValue(value.getValue());
        }
    }

    @Override
    public void registerChangeListener(WidgetListener widgetListener) {
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (isEditable) {
                    Value value = question.getType().accept(new TypeVisitor<Value>() {
                        @Override
                        public Value visit(BooleanType booleanType) {
                            return new BooleanValue(textField.getText());
                        }

                        @Override
                        public Value visit(DecimalType decimalType) {
                            return new DecimalValue(textField.getText());
                        }

                        @Override
                        public Value visit(IntegerType integerType) {
                            return new IntegerValue(textField.getText());
                        }

                        @Override
                        public Value visit(MoneyType moneyType) {
                            return new MoneyValue(textField.getText());
                        }

                        @Override
                        public Value visit(StringType stringType) {
                            return new StringValue(textField.getText());
                        }

                        @Override
                        public Value visit(DateType dateType) {
                            return new DateValue(textField.getText());
                        }

                        @Override
                        public Value visit(ErrorType errorType) {
                            throw new IllegalArgumentException();
                        }
                    });
                    widgetListener.onInputValueUpdated(question, value);
                }
            }
        });
    }

    @Override
    public JComponent getComponent() {
        return textField;
    }


    private JFormattedTextField createTextField(Question question) {
        return question.getType().accept(new TypeVisitor<JFormattedTextField>() {
            @Override
            public JFormattedTextField visit(BooleanType booleanType) {
                return null;
            }

            @Override
            public JFormattedTextField visit(DecimalType decimalType) {
                NumberFormat format = NumberFormat.getInstance();
                NumberFormatter formatter = new NumberFormatter(format);
                formatter.setValueClass(Double.class);
                formatter.setMaximum(Double.MAX_VALUE);
                formatter.setAllowsInvalid(true);
                return new JFormattedTextField(formatter);
            }

            @Override
            public JFormattedTextField visit(IntegerType integerType) {
                NumberFormat format = NumberFormat.getInstance();
                NumberFormatter formatter = new NumberFormatter(format);
                formatter.setValueClass(Integer.class);
                formatter.setMaximum(Integer.MAX_VALUE);
                formatter.setAllowsInvalid(true);
                format.setGroupingUsed(false);
                return new JFormattedTextField(formatter);
            }

            @Override
            public JFormattedTextField visit(MoneyType moneyType) {
                NumberFormat format = NumberFormat.getInstance();
                NumberFormatter formatter = new NumberFormatter(format);
                formatter.setValueClass(Double.class);
                formatter.setMaximum(Double.MAX_VALUE);
                formatter.setAllowsInvalid(true);
                return new JFormattedTextField(formatter);
            }

            @Override
            public JFormattedTextField visit(StringType stringType) {
                return new JFormattedTextField();
            }

            @Override
            public JFormattedTextField visit(DateType dateType) {
                DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                DateFormatter formatter = new DateFormatter(format);
                formatter.setValueClass(Date.class);
                formatter.setAllowsInvalid(true);
                return new JFormattedTextField(formatter);
            }

            @Override
            public JFormattedTextField visit(ErrorType errorType) {
                throw new IllegalArgumentException();
            }
        });
    }
}
