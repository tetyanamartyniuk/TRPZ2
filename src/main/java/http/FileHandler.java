package http;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHandler {

    public String readFile(String path) throws IOException {

        if (!Files.exists(Paths.get(path))) {
            throw new IOException("File not found: " + path);
        }

        return new String(Files.readAllBytes(Paths.get(path)));
    }
}

