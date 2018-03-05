package ql.ast.visitors;

import ql.ast.Form;

public interface FormVisitor<T> {

    T visit(Form form);

}
