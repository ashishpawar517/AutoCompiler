import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Reading Lines from the file
public class RandomReader {

    String getLine(String filename, long lineNumber) throws IOException {
        // String result = null;
        // BufferedReader br = null;
        // try {
        //     FileInputStream fs = new FileInputStream(filename);
        //     br = new BufferedReader(new InputStreamReader(fs));
        //     for (int i = 0; i < lineNumber; ++i)
        //         br.readLine();
        //     result = br.readLine();
        //     System.out.println(result);
        // } catch (Exception e) {
        //     System.out.println("ERROR :" + e.getMessage());
        // } finally {
        //     br.close();
        // }
        // return result;
        FileReader tempFileReader = null;
        BufferedReader tempBufferedReader = null;
        try {
            tempFileReader = new FileReader(filename);
            tempBufferedReader = new BufferedReader(tempFileReader);
        } catch (Exception e) {
        }
        String returnStr = "ERROR";
        for (int i = 0; i < lineNumber - 1; i++) {
            try {
                tempBufferedReader.readLine();
            } catch (Exception e) {
            }
        }
        try {
            returnStr = tempBufferedReader.readLine();
        } catch (Exception e) {
        }

        return returnStr;
    }
}