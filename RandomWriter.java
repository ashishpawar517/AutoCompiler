import java.io.IOException;
import java.io.RandomAccessFile;
// this class is used for writing specific missing symbols in source java file
public class RandomWriter {
    
    //write to file to peek to certain position in file then write the certain data onto file
     void writeToFile(String filePath, String data, long position) throws IOException {
        RandomAccessFile file = new RandomAccessFile(filePath, "rw");
        file.seek(position);
        file.write(data.getBytes());
        file.seek(position+1);
        file.write("\n".getBytes());
        file.close();
    }
}