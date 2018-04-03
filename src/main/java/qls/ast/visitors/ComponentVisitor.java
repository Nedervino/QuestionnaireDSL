package qls.ast.visitors;

import qls.ast.components.QuestionReference;
import qls.ast.components.Section;

public interface ComponentVisitor<T> {

    T visit(Section section);

    T visit(QuestionReference questionReference);

}
