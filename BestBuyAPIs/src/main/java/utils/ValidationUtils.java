package utils;

import io.restassured.response.Response;
import org.testng.Assert;

public class ValidationUtils {

    public static void validateStatusCode(Response response, int expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode, "Expected status code: " + expectedStatusCode);
    }

    public static void validateFieldIsNotNull(Response response, String jsonPath, String fieldName) {
        Assert.assertNotNull(response.jsonPath().get(jsonPath), fieldName + " should not be null");
    }

    public static void validateFieldEquals(Response response, String jsonPath, Object expectedValue, String fieldName) {
        Assert.assertEquals(response.jsonPath().get(jsonPath), expectedValue, fieldName + " should match");
    }
}
