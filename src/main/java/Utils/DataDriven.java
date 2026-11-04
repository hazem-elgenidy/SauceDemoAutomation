package Utils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataDriven {

    // Relative to the project folder, so it works on any computer and in CI
    private static final String DEFAULT_PATH = "src/test/TestData.json";

    /**
     * Reads the JSON file and returns the root JsonObject.
     * Caller can pick fields like root.getAsJsonObject("valid").get("username").getAsString()
     */
    public static JsonObject jsonReader() {
        return jsonReader(DEFAULT_PATH);
    }

    public static JsonObject jsonReader(String path) {
        try {
            String content = Files.readString(Path.of(path));
            return JsonParser.parseString(content).getAsJsonObject();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON file at: " + path, e);
        }
    }
}
