package ql.ast.visitors;

import ql.ast.FormNode;

public interface FormVisitor<T> {

    T visit(FormNode formNode);

}
