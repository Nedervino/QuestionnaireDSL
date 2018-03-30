package ql.evaluator;

import ql.ast.Form;
import ql.ast.expressions.Variable;
import ql.ast.expressions.binary.*;
import ql.ast.expressions.literals.*;
import ql.ast.expressions.unary.Negation;
import ql.ast.expressions.unary.Negative;
import ql.ast.statements.*;
import ql.ast.visitors.ExpressionVisitor;
import ql.ast.visitors.FormStatementVisitor;
import ql.evaluator.datastore.ExpressionStore;
import ql.evaluator.datastore.QuestionStore;
import ql.evaluator.datastore.ValueStore;
import ql.evaluator.values.*;

import java.util.List;


public class Evaluator implements FormStatementVisitor<String>, FormEvaluator {

    private final ExpressionStore expressionStore;
    private final QuestionStore questionStore;
    private final ValueStore valueStore;
    private final ExpressionEvaluator expressionEvaluator;

    private final QuestionCollector questionCollector;
    private Form form;

    public Evaluator(Form form) {
        this.form = form;

        expressionStore = new ExpressionStore();
        questionStore = new QuestionStore();
        valueStore = new ValueStore();
        expressionEvaluator = new ExpressionEvaluator(valueStore);

        questionCollector = new QuestionCollector();

        visit(form);
    }

    @Override
    public void evaluate() {
        for (Question question : getQuestions()) {
            if (expressionStore.hasExpression(question.getId())) {
                //TODO: replace with static expressionevaluator
                Value value = expressionEvaluator.evaluate(expressionStore.getExpression(question.getId()));
                valueStore.setValue(question.getId(), value);
            }
        }
    }


    @Override
    public void setValue(String questionId, Value value) {
        System.out.printf("Updating. Value for %s was %s\n", questionId, valueStore.getValue(questionId).getValue().toString());
        valueStore.setValue(questionId, value);
        System.out.printf("Value for %s is now %s\n", questionId, valueStore.getValue(questionId).getValue().toString());

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
    public String visit(Form form) {
        for (Statement statement : form.getStatements()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public String visit(Question question) {
        questionStore.addQuestion(question);
        // valueStore.setValue(question.getId(), new StringValue(""));
        return question.getId();
    }

    @Override
    public String visit(ComputedQuestion question) {
        // valueStore.setValue(question.getId(), question.getExpression().accept(this));

        questionStore.addQuestion(question);
        expressionStore.addExpression(question.getId(), question.getExpression());
        return question.getId();
    }

    //TODO: handle nested dependencies within which parent is false but child is true
    @Override
    public String visit(IfStatement node) {
        for (Statement statement : node.getIfStatements()) {
            String identifier = statement.accept(this);
            if(identifier != null) {
                questionStore.addConditionDependency(identifier, node.getCondition());
            }
        }

        // if (((BooleanValue) node.getCondition().accept(this)).getValue()) {
        //     visit(node.getIfStatements());
        // }
        return null;
    }

    @Override
    public String visit(IfElseStatement node) {
        for (Statement statement : node.getIfStatements()) {
            String identifier = statement.accept(this);
            if(identifier != null) {
                questionStore.addConditionDependency(identifier, node.getCondition());
            }
        }

        for (Statement statement : node.getElseStatements()) {
            String identifier = statement.accept(this);
            if(identifier != null) {
                questionStore.addConditionDependency(identifier, new Negation(node.getCondition(), node.getSourceLocation()));
            }
        }

        // List<Statement> statements;
        // if (((BooleanValue) node.getCondition().accept(this)).getValue()) {
        //     statements = node.getIfStatements();
        // } else {
        //     statements = node.getElseStatements();
        // }
        // visit(statements);
        return null;
    }


}
