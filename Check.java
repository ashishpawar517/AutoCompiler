import java.util.*;
import java.util.stream.Collectors;

import javax.tools.*;
import javax.tools.JavaCompiler.*;
import java.io.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Check {
    public static void main(String[] args) throws Exception {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        MyDiagnosticListener listener = new MyDiagnosticListener(); 
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(listener, null, null); 
       
        String fileToCompile = args[0];
       
        Iterable fileObjects = fileManager.getJavaFileObjectsFromStrings(Arrays.asList(fileToCompile)); 
        CompilationTask task = compiler.getTask(null, fileManager, listener, null, null, fileObjects); 

        Boolean result = task.call(); // compile the file 
        List<Tuple<String>> list = listener.getList();
        List<Tuple<String>> list_missing_symbols = list.stream()
        .filter(e->e.getThird().endsWith("expected")) //filter out only expected errors
        .sorted(Comparator.comparing(Tuple::getSecond))
        .collect(Collectors.toList());
    
        System.out.println(list_missing_symbols);
        RandomWriter writer = new RandomWriter();
        for (Tuple<String> tuple : list_missing_symbols) {
            String error = tuple.getThird();
            System.out.print(error +" " +error.charAt(1)+"\t");
            String pos = tuple.getSecond();
            writer.writeToFileWithNewLine(fileToCompile, String.valueOf(error.charAt(1)), Long.parseLong(pos));
        }
    
        System.out.println("missing symbols");
        List<Tuple<String>> otherErrors = list.stream()
        .filter(e -> !e.getThird().endsWith("expected")) 
        .sorted(Comparator.comparing(Tuple::getSecond))
        .collect(Collectors.toList());
    
        System.out.println(otherErrors);
        RandomReader reader = new RandomReader();
        for (Tuple<String> tuple : otherErrors) {
            
            String error  = tuple.getThird();
            error  = error.trim();
            String type = tuple.getFirst();
            
            if(error.equals("incompatible types: int[] cannot be converted to int")|| 
               error.equals("incompatible types: double[] cannot be converted to double") ||
               error.equals("incompatible types: float[] cannot be converted to float") 
                    )
            {
                long lineNumber = Long.parseLong(tuple.getForth());

                String line = reader.getLine(fileToCompile, lineNumber);
                StringBuffer sb = new StringBuffer(line.trim());
                int index = sb.indexOf("=");
                sb.insert(index - 1, "[]");
                writer.commentLine(fileToCompile, lineNumber-1);
                writer.writeTofileWithLineNumber(fileToCompile, sb.toString(), lineNumber);
            }
            if(error.equals("reached end of file while parsing"))
            {
                long lineNumber = Long.parseLong(tuple.getForth());
                writer.writeTofileWithLineNumber(fileToCompile, "}",lineNumber );
            }
             if(type.equals("compiler.err.class.public.should.be.in.file"))
            {
                long lineNumber = Long.parseLong(tuple.getForth());
                writer.commentLine(fileToCompile, lineNumber - 1);
                String classname = fileToCompile.split("[.]")[0];
                writer.writeTofileWithLineNumber(fileToCompile, "public class "+classname+"{", lineNumber);
            }

            else
            {
                System.out.println("Currently not support for this error ");
            }
        }

        System.out.println("done 2");
        if (result) {
            System.out.println("\nCompilation has succeeded");
        }

        // ANTLR parsing and error handling
        CharStream input = CharStreams.fromFileName(fileToCompile);
        JavaGrammarLexer lexer = new JavaGrammarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JavaGrammarParser parser = new JavaGrammarParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new ANTLRErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                System.err.println("line " + line + ":" + charPositionInLine + " " + msg);
                try {
                    writer.commentLine(fileToCompile, line - 1);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact, BitSet ambigAlts, ATNConfigSet configs) {
            }

            @Override
            public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex, BitSet conflictingAlts, ATNConfigSet configs) {
            }

            @Override
            public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction, ATNConfigSet configs) {
            }
        });

        parser.compilationUnit();
    }
}
