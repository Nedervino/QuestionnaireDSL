package ql.evaluator;

import ql.ast.Form;
import ql.ast.expressions.Variable;
import ql.ast.expressions.binary.*;
import ql.ast.expressions.literals.*;
import ql.ast.expressions.unary.ArithmeticNegation;
import ql.ast.expressions.unary.LogicalNegation;
import ql.ast.visitors.ExpressionVisitor;
import ql.ast.visitors.FormVisitor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ReferenceMapVisitor implements FormVisitor<Void>, ExpressionVisitor<Void> {

    HashMap<String, List<Variable>> referenceMap;

    public ReferenceMapVisitor(){
        referenceMap = new HashMap<>();
    }

    public HashMap<String, List<Variable>> getMap(Form form){
        visit(form);

        return referenceMap;
    }

    @Override
    public Void visit(Addition addition) {
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
    public Void visit(Variable node){
        String varName = node.toString();

        List<Variable> referenceList;
        if(!referenceMap.containsKey(varName)) {
            referenceList = new LinkedList();
            referenceMap.put(varName, referenceList);
        }
        else{
            referenceList = referenceMap.get(varName);
        }
        referenceList.add(node);

        return null;
    }

    @Override
    public Void visit(Form form) {
        return null;
    }
}
