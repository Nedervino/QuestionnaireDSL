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

import java.util.*;


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
        for (EnvironmentListener listener : listeners) {
            listener.onEnvironmentUpdated();
        }
    }

    @Override
    public boolean setValue(String questionId, Value value) {
        if (questionIsComputed(questionId)) {
            return false;
        }
        System.out.printf("Was: %s%n", getQuestionValue(questionId).getValue().toString());
        valueStore.setValue(questionId, value);
        System.out.printf("Will be: %s%n", value.getValue().toString());
        evaluate();
        System.out.printf("Is: %s%n", getQuestionValue(questionId).getValue().toString());
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
            List<Expression> conditionExpressions = questionStore.getConditionDependencies(questionId);
            for(Expression conditionExpression : conditionExpressions) {
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
        // Initialise environment with default values
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
        return Arrays.asList(new String[]{question.getId()});
    }

    @Override
    public List<String> visit(ComputedQuestion question) {
        questionStore.addQuestion(question);
        expressionStore.addExpression(question.getId(), question.getExpression());
        return Arrays.asList(new String[]{question.getId()});
    }

    //TODO: handle nested dependencies within which parent is false but child is true
    @Override
    public List<String> visit(IfStatement node) {
        List<String> allIdentifiers = new LinkedList<>();

        for (Statement statement : node.getIfStatements()) {
            List<String> identifiers = statement.accept(this);
            if (identifiers != null) {
                for (String identifier : identifiers) {
                    questionStore.addConditionDependency(identifier, node.getCondition());
                }
                allIdentifiers.addAll(identifiers);
            }
        }
        return allIdentifiers;
    }

    @Override
    public List<String> visit(IfElseStatement node) {
        List<String> allIdentifiers = new LinkedList<>();

        for (Statement statement : node.getIfStatements()) {
            List<String> identifiers = statement.accept(this);
            if (identifiers != null) {
                for(String identifier : identifiers) {
                    questionStore.addConditionDependency(identifier, node.getCondition());
                }
                allIdentifiers.addAll(identifiers);
            }
        }

        for (Statement statement : node.getElseStatements()) {
            List<String> identifiers = statement.accept(this);
            if (identifiers != null) {
                for (String identifier : identifiers) {
                    questionStore.addConditionDependency(identifier, new Negation(node.getCondition(), node.getSourceLocation()));
                }
                allIdentifiers.addAll(identifiers);
            }
        }
        return allIdentifiers;
    }

}
