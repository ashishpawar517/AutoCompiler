
// https://docs.oracle.com/javase/6/docs/api/javax/tools/JavaCompiler.html

import java.io.*;
import java.util.*;
import javax.tools.*;
import javax.tools.JavaCompiler.*;

import java.io.RandomAccessFile;

public class AdvancedCompilationTest {
    public static void main(String[] args) throws Exception {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        MyDiagnosticListener listener = new MyDiagnosticListener();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(listener, null, null);
        String fileToCompile = args[0];
        Iterable fileObjects = fileManager.getJavaFileObjectsFromStrings(Arrays.asList(fileToCompile));
        CompilationTask task = compiler.getTask(null, fileManager, listener, null, null, fileObjects);
        Boolean result = task.call();

        if (result == true) {
            System.out.println("Compilation has succeeded");
        }

        List<Long> pos = listener.getPosition();
        System.out.println(pos);
        int count = 0;
        pos.sort(Comparator.naturalOrder());
        for (long p : pos) {
            writeToFile(args[0], ";", p);
            count++;
        }
        System.out.println("Semicolon Added "+count);

    }

    static void writeToFile(String filePath, String data, long position) throws IOException {
        RandomAccessFile file = new RandomAccessFile(filePath, "rw");
        file.seek(position);
        file.write(data.getBytes());
        file.close();
    }
}

// class MyDiagnosticListener implements DiagnosticListener {
//     List<Long> pos ;//= new ArrayList<>();

//     MyDiagnosticListener()
//     {
//         pos = new ArrayList<>();
//     }
//     public void report(Diagnostic diagnostic) {

//         // System.out.println("Code->" + diagnostic.getCode());
//         // System.out.println("Column Number->" + diagnostic.getColumnNumber());
//         // System.out.println("End Position->" + diagnostic.getEndPosition());
//         // System.out.println("Kind->" + diagnostic.getKind());
//         // System.out.println("Line Number->" + diagnostic.getLineNumber());
//         System.out.println("Message->" + diagnostic.getMessage(Locale.ENGLISH));
//         System.out.println("Position->" + diagnostic.getPosition());
//         pos.add(diagnostic.getPosition());
//         // System.out.println("Source" + diagnostic.getSource());
//         // System.out.println("Start Position->" + diagnostic.getStartPosition());
//         System.out.println("\n");
//     }

//     public List<Long> getPosition() {
//         return pos;
//     }
// }