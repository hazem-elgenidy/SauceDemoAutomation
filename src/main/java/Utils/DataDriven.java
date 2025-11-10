package Utils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataDriven {

    private static final String DEFAULT_PATH = "C:\\Users\\Admin\\IdeaProjects\\SauceDemoAutomation\\src\\test\\TestData.json";

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
