import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Arrays;
public class Parser
{
    private String source_code;
    private String[] code_newline_separated;
    //source code should be newline separated lines
    Parser(String source_code)
    {
        this.source_code = source_code;
        this.code_newline_separated = source_code.split("\n");
    }

    //check brackets
    public void checkBrackets()
    {

    }


    //check class (keyword) class-name and two brackets { }
    public List<String> checkClass()
    {
        List<String> res = new ArrayList<>();
        // System.out.println(Arrays.toString(arr));

        // Pattern p = Matcher.
        Matcher m = Pattern.compile("(class)\\s([A-Z][a-z]*)(\\{)(.*)(\\})").matcher(code_newline_separated[0]);
        if(m.lookingAt())
        {
            System.out.println("given file matches proper className template");
            System.out.println(m.group(0));
        }
        
        
        return res;
    }    

    // return all methods which are correct matches proper method regex
    public List<String> methods()
    {
        List<String> res = new ArrayList<>();

        return res;
    }


    public static void main(String[] args) {
        String non_parsed_text = "class Demo {\n public static void main(String args[]){\n }\n}";
        
        Parser p = new Parser(non_parsed_text);

        List<String> class_out = p.checkClass();
        
        // System.out.println(non_parsed_text);
        // System.out.println(class_out);
    }
}