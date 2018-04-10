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
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class FormEnvironment implements FormStatementVisitor<List<String>>, Environment {

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
        boolean reevaluationNecessary = false;
        for (Question question : getQuestions()) {
            if (expressionStore.hasExpression(question.getId())) {
                Value value = expressionEvaluator.evaluate(expressionStore.getExpression(question.getId()));
                if (valueStore.setValue(question.getId(), value)) {
                    reevaluationNecessary = true;
                }
            }
        }
        if (reevaluationNecessary) {
            evaluate();
        } else {
            notifyChangeListeners();
        }
    }

    @Override
    public Question getQuestion(String questionId) {
        return questionStore.getQuestion(questionId);
    }

    @Override
    public void registerChangeListener(EnvironmentListener environmentListener) {
        listeners.add(environmentListener);
    }

    private void notifyChangeListeners() {
        for (EnvironmentListener listener : listeners) {
            listener.onEnvironmentUpdated();
        }
    }

    @Override
    public boolean setValue(String questionId, Value value) {
        if (questionIsComputed(questionId)) {
            return false;
        }
        valueStore.setValue(questionId, value);
        evaluate();
        return true;
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
            for (Expression conditionExpression : questionStore.getConditionDependencies(questionId)) {
                BooleanValue condition = (BooleanValue) expressionEvaluator.evaluate(conditionExpression);
                if (!condition.getValue()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public List<String> visit(Form form) {
        for (Statement statement : form.getStatements()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public List<String> visit(Question question) {
        questionStore.addQuestion(question);
        initialiseDefaultValue(question);
        return Collections.singletonList(question.getId());
    }

    @Override
    public List<String> visit(ComputedQuestion question) {
        questionStore.addQuestion(question);
        initialiseDefaultValue(question);
        expressionStore.addExpression(question.getId(), question.getExpression());
        return Collections.singletonList(question.getId());
    }

    private void initialiseDefaultValue(Question question) {
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
                throw new IllegalArgumentException();
            }

        }));
    }

    @Override
    public List<String> visit(IfStatement node) {
        List<String> dependingQuestions = new ArrayList<>();

        for (Statement statement : node.getIfStatements()) {
            List<String> identifiers = statement.accept(this);
            for (String identifier : identifiers) {
                questionStore.addConditionDependency(identifier, node.getCondition());
            }
            dependingQuestions.addAll(identifiers);
        }
        return dependingQuestions;
    }

    @Override
    public List<String> visit(IfElseStatement node) {
        List<String> dependingQuestions = new ArrayList<>();

        for (Statement statement : node.getIfStatements()) {
            List<String> identifiers = statement.accept(this);
            for (String identifier : identifiers) {
                questionStore.addConditionDependency(identifier, node.getCondition());
            }
            dependingQuestions.addAll(identifiers);
        }

        for (Statement statement : node.getElseStatements()) {
            List<String> identifiers = statement.accept(this);
            for (String identifier : identifiers) {
                questionStore.addConditionDependency(identifier, new Negation(node.getCondition(), node.getSourceLocation()));
            }
            dependingQuestions.addAll(identifiers);
        }
        return dependingQuestions;
    }

}
