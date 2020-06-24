import java.util.*;
import javax.tools.JavaCompiler.*;
import javax.tools.*;

public class MyDiagnosticListener implements DiagnosticListener {
    List<Long> pos;// = new ArrayList<>();

    MyDiagnosticListener() {
        pos = new ArrayList<>();
    }

    public void report(Diagnostic diagnostic) {

        // System.out.println("Code->" + diagnostic.getCode());
        // System.out.println("Column Number->" + diagnostic.getColumnNumber());
        // System.out.println("End Position->" + diagnostic.getEndPosition());
        // System.out.println("Kind->" + diagnostic.getKind());
        // System.out.println("Line Number->" + diagnostic.getLineNumber());
        System.out.println("Message->" + diagnostic.getMessage(Locale.ENGLISH));
        System.out.println("Position->" + diagnostic.getPosition());
        pos.add(diagnostic.getPosition());
        // System.out.println("Source" + diagnostic.getSource());
        // System.out.println("Start Position->" + diagnostic.getStartPosition());
        System.out.println("\n");
    }

    public List<Long> getPosition() {
        return pos;
    }
}