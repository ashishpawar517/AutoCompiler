import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomWriter {
    

     void writeToFile(String filePath, String data, long position) throws IOException {
        RandomAccessFile file = new RandomAccessFile(filePath, "rw");
        file.seek(position);
        file.write(data.getBytes());
        file.seek(position+1);
        file.write("\n".getBytes());
        file.close();
    }
}