// AdvancedCompilationTest.java:
// package test;
import java.io.*;
import java.util.*;
import javax.tools.*;
import javax.tools.JavaCompiler.*;
public class Check {
	public static void main(String[] args) throws Exception {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
// Line 1.
		MyDiagnosticListener1 listener = new MyDiagnosticListener1(); // Line 2.
		StandardJavaFileManager fileManager  =
		compiler.getStandardFileManager(listener, null, null); // Line 3.
String fileToCompile = "Demo.java";
// Line 4
		Iterable fileObjects =						fileManager.getJavaFileObjectsFromStrings(
Arrays.asList(fileToCompile));  // Line 5
CompilationTask task = compiler.getTask(null, fileManager, listener, null, null, fileObjects); // Line 6
		Boolean result = task.call(); // Line 7
		if(result == true){
			System.out.println("Compilation has succeeded");
		}
	}
}
class MyDiagnosticListener1 implements DiagnosticListener{
	public void report(Diagnostic diagnostic) {
		System.out.println("Code->" +  diagnostic.getCode());
		System.out.println("Column Number->" + diagnostic.getColumnNumber());
		System.out.println("End Position->" + diagnostic.getEndPosition());
		System.out.println("Kind->" + diagnostic.getKind());
		System.out.println("Line Number->" + diagnostic.getLineNumber());
		System.out.println("Message->"+ diagnostic.getMessage(Locale.ENGLISH));
		System.out.println("Position->" + diagnostic.getPosition());
		System.out.println("Source" + diagnostic.getSource());
		System.out.println("Start Position->" + diagnostic.getStartPosition());
		System.out.println("\n");
	}
}