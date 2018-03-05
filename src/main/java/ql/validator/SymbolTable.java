package ql.validator;

import ql.ast.expressions.IDNode;
import ql.ast.types.Type;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {

    private final Map<String, Type> table;

    public SymbolTable() {
        this.table = new HashMap();
    }

    public void declare(IDNode identifier, Type type) {
        table.put(identifier.toString(), type);
    }

    public boolean isDeclared(IDNode identifier) {
        return table.containsKey(identifier.toString());
    }

    public Type lookup(IDNode identifier) {
        return table.get(identifier.toString());
    }
}
