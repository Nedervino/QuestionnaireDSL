package ql.evaluator;

import ql.ast.ASTNode;
import ql.ast.Form;
import ql.ast.expressions.Expression;
import ql.ast.expressions.Variable;
import ql.ast.expressions.binary.*;
import ql.ast.expressions.literals.*;
import ql.ast.expressions.unary.ArithmeticNegation;
import ql.ast.expressions.unary.LogicalNegation;
import ql.ast.statements.ComputedQuestion;
import ql.ast.statements.IfElseStatement;
import ql.ast.statements.IfStatement;
import ql.ast.statements.Question;
import ql.ast.visitors.ExpressionVisitor;
import ql.ast.visitors.FormVisitor;
import ql.ast.visitors.StatementVisitor;
import ql.gui.FormViewer;

import java.util.*;


public class Evaluator implements FormVisitor<Void>, StatementVisitor<Void>, ExpressionVisitor<Void> {

    FormViewer formViewer;
    HashMap<ASTNode, Object> storedValues;
    HashMap<String, List<Variable>> referenceMap;
    HashMap<ASTNode, ASTNode> parentMap;
    boolean repaintFlag;

    public Evaluator(){
        storedValues = new HashMap<>();
    }

    public Object get(ASTNode varName) {
        return storedValues.get(varName);
    }

    public void start(Form form) {
        ReferenceMapper referenceMapper = new ReferenceMapper();
        ParentMapper parentMapper = new ParentMapper();

        referenceMap = referenceMapper.getMap(form);
        parentMap = parentMapper.getMap(form);
    }

    public void update(Question node, Object value) {
        //Recognize whether answers to question match the declared type

        //Update what value is stored at this node in the current state
        repaintFlag = false;
        storedValues.put(node, value);
        visit(node);

        //repaint the GUI
        if(repaintFlag) {
            formViewer.repaint();
        }
    }

    @Override
    public Void visit(Question node) {
        visitQuestion(node);
        return null;
    }

    @Override
    public Void visit(ComputedQuestion node) {
        repaintFlag = true;
        visitQuestion(node);
        return null;
    }

    private void visitQuestion(Question node) {
        //Call the evaluator to calculate the new values of the referring nodes, and their parents. Deposit these in the Evaluator object.
        String varName = node.getId();
        List<Variable> referringNodes = referenceMap.get(varName);
        //Starting at the first reference node, we update the value of the reference node in the state object,
        //then we evaluate its parent. This will be an overloaded method. When it encounters expressions it evaluates and stores their results.
        //When it encounters a computedQuestion parent, it will look up the referring nodes for that node, and call evaluate that node and it's parents,
        //until all necessary parents have been evaluated.

        if(referringNodes == null){
            return;
        }
        Object value = storedValues.get(node);
        for(Variable referringNode : referringNodes){
            storedValues.put(referringNode, value);
            visit(referringNode);
        }

    }

    public boolean areCalculated(Collection<Expression> expressions) {
        boolean areCalculated = true;
        for(Expression expression : expressions) {
            if(!storedValues.containsKey(expression)){
                areCalculated = false;
            }
        }
        return areCalculated;

        //Only when all terms in an expression are known, we will visit the parent
    }

    public void visitParent(ASTNode node){
        ASTNode parent = parentMap.get(node);
        if(parent!=null){
            //TODO currently we can't call this visit line, since we don't have a generic visit method for ASTNodes.
            // visit(parent);
        }
    }

    @Override
    public Void visit(IfStatement node) {
        repaintFlag = true;
        return null;
    }

    @Override
    public Void visit(IfElseStatement ifElseStatement) {
        return null;
    }

    @Override
    public Void visit(Addition addition) {
        Expression left = addition.getLeft();
        Expression right = addition.getRight();
        List<Expression> terms = Arrays.asList(new Expression[]{left, right});
        if(areCalculated(terms)){
            //TODO Evaluate the result. This is type specific for money, integer, and float.
            Object result = null;
            storedValues.put(addition, result);
            visitParent(addition);
        }
        return null;
    }

    @Override
    public Void visit(LogicalAnd logicalAnd) {
        return null;
    }

    @Override
    public Void visit(Division division) {
        return null;
    }

    @Override
    public Void visit(Equal equal) {
        return null;
    }

    @Override
    public Void visit(GreaterThanEqual greaterThanEqual) {
        return null;
    }

    @Override
    public Void visit(GreaterThan greaterThan) {
        return null;
    }

    @Override
    public Void visit(LessThanEqual lessThanEqual) {
        return null;
    }

    @Override
    public Void visit(LessThan lessThan) {
        return null;
    }

    @Override
    public Void visit(Multiplication multiplication) {
        return null;
    }

    @Override
    public Void visit(NotEqual notEqual) {
        return null;
    }

    @Override
    public Void visit(LogicalOr logicalOr) {
        return null;
    }

    @Override
    public Void visit(Subtraction subtraction) {
        return null;
    }

    @Override
    public Void visit(LogicalNegation logicalNegation) {
        return null;
    }

    @Override
    public Void visit(ArithmeticNegation arithmeticNegation) {
        return null;
    }

    @Override
    public Void visit(StringLiteral stringLiteral) {
        return null;
    }

    @Override
    public Void visit(IntegerLiteral integerLiteral) {
        return null;
    }

    @Override
    public Void visit(BooleanLiteral booleanLiteral) {
        return null;
    }

    @Override
    public Void visit(DateLiteral dateLiteral) {
        return null;
    }

    @Override
    public Void visit(DecimalLiteral decimalLiteral) {
        return null;
    }

    @Override
    public Void visit(MoneyLiteral moneyLiteral) {
        return null;
    }

    @Override
    public Void visit(Variable variable) {
        return null;
    }

    @Override
    public Void visit(Form form) {
        return null;
    }
}
