import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import javax.tools.*;

// public class AutoSemiColon {
//     public static void main(String[] args) {

//         // String filename = args[0];
//         // String command = "ls";
//         // System.out.println(command);
//         // try {
//         // Process process = Runtime.getRuntime().exec(command);

//         // BufferedReader reader = new BufferedReader(new
//         // InputStreamReader(process.getInputStream()));
//         // StringBuffer out = new StringBuffer();
//         // String line;
//         // while ((line = reader.readLine()) != null) {
//         // System.out.println(line);
//         // out.append(line);/
//         // out.append("\n");
//         // }
//         // System.out.println(out);
//         // reader.close();

//         // } catch (IOException e) {
//         // e.printStackTrace();
//         // }

//         // String fileToCompile = "Demo.java";

//         // JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
//         // try {
//         //     FileOutputStream errorStream = new FileOutputStream("Errors.txt");
//         //     int compilationResult = compiler.run(null, null, errorStream, "-verbose", fileToCompile);

//         //     if (compilationResult == 0) {

//         //         System.out.println("Compilation is successful");

//         //     } else {

//         //         System.out.println("Compilation Failed");

//         //     }

//         // } catch (FileNotFoundException e) {
//         //     e.printStackTrace();
//         // }

//     }
// }

// https://docs.oracle.com/javase/6/docs/api/javax/tools/JavaCompiler.html

import java.util.*;
import javax.tools.JavaCompiler.*;
import java.io.RandomAccessFile;

public class AutoSemiColon {
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
        // System.out.println(pos);
        int count = 0;
        pos.sort(Comparator.naturalOrder());
        for (long p : pos) {
            writeToFile(args[0], ";", p);
            count++;
        }
        System.out.println("Semicolon Added " + count);

    }

    static void writeToFile(String filePath, String data, long position) throws IOException {
        RandomAccessFile file = new RandomAccessFile(filePath, "rw");
        file.seek(position);
        file.write(data.getBytes());
        file.close();
    }
}
