package ql;

public class Main {
    public static void main (String[] args) {
        String filename = "src/input/ql/formIf.ql";

        FormGenerator formGen = new FormGenerator();
        formGen.start(filename);

        TypeChecker typeCheck = new TypeChecker();
        typeCheck.start(filename);
    }
}
