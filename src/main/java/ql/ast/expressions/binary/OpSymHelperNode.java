package ql.ast.expressions.binary;

import ql.ast.ASTNode;
import ql.ast.ASTVisitor;


//Only purpose of this class is to extract the content of a TerminalNode. Depending on a particular symbol, we will just create a specific operation node, such as
//AddNode, NegNode etc. Since these classes are specific for each operation symbol, we don't need to store the symbol anymore either.

public class OpSymHelperNode extends ASTNode {

    private String content;

    public OpSymHelperNode(String content) {
        this.setContent(content);
    }

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitOpSym(this);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
