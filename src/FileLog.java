import java.io.File;
import java.io.IOException; 

public class FileLog {
    public String fileName;

    public FileLog(String fileName) {
        this.fileName = fileName;
        
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    public void log(String message) {
        try {
            java.nio.file.Files.write(java.nio.file.Paths.get(fileName), (message + "\n").getBytes(), java.nio.file.StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
