package ql;

import org.antlr.v4.runtime.*;
//import org.antlr.v4.gui.*;
//
//import ql.QLBaseListener;
//import ql.QLParser;
//import ql.QLLexer;

import java.io.IOException;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class FormGenerator {

    public void start(String fileName) {

        CharStream inputStream = null;
        try {
            inputStream = CharStreams.fromFileName(fileName);
        } catch (IOException e) {
            System.err.println("Couldn't find source file: " + e.getMessage());
        }

        QLLexer lexer = new QLLexer(inputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(commonTokenStream);

        ParseTreeWalker walker = new ParseTreeWalker();
//
//
//        QLParser.FileContext fileContext = QLParser.file();
//        QLVisitor visitor = new QLVisitor();
//        visitor.visit(fileContext);
//
//        ANTLRStringStream in = new ANTLRStringStream("hello parrrt");
//        QLLexer lexer = new QLLexer(in);
//        CommonTokenStream tokens = new CommonTokenStream(lexer);
//        QLParser parser = new QLParser(tokens);
//        parser.eval();
    }

    public static void main(String[] args) {

    }

}
