package ql.evaluator;

import ql.ast.ASTNode;
import ql.ast.Form;
import ql.ast.expressions.Expression;
import ql.ast.expressions.Variable;
import ql.ast.statements.ComputedQuestion;
import ql.ast.statements.IfStatement;
import ql.ast.statements.Question;
import ql.gui.FormViewer;

import java.util.HashMap;
import java.util.List;


//The state will hold all currently calculated values. Not just the values of variables, but also all their parent calculations. This way,
//when we change an input variable, we can invalidate all the referring nodes and their vars, without having to recalculate everything.

public class Evaluator {

    FormViewer formViewer;
    HashMap<ASTNode, String> storedValues;
    HashMap<String, List<Variable>> referenceMap;
    HashMap<ASTNode, ASTNode> parentMap;
    boolean repaintFlag;


    public void update(Question node, String value) {
        //Recognize whether answers to question match the declared type

        //Update what value is stored at this node in the current state
        repaintFlag = false;
        storedValues.put(node, value);
        evaluate(node);

        //repaint the GUI
        //TODO only repaint the gui when computedQuestions or IfStatements'expressions have different evaluations than before
        if(repaintFlag) {
            formViewer.repaint();
        }
    }

    private void evaluate(Question node) {
        evaluateQuestion(node);
    }

    private void evaluate(ComputedQuestion node) {
        //If all variables are known at the point of evaluation, set the repaint Flag to true, as we will actually store a value for this expression now.
        evaluateQuestion(node);
    }

    private void evaluateQuestion(Question node) {
        //Call the evaluator to calculate the new values of the referring nodes, and their parents. Deposit these in the Evaluator object.
        String varName = node.getId();
        List<Variable> referringNodes = referenceMap.get(varName);
        //Starting at the first reference node, we update the value of the reference node in the state object,
        //then we evaluate its parent. This will be an overloaded method. When it encounters expressions it evaluates and stores their results.
        //When it encounters a computedQuestion parent, it will look up the referring nodes for that node, and call evaluate that node and it's parents,
        //until all necessary parents have been evaluated.

        String value = storedValues.get(node);
        for(Variable referringNode : referringNodes){
            storedValues.put(referringNode, value);
            evaluate(referringNode);
        }

    }

    private void evaluate(Expression node) {

    }

    private void evaluate(IfStatement node) {

    }

    public String get(String varName) {
        return storedValues.get(varName);
    }

    public void start(Form form) {
        ReferenceMapper referenceMapper = new ReferenceMapper();
        referenceMap = referenceMapper.getMap(form);
        ParentMapper parentMapper = new ParentMapper();
        parentMap = parentMapper.getMap(form);
    }
}
