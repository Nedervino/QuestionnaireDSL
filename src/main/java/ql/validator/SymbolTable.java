package ql.validator;

import ql.ast.types.Type;

import java.util.HashMap;
import java.util.Map;


/**
 * Storage for encountered identifiers and their datatypes
 */
public class SymbolTable {

    private final Map<String, Type> table;

    public SymbolTable() {
        this.table = new HashMap();
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
