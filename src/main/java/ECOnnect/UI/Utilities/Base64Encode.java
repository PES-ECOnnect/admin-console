package ECOnnect.UI.Utilities;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class Base64Encode {
    public static String encode(String filePath) {
        Path path = Paths.get(filePath);
        byte[] file;
        try {
            file = Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException("Could not read file: " + filePath, e);
        }
        byte[] encoded = Base64.getEncoder().encode(file);
        return new String(encoded, StandardCharsets.US_ASCII);
    }
}
