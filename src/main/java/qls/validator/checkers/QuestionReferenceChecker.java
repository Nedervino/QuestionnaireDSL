package qls.validator.checkers;

import ql.ast.Form;
import ql.ast.SourceLocation;
import ql.ast.statements.Question;
import ql.validator.checkers.BaseChecker;
import ql.validator.symboltable.QuestionCollector;
import qls.ast.Page;
import qls.ast.Stylesheet;
import qls.ast.components.Component;
import qls.ast.components.QuestionReference;
import qls.ast.components.Section;
import qls.ast.visitors.ComponentVisitor;
import qls.ast.visitors.StylesheetPageVisitor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Checks Form and Stylesheet for duplicate references, references to non-existent ql questions,
 * and missing references for existing ql questions
 */
public class QuestionReferenceChecker extends BaseChecker  {

    public QuestionReferenceChecker(Form form, Stylesheet stylesheet) {
        List<String> questionIds = new QuestionCollector(form).getQuestions()
                .stream()
                .map(Question::getId)
                .collect(Collectors.toList());
        List<QuestionReference> questionReferences = new QuestionReferenceCollector(stylesheet).getQuestionReferences();

        List<String> questionReferenceIds = questionReferences
                .stream()
                .map(QuestionReference::getQuestionId)
                .collect(Collectors.toList());

        Set<String> uniqueReferences = new HashSet<>();
        List<QuestionReference> duplicateQuestionReferences = questionReferences.stream()
                .filter(r -> !uniqueReferences.add(r.getQuestionId()))
                .collect(Collectors.toList());

        for(QuestionReference question : duplicateQuestionReferences) {
            issueTracker.addError(question.getSourceLocation(), String.format("Multiple references for question \"%s\"", question.getQuestionId()));
        }

        List<String> nonExistingReferences = new ArrayList<>(questionReferenceIds);
        nonExistingReferences.removeAll(questionIds);

        //TODO
        for(String questionId : nonExistingReferences) {
            issueTracker.addError(new SourceLocation(0,0), String.format("Stylesheet contains reference to non-existing question \"%s\"", questionId));
        }

        List<String> nonReferredQuestions = new ArrayList<>(questionIds);
        nonReferredQuestions.removeAll(questionReferenceIds);

        //TODO
        for(String questionId : nonReferredQuestions) {
            issueTracker.addError(new SourceLocation(0,0), String.format("Stylesheet misses reference to question \"%s\"", questionId));
        }

    }


    private class QuestionReferenceCollector implements StylesheetPageVisitor<Void>, ComponentVisitor<Void> {

        private final List<QuestionReference> questionReferences;

        protected QuestionReferenceCollector(Stylesheet stylesheet) {
            questionReferences = new ArrayList<>();
            stylesheet.accept(this);
        }

        protected List<QuestionReference> getQuestionReferences() {
            return new ArrayList<>(questionReferences);
        }

        @Override
        public Void visit(Stylesheet stylesheet) {
            for(Page page : stylesheet.getPages()) {
                page.accept(this);
            }
            return null;
        }

        @Override
        public Void visit(Page page) {
            for(Component component : page.getComponents()) {
                component.accept(this);
            }
            return null;
        }

        @Override
        public Void visit(Section section) {
            for(Component component : section.getComponents()) {
                component.accept(this);
            }
            return null;
        }

        @Override
        public Void visit(QuestionReference questionReference) {
            questionReferences.add(questionReference);
            return null;
        }

    }

}
