package view;

import org.antlr.v4.runtime.tree.ParseTree;
import ql.QLParser;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class FormView extends JPanel{

    public void start(ParseTree parseTree) {


        /*TODO
        build a linkedlist of unique identifiers for each element in the tree
        do this by using a visitor which visits each node and adds the children to the linkedlist.
        This will serve as the default rendering method

        store each element in a hashmap
        the keys of this hashmap can be referred to in the QLS to stylize the form.
        the objects these keys refer to determine how the input field or (output) text will look.
        errors in the qls should be recognized as well, so we want to 'typecheck' the qls perhaps so that a spinwheel for all possible integers gives
        an error.
         */

        init();

        DefaultRenderVisitor visitor = new DefaultRenderVisitor(this);
        visitor.visit(parseTree);

    }


    private void init() {
        JFrame frame = new JFrame("Form Viewer");
        JPanel panel = this;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400,400);
        frame.setVisible(true);
        frame.add(panel);
    }

    public void renderInput(QLParser.InputContext ctx) {

    }

    public void renderOutput(QLParser.OutputContext ctx) {
        
    }
}
