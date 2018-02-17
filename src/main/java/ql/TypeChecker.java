package ql;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;


public class TypeChecker {


    public void start(String fileName) {

        CharStream inputStream = null;
        try {
            inputStream = CharStreams.fromFileName(fileName);
        } catch (IOException e) {
            System.err.println("Couldn't find source file: " + e.getMessage());
        }

        QLLexer lexer = new QLLexer(inputStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokenStream);
        // parser.removeErrorListeners();
        ParseTree parseTree = parser.form();


        FormGenerator formGen = new FormGenerator();
        formGen.start("src/input/ql/form2.ql");


        //TODO
        //Write wrapper for each tree node
        //wrapper holds the original node
        //children are copied to wrapper node
        //wrapper holds type info
        //Now we can determine the type of each node



        TypeCheckVisitor visitor = new TypeCheckVisitor();

        visitor.visit(parseTree);

        System.out.println("No type conflicts found.");
    }

}
