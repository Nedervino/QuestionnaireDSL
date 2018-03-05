package ql.evaluator;

import ql.ast.BaseASTVisitor;

public class EvaluationVisitor extends BaseASTVisitor<EvaluationNode> {

    //GUI shows current state, with text boxes where relevant

    //listeners on text boxes will call propose changes to, and invalidate the current state (specifically the changed node and it's parents/nodes which refer to this node) (graph?)

    //changes to current state will call changes to validator (preferably just part of the tree)

    //after passing the type check, this will call to evaluator

    //evaluator updates the current state, and validates it. The GUI is then invalidated. (or do this invalidation call at the start which sets everything in motion)


}
