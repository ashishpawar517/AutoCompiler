// package omitted currently
// package test;
import java.util.*;
import java.util.stream.Collectors;

import javax.tools.*;
import javax.tools.JavaCompiler.*;

//class name will be renamed afterwards
public class Check {
    public static void main(String[] args) throws Exception {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        MyDiagnosticListener listener = new MyDiagnosticListener(); 
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(listener, null, null); 
       
       
        //file name using command line argument
        String fileToCompile = args[0];
       
        Iterable fileObjects = fileManager.getJavaFileObjectsFromStrings(Arrays.asList(fileToCompile)); 
        CompilationTask task = compiler.getTask(null, fileManager, listener, null, null, fileObjects); 
        
        
        
        Boolean result = task.call(); // compile the file 
        if (result == true) {
            System.out.println("Compilation has succeeded");
        }
        //print the list each tuple has 3 values error type, position and what symbol is expected
        // System.out.println(listener.getList());
        List<Tuple<String>> list = listener.getList();
        // sort using positions of errors
        list = list.stream().sorted(Comparator.comparing(Tuple::getSecond)).collect(Collectors.toList());
    
        // System.out.println(list);
       
        RandomWriter writer = new RandomWriter();
        for (Tuple<String> tuple : list) {
            String error = tuple.getThird();
            System.out.println(error.charAt(1));
            String pos = tuple.getSecond();
            writer.writeToFile(fileToCompile, String.valueOf(error.charAt(1)), Long.parseLong(pos));

        }
    }
}
