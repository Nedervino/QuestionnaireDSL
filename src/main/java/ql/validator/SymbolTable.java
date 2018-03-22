package ql.validator;

import ql.ast.Form;
import ql.ast.statements.Question;
import ql.ast.types.Type;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Storage for encountered identifiers and their datatypes
 */
public class SymbolTable {

    private final Map<String, Type> table;

    public SymbolTable(Form form) {
        table = new HashMap<>();

        List<Question> questions = new QuestionCollector(form).getQuestions();
        for(Question question : questions) {
            table.put(question.getId(), question.getType());
        }
    }

    public SymbolTable() {
        this.table = new HashMap<>();
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
}
