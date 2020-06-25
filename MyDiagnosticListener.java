import java.util.*;
import javax.tools.*;
// for each time task.call is fired report method of MyDiagnosticListener is fired
public class MyDiagnosticListener implements DiagnosticListener {

    //list of tuples for storing error information
    List<Tuple<String>> li;
    MyDiagnosticListener()
    {
        li = new ArrayList<>();
    }
    public void report(Diagnostic diagnostic) {

        // System.out.println("Code->" + diagnostic.getCode());
        // System.out.println("Column Number->" + diagnostic.getColumnNumber());
        // System.out.println("End Position->" + diagnostic.getEndPosition());
        // System.out.println("Kind->" + diagnostic.getKind());
        // System.out.println("Line Number->" + diagnostic.getLineNumber());
        // System.out.println("Message->" + diagnostic.getMessage(Locale.ENGLISH));
        // System.out.println("Position->" + diagnostic.getPosition());
        // System.out.println("Source" + diagnostic.getSource());
        // System.out.println("Start Position->" + diagnostic.getStartPosition());
        // System.out.println("\n");

        //adding error code, position, error message to list for each error
        li.add(new Tuple<String> (diagnostic.getCode(), String.valueOf(
                diagnostic.getPosition()),diagnostic.getMessage(Locale.ENGLISH) ));

    }

    //return the current list
    public List<Tuple<String>> getList()
    {
        return this.li;
    }
}