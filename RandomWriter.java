import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
// this class is used for writing specific missing symbols in source java file
public class RandomWriter {
    
    //write to file to peek to certain position in file then write the certain data onto file
     void writeToFileWithNewLine(String filePath, String data, long position) throws IOException {
        RandomAccessFile file = new RandomAccessFile(filePath, "rw");
        file.seek(position);
        file.write(data.getBytes());
        file.seek(position+1);
        file.write("\n".getBytes());
        file.close();
    }

    void writeToFile(String filePath, String data, long position) throws IOException {
        RandomAccessFile file = new RandomAccessFile(filePath, "rw");
        file.seek(position);
        file.write(data.getBytes());
        file.seek(position + 1);
        file.write("\n".getBytes());
        file.close();
    }

    void writeTofileWithLineNumber(String filepath, String data, long lineNumber) throws IOException
    {
        Path p = Paths.get(filepath);
        List<String> lines = Files.readAllLines(p);
    //     // lines.set(line, dataType.toUpperCase() + ":" + newData);
        lines.set((int) lineNumber, data);
        Files.write(p, lines); // You can add a charset and other options too
 
    }
}