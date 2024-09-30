package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JsonDataReader {

    public JSONArray getJsonArray(String fileName, String key) {
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = null;

        try (FileReader reader = new FileReader("src/test/resources/" + fileName)) {
            Object obj = jsonParser.parse(reader);

            JSONObject jsonObject = (JSONObject) obj;
            jsonArray = (JSONArray) jsonObject.get(key);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }
}
