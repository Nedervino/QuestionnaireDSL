package ql.validator.symboltable;

import ql.ast.Form;
import ql.ast.statements.*;
import ql.ast.types.Type;
import ql.ast.visitors.FormStatementVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Storage for encountered identifiers and their datatypes
 */
public class SymbolTable {

    private final Map<String, Type> table;

    public SymbolTable() {
        this.table = new HashMap<>();
    }

    public SymbolTable(Form form) {
        table = new HashMap<>();

        List<Question> questions = new QuestionCollector(form).getQuestions();
        for (Question question : questions) {
            table.put(question.getId(), question.getType());
        }
    }

    public void declare(String identifier, Type type) {
        table.put(identifier, type);
    }

    public boolean isDeclared(String identifier) {
        return table.containsKey(identifier);
    }

    public Type lookup(String identifier) {
        return table.get(identifier);
    }


    private class QuestionCollector implements FormStatementVisitor<Void> {

        private final List<Question> questions;

        protected QuestionCollector(Form form) {
            questions = new ArrayList<>();
            form.accept(this);
        }

        protected List<Question> getQuestions() {
            return new ArrayList<>(questions);
        }

        @Override
        public Void visit(Form form) {
            for (Statement statement : form.getStatements()) {
                statement.accept(this);
            }
            return null;
        }

        @Override
        public Void visit(IfStatement ifStatement) {
            for (Statement statement : ifStatement.getIfStatements()) {
                statement.accept(this);
            }
            return null;
        }

        @Override
        public Void visit(IfElseStatement ifElseStatement) {
            for (Statement statement : ifElseStatement.getIfStatements()) {
                statement.accept(this);
            }
            for (Statement statement : ifElseStatement.getElseStatements()) {
                statement.accept(this);
            }
            return null;
        }

        @Override
        public Void visit(Question question) {
            questions.add(question);
            return null;
        }

        @Override
        public Void visit(ComputedQuestion computedQuestion) {
            questions.add(computedQuestion);
            return null;
        }

    }

}
