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

import java.util.*;
import java.util.stream.Collectors;

/**
 * Checks Form and Stylesheet for duplicate references, references to non-existent ql questions,
 * and missing references for existing ql questions
 */
public class QuestionReferenceChecker extends BaseChecker {

    public QuestionReferenceChecker(Form form, Stylesheet stylesheet) {
        List<String> questionIds = new QuestionCollector(form).getQuestions()
                .stream()
                .map(Question::getId)
                .collect(Collectors.toList());

        List<QuestionReference> questionReferences = new QuestionReferenceCollector(stylesheet).getQuestionReferences();

        Map<String, QuestionReference> questionReferenceMap = new HashMap<>();
        for (QuestionReference reference : questionReferences) {
            questionReferenceMap.put(reference.getQuestionId(), reference);
        }

        List<String> questionReferenceIds = questionReferences
                .stream()
                .map(QuestionReference::getQuestionId)
                .collect(Collectors.toList());

        findDuplicateReferences(questionReferences);

        findReferencesToNonExistingQuestions(questionReferenceIds, questionIds, questionReferenceMap);

        findMissingQuestionReferences(questionIds, questionReferenceIds);

    }


    private void findDuplicateReferences(List<QuestionReference> questionReferences) {
        Set<String> uniqueReferences = new HashSet<>();
        List<QuestionReference> duplicateQuestionReferences = questionReferences.stream()
                .filter(r -> !uniqueReferences.add(r.getQuestionId()))
                .collect(Collectors.toList());

        for (QuestionReference question : duplicateQuestionReferences) {
            issueTracker.addError(question.getSourceLocation(), String.format("Multiple references for question \"%s\"", question.getQuestionId()));
        }
    }

    private void findReferencesToNonExistingQuestions(List<String> questionReferenceIds, List<String> questionIds, Map<String, QuestionReference> questionReferenceMap) {
        List<String> nonExistingReferences = new ArrayList<>(questionReferenceIds);
        nonExistingReferences.removeAll(questionIds);

        for (String questionId : nonExistingReferences) {
            QuestionReference reference = questionReferenceMap.get(questionId);
            issueTracker.addError(reference.getSourceLocation(), String.format("Stylesheet contains reference to non-existing question \"%s\"", questionId));
        }
    }

    private void findMissingQuestionReferences(List<String> questionIds, List<String> questionReferenceIds) {
        List<String> nonReferredQuestions = new ArrayList<>(questionIds);
        nonReferredQuestions.removeAll(questionReferenceIds);

        for (String questionId : nonReferredQuestions) {
            issueTracker.addError(new SourceLocation(0, 0), String.format("Stylesheet misses reference to question \"%s\"", questionId));
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
            for (Page page : stylesheet.getPages()) {
                page.accept(this);
            }
            return null;
        }

        @Override
        public Void visit(Page page) {
            for (Component component : page.getComponents()) {
                component.accept(this);
            }
            return null;
        }

        @Override
        public Void visit(Section section) {
            for (Component component : section.getComponents()) {
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
