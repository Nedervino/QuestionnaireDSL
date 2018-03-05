package ql.validator;

import ql.ast.expressions.Variable;
import ql.ast.types.Type;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {

    private final Map<String, Type> table;

    public SymbolTable() {
        this.table = new HashMap();
    }

    public void declare(Variable variable, Type type) {
        table.put(variable.toString(), type);
    }

    public boolean isDeclared(Variable variable) {
        return table.containsKey(variable.toString());
    }

    public Type lookup(Variable variable) {
        return table.get(variable.toString());
    }
}
