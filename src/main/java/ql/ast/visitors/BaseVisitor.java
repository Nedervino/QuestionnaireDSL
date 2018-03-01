package ql.ast.visitors;

import ql.ast.FormNode;

public interface BaseVisitor<T> {

    T visit(FormNode formNode);

}
