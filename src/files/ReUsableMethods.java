package files;

import io.restassured.path.json.JsonPath;

public class ReUsableMethods {

    public static JsonPath stringToJson(String responseBody){
        var jsonResponse = new JsonPath(responseBody);
        return jsonResponse;
    }
}
