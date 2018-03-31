package ql.environment;

import ql.ast.Form;
import ql.ast.expressions.Expression;
import ql.ast.expressions.unary.Negation;
import ql.ast.statements.*;
import ql.ast.types.*;
import ql.ast.visitors.FormStatementVisitor;
import ql.ast.visitors.TypeVisitor;
import ql.environment.datastore.ExpressionStore;
import ql.environment.datastore.QuestionStore;
import ql.environment.datastore.ValueStore;
import ql.environment.values.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class FormEnvironment implements FormStatementVisitor<String>, Environment {

    private final ExpressionStore expressionStore;
    private final QuestionStore questionStore;
    private final ValueStore valueStore;
    private final ExpressionEvaluator expressionEvaluator;
    private final List<EnvironmentListener> listeners;

    public FormEnvironment(Form form) {
        expressionStore = new ExpressionStore();
        questionStore = new QuestionStore();
        valueStore = new ValueStore();
        expressionEvaluator = new ExpressionEvaluator(valueStore);
        listeners = new ArrayList<>();
        visit(form);
    }

    @Override
    public void evaluate() {
        for (Question question : getQuestions()) {
            if (expressionStore.hasExpression(question.getId())) {
                //TODO: replace with static expressionEvaluator
                Value value = expressionEvaluator.evaluate(expressionStore.getExpression(question.getId()));
                valueStore.setValue(question.getId(), value);
            }
        }
        notifyChangeListeners();
    }

    @Override
    public void registerChangeListener(EnvironmentListener environmentListener) {
        listeners.add(environmentListener);
    }

    private void notifyChangeListeners() {
        for(EnvironmentListener listener : listeners) {
            listener.onEnvironmentUpdated();
        }
    }

    @Override
    public void setValue(String questionId, Value value) {
        System.out.printf("Was: %s%n", getQuestionValue(questionId).getValue().toString());
        valueStore.setValue(questionId, value);
        System.out.printf("Will be: %s%n", value.getValue().toString());
        evaluate();
        System.out.printf("Is: %s%n", getQuestionValue(questionId).getValue().toString());

    }

    @Override
    public List<Question> getQuestions() {
        return questionStore.getQuestions();
    }

    @Override
    public Value getQuestionValue(String questionId) {
        return valueStore.getValue(questionId);
    }

    @Override
    public boolean questionIsComputed(String questionId) {
        return expressionStore.hasExpression(questionId);
    }

    @Override
    public boolean questionIsVisible(String questionId) {
        if (questionStore.hasConditionDependency(questionId)) {
            Expression conditionExpression = questionStore.getConditionDependency(questionId);
            BooleanValue condition = (BooleanValue) expressionEvaluator.evaluate(conditionExpression);
            return condition.getValue();
        }
        return true;
    }

    @Override
    public String visit(Form form) {
        for (Statement statement : form.getStatements()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public String visit(Question question) {
        questionStore.addQuestion(question);
        //Initialise environment with default values
        valueStore.setValue(question.getId(), question.getType().accept(new TypeVisitor<Value>() {

            @Override
            public Value visit(BooleanType booleanType) {
                return new BooleanValue(false);
            }

            @Override
            public Value visit(DecimalType decimalType) {
                return new DecimalValue(0.0);
            }

            @Override
            public Value visit(IntegerType integerType) {
                return new IntegerValue(0);
            }

            @Override
            public Value visit(MoneyType moneyType) {
                return new MoneyValue(0.00);
            }

            @Override
            public Value visit(StringType stringType) {
                return new StringValue("");
            }

            @Override
            public Value visit(DateType dateType) {
                return new DateValue(new Date());
            }

            @Override
            public Value visit(ErrorType errorType) {
                //TODO: optionally remove from visitor interface
                return null;
            }

        }));
        return question.getId();
    }

    @Override
    public String visit(ComputedQuestion question) {
        questionStore.addQuestion(question);
        expressionStore.addExpression(question.getId(), question.getExpression());
        return question.getId();
    }

    //TODO: handle nested dependencies within which parent is false but child is true
    @Override
    public String visit(IfStatement node) {
        for (Statement statement : node.getIfStatements()) {
            String identifier = statement.accept(this);
            if (identifier != null) {
                questionStore.addConditionDependency(identifier, node.getCondition());
            }
        }
        return null;
    }

    @Override
    public String visit(IfElseStatement node) {
        for (Statement statement : node.getIfStatements()) {
            String identifier = statement.accept(this);
            if (identifier != null) {
                questionStore.addConditionDependency(identifier, node.getCondition());
            }
        }

        for (Statement statement : node.getElseStatements()) {
            String identifier = statement.accept(this);
            if (identifier != null) {
                questionStore.addConditionDependency(identifier, new Negation(node.getCondition(), node.getSourceLocation()));
            }
        }
        return null;
    }

}
