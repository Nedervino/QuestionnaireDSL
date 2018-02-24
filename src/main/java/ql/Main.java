package ql;

public class Main {
    public static void main (String[] args) {
        String fileName = "src/input/ql/formIf.ql";
        FormGenerator formGenerator = new FormGenerator();
        formGenerator.loadFile(fileName);
    }
}