package ql.evaluator;

import ql.ast.Form;
import ql.ast.expressions.Expression;
import ql.ast.expressions.Variable;
import ql.ast.statements.Question;
import ql.gui.FormView;

import java.util.HashMap;
import java.util.List;


//The state will hold all currently calculated values. Not just the values of variables, but also all their parent calculations. This way,
//when we change an input variable, we can invalidate all the referring nodes and their vars, without having to recalculate everything.

public class Evaluator {

    FormView formView;
    HashMap<Question, String> storedValue;
    HashMap<String, List<Variable>> referenceMap;

    public void setFormView(FormView formView){
        this.formView = formView;
    }

    public void update(Question node, String value) {

        //Recognize whether answers to question match the declared type

        //Update what value is stored at this node in the current state
        storedValue.put(node, value);
        evaluate(node);


        //repaint the GUI
        formView.repaint();
    }

    private void evaluate(Question node) {
        //Call the evaluator to calculate the new values of the referring nodes, and their parents. Deposit these in the Evaluator object.
        String varName = node.getId();
        List<Variable> referringNodes = referenceMap.get(varName);
        //Starting at the first reference node, we update the value of the reference node in the state object,
        //then we evaluate its parent. This will be an overloaded method. When it encounters expressions it evaluates and stores their results.
        //When it encounters a computedQuestion parent, it will look up the referring nodes for that node, and call evaluate that node and it's parents,
        //until all necessary parents have been evaluated.
    }

    private void evaluate(Expression node){

    }

    public String get(String varName) {
        return storedValue.get(varName);
    }

    public void start(Form form) {
        ReferenceMapVisitor referenceMapper = new ReferenceMapVisitor();
        referenceMap = referenceMapper.getMap(form);
    }
}
