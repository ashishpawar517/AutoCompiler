
// AdvancedCompilationTest.java:
// package test;
// import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import javax.tools.*;
import javax.tools.JavaCompiler.*;
import java.io.*;

public class Check {
    public static void main(String[] args) throws Exception {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        MyDiagnosticListener listener = new MyDiagnosticListener(); 
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(listener, null, null); 
        String fileToCompile = args[0];
        Iterable fileObjects = fileManager.getJavaFileObjectsFromStrings(Arrays.asList(fileToCompile)); 
        CompilationTask task = compiler.getTask(null, fileManager, listener, null, null, fileObjects); 
        Boolean result = task.call(); // Line 7
        
        // System.out.println(listener.getList());
        List<Tuple<String>> list = listener.getList();
        list = list.stream().sorted(Comparator.comparing(Tuple::getSecond)).collect(Collectors.toList());
    
        //System.out.println(list);
        if (result != true)
            System.out.println("Missing Elements : ");
        RandomWriter writer = new RandomWriter();
        for (Tuple<String> tuple : list) {
            String error = tuple.getThird();
            System.out.print(error.charAt(1)+"\t");
            String pos = tuple.getSecond();
            writer.writeToFile(fileToCompile, String.valueOf(error.charAt(1)), Long.parseLong(pos));
        }
        result=true;
        if (result == true) {
            System.out.println("\nCompilation has succeeded");
        }
    }
}

// class MyDiagnosticListener implements DiagnosticListener {

//     List<Tuple<String>> li;
//     MyDiagnosticListener()
//     {
//         li = new ArrayList<>();
//     }
//     public void report(Diagnostic diagnostic) {

//         // System.out.println("Code->" + diagnostic.getCode());
//         // System.out.println("Column Number->" + diagnostic.getColumnNumber());
//         // System.out.println("End Position->" + diagnostic.getEndPosition());
//         // System.out.println("Kind->" + diagnostic.getKind());
//         // System.out.println("Line Number->" + diagnostic.getLineNumber());
//         // System.out.println("Message->" + diagnostic.getMessage(Locale.ENGLISH));
//         // System.out.println("Position->" + diagnostic.getPosition());
//         // System.out.println("Source" + diagnostic.getSource());
//         // System.out.println("Start Position->" + diagnostic.getStartPosition());
//         // System.out.println("\n");

//         li.add(new Tuple<String> (diagnostic.getCode(), String.valueOf(
//                 diagnostic.getPosition()),diagnostic.getMessage(Locale.ENGLISH) ));

//     }

//     public List<Tuple<String>> getList()
//     {
//         return this.li;
//     }
// }