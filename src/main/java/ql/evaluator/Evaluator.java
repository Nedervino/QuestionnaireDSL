package ql.evaluator;

import ql.ast.ASTNode;
import ql.ast.Form;
import ql.ast.expressions.Expression;
import ql.ast.expressions.Variable;
import ql.ast.expressions.binary.*;
import ql.ast.expressions.literals.*;
import ql.ast.expressions.unary.ArithmeticNegation;
import ql.ast.expressions.unary.LogicalNegation;
import ql.ast.statements.*;
import ql.ast.types.*;
import ql.ast.visitors.ExpressionVisitor;
import ql.ast.visitors.FormVisitor;
import ql.ast.visitors.StatementVisitor;
import ql.gui.FormViewer;

import java.math.BigDecimal;
import java.util.*;


public class Evaluator implements FormVisitor<Void>, StatementVisitor<Void>, ExpressionVisitor<Void> {

    public FormViewer formViewer;
    HashMap<ASTNode, Evaluatable> storedValues;
    HashMap<String, Question> idLookup;
    Form form;

    public Evaluator() {
        storedValues = new HashMap<>();
        idLookup = new HashMap<>();
    }

    public Evaluatable get(ASTNode varName) {
        return storedValues.get(varName);
    }

    public void start(Form form) {
        this.form = form;
        visit(form);
    }

    public void update(Question node, String value) {
        //Recognize whether answers to question match the declared type

        Evaluatable evaluatable = createEvaluatable(node.getType(), value);
        //Update what value is stored at this node in the current state
        storedValues.put(node, evaluatable);
        visit(form);

        //repaint the GUI
        formViewer.repaint();
    }

    public void update(Question node, boolean value) {
        //TODO Recognize whether answers to question match the declared type

        EvaluatableBoolean evaluatable = new EvaluatableBoolean(value);
        storedValues.put(node, evaluatable);
        visit(form);

        //repaint the GUI
        formViewer.repaint();
    }

    private Evaluatable createEvaluatable(Type type, String value) {
        //TODO write switch which creates the right evaluatable implementation (string, int etc)
        if(type instanceof StringType){
            return new EvaluatableString(value);
        } else if (type instanceof IntegerType) {
            return new EvaluatableInteger(Integer.parseInt(value));
        } else if (type instanceof DecimalType) {
            return new EvaluatableDecimal(Double.parseDouble(value));
        } else if (type instanceof MoneyType) {
            return new EvaluatableMoney(new BigDecimal(value));
        } else if (type instanceof DateType) {
            return new EvaluatableDate(new Date(value));
        }
        return null;
    }

    @Override
    public Void visit(Question node) {
        String varName = node.getId();
        idLookup.put(varName, node);
        return null;
    }

    @Override
    public Void visit(ComputedQuestion node) {
        System.out.println("Visiting computedQuestion");

        String varName = node.getId();
        idLookup.put(varName, node);

        Expression expression = node.getExpression();
        expression.accept(this);
        if(isCalculated(expression)) {
            Evaluatable value = storedValues.get(expression);
            System.out.println("computed question value");
            System.out.println(value.getValue());
            storedValues.put(node, value);
        }
        return null;
    }

    private boolean isCalculated(ASTNode left, ASTNode right) {
        List<ASTNode> terms = Arrays.asList(new ASTNode[]{left, right});
        return isCalculated(terms);
    }

    //This method can be used to check whether all children have been calculated. Handy when writing expression visit methods, since all binary operations will need to evaluate two terms.
    public boolean isCalculated(Collection<ASTNode> nodes) {

        boolean areCalculated = true;
        for (ASTNode node : nodes) {
            if (!isCalculated(node)) {
                areCalculated = false;
            }
        }
        return areCalculated;
    }


    public boolean isCalculated(ASTNode node) {
        return storedValues.containsKey(node);
    }

    @Override
    public Void visit(IfStatement node) {
        System.out.println("visitng if");

        Expression expression = node.getCondition();
        expression.accept(this);

        if(isCalculated(expression)) {
            Evaluatable value = storedValues.get(expression);
            if (value.isTrue()) {
                System.out.println("condition is true");

                List<Statement> statements = node.getIfStatements();
                visit(statements);
            }
        }

        return null;
    }

    void visit(List<Statement> statements){
        for(Statement statement : statements){
            System.out.println("visiting statement");
            statement.accept(this);
        }
    }

    @Override
    public Void visit(IfElseStatement node) {
        Expression expression = node.getCondition();
        expression.accept(this);
        Evaluatable value = storedValues.get(expression);
        List<Statement> statements;
        if(isCalculated(expression)) {
            if (value.isTrue()) {
                statements = node.getIfStatements();
            } else {
                statements = node.getElseStatements();
            }
            visit(statements);
        }

        return null;
    }

    @Override
    public Void visit(Addition node) {
        Expression left = node.getLeft();
        Expression right = node.getRight();
        left.accept(this);
        right.accept(this);
        if (isCalculated(left, right)) {
            Evaluatable result = storedValues.get(left).add(storedValues.get(right));
            storedValues.put(node, result);
        }
        return null;
    }

    @Override
    public Void visit(LogicalAnd node) {
        Expression left = node.getLeft();
        Expression right = node.getRight();
        left.accept(this);
        right.accept(this);
        if (isCalculated(left, right)) {
            EvaluatableBoolean result = storedValues.get(left).and(storedValues.get(right));
            storedValues.put(node, result);
        }
        return null;
    }

    @Override
    public Void visit(Division node) {
        Expression left = node.getLeft();
        Expression right = node.getRight();
        left.accept(this);
        right.accept(this);
        if (isCalculated(left, right)) {
            Evaluatable result = storedValues.get(left).divide(storedValues.get(right));
            storedValues.put(node, result);
        }
        return null;
    }

