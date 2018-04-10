package qls.ast.visitors;

import qls.ast.Page;
import qls.ast.Stylesheet;

public interface StylesheetPageVisitor<T> {

    T visit(Stylesheet stylesheet);

    T visit(Page page);

}