    @Override
    public Void visit(Equal node) {
        Expression left = node.getLeft();
        Expression right = node.getRight();
        left.accept(this);
        right.accept(this);
        if (isCalculated(left, right)) {
            EvaluatableBoolean result = storedValues.get(left).isEqual(storedValues.get(right));
            storedValues.put(node, result);
        }
        return null;
    }

    @Override
    public Void visit(GreaterThanEqual node) {
        Expression left = node.getLeft();
        Expression right = node.getRight();
        left.accept(this);
        right.accept(this);
        if (isCalculated(left, right)) {
            EvaluatableBoolean result = storedValues.get(left).greaterThanEqual(storedValues.get(right));
            storedValues.put(node, result);
        }
        return null;
    }

    @Override
    public Void visit(GreaterThan node) {
        Expression left = node.getLeft();
        Expression right = node.getRight();
        left.accept(this);
        right.accept(this);
        if (isCalculated(left, right)) {
            EvaluatableBoolean result = storedValues.get(left).greaterThan(storedValues.get(right));
            storedValues.put(node, result);
        }
        return null;
    }

    @Override
    public Void visit(LessThanEqual node) {
        Expression left = node.getLeft();
        Expression right = node.getRight();
        left.accept(this);
        right.accept(this);
        if (isCalculated(left, right)) {
            EvaluatableBoolean result = storedValues.get(left).lessThanEqual(storedValues.get(right));
            storedValues.put(node, result);
        }
        return null;
    }

    @Override
    public Void visit(LessThan node) {
        Expression left = node.getLeft();
        Expression right = node.getRight();
        left.accept(this);
        right.accept(this);
        if (isCalculated(left, right)) {
            EvaluatableBoolean result = storedValues.get(left).lessThan(storedValues.get(right));
            storedValues.put(node, result);
        }
        return null;
    }

    @Override
    public Void visit(Multiplication node) {
        Expression left = node.getLeft();
        Expression right = node.getRight();
        left.accept(this);
        right.accept(this);
        if (isCalculated(left, right)) {
            Evaluatable result = storedValues.get(left).multiply(storedValues.get(right));
            storedValues.put(node, result);
        }
        return null;
    }

    @Override
    public Void visit(NotEqual node) {
        Expression left = node.getLeft();
        Expression right = node.getRight();
        left.accept(this);
        right.accept(this);
        if (isCalculated(left, right)) {
            EvaluatableBoolean result = storedValues.get(left).notEqual(storedValues.get(right));
            storedValues.put(node, result);
        }
        return null;
    }

    @Override
    public Void visit(LogicalOr node) {
        Expression left = node.getLeft();
        Expression right = node.getRight();
        left.accept(this);
        right.accept(this);
        if (isCalculated(left, right)) {
            EvaluatableBoolean result = storedValues.get(left).or(storedValues.get(right));
            storedValues.put(node, result);
        }
        return null;
    }

    @Override
    public Void visit(Subtraction node) {
        Expression left = node.getLeft();
        Expression right = node.getRight();
        left.accept(this);
        right.accept(this);
        if (isCalculated(left, right)) {
            Evaluatable result = storedValues.get(left).subtract(storedValues.get(right));
            storedValues.put(node, result);
        }
        return null;
    }

    @Override
    public Void visit(LogicalNegation node) {
        Expression term = node.getExpression();
        term.accept(this);
        if (isCalculated(term)) {
            EvaluatableBoolean result = storedValues.get(term).logicalNegate();
            storedValues.put(node, result);
        }
        return null;
    }

    @Override
    public Void visit(ArithmeticNegation node) {
        Expression term = node.getExpression();
        term.accept(this);
        if (isCalculated(term)) {
            Evaluatable result = storedValues.get(term).arithmeticNegate();
            storedValues.put(node, result);
        }
        return null;
    }

    @Override
    public Void visit(StringLiteral node) {
        EvaluatableString value = new EvaluatableString(node.getValue());
        storedValues.put(node, value);
        return null;
    }

    @Override
    public Void visit(IntegerLiteral node) {
        EvaluatableInteger value = new EvaluatableInteger(node.getValue());
        storedValues.put(node, value);
        return null;
    }

    @Override
    public Void visit(BooleanLiteral node) {
        EvaluatableBoolean value = new EvaluatableBoolean(node.getValue());
        storedValues.put(node, value);
        return null;
    }

    @Override
    public Void visit(DateLiteral node) {
        EvaluatableDate value = new EvaluatableDate(node.getValue());
        storedValues.put(node, value);
        return null;
    }

    @Override
    public Void visit(DecimalLiteral node) {
        EvaluatableDecimal value = new EvaluatableDecimal(node.getValue());
        storedValues.put(node, value);
        return null;
    }

    @Override
    public Void visit(MoneyLiteral node) {
        EvaluatableMoney value = new EvaluatableMoney(node.getValue());
        storedValues.put(node, value);
        return null;
    }

    @Override
    public Void visit(Variable variable) {
        String varName = variable.toString();
        System.out.println(varName);
        Question declarationNode = findDeclarationNode(varName);
        System.out.println(declarationNode.getType());
        if(isCalculated(declarationNode)) {
            Evaluatable value = storedValues.get(declarationNode);
            System.out.println(value.getValue());
            storedValues.put(variable, value);
        }
        return null;
    }

    private Question findDeclarationNode(String varName) {
        return idLookup.get(varName);
    }

    @Override
    public Void visit(Form form) {
        List<Statement> statements = form.getStatements();
        visit(statements);
        return null;
    }
}
