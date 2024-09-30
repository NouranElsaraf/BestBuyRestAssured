package APITests;

import Base.BaseTest;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;
import utils.EndPoints;
import utils.QueryParameters;
import utils.ValidationUtils;

public class APITest extends BaseTest {

    @Test
    public void getAllProductsTest() {
        Response response = restClient.getRequest(EndPoints.GET_PRODUCTS);

        // Reuse validation utility for status code
        ValidationUtils.validateStatusCode(response, 200);
    }

    @Test
    public void getProductByIdTest() {
        Response response = restClient.getRequestWithParam(EndPoints.GET_PRODUCT_BY_ID, QueryParameters.DEFAULT_PRODUCT_ID);

        // Reuse validation utilities
        ValidationUtils.validateStatusCode(response, 200);
        ValidationUtils.validateFieldIsNotNull(response, "name", "Product name");
    }

    @Test
    public void createProductTest() {
        String productBody = createProductJson("New Product", "Electronics", 299.99, "123456789012", "A new electronic product", "BestBuy");

        Response response = restClient.postRequest(EndPoints.CREATE_PRODUCT, productBody);

        // Reuse validation utilities
        ValidationUtils.validateStatusCode(response, 201);
        ValidationUtils.validateFieldEquals(response, "name", "New Product", "Product name");
        ValidationUtils.validateFieldEquals(response, "type", "Electronics", "Product type");
    }

    private String createProductJson(String name, String type, double price, String upc, String description, String manufacturer) {
        JSONObject productJson = new JSONObject();
        productJson.put("name", name);
        productJson.put("type", type);
        productJson.put("price", price);
        productJson.put("upc", upc);
        productJson.put("description", description);
        productJson.put("manufacturer", manufacturer);

        return productJson.toString();
    }

    @Test
    public void deleteProductTest() {
        Response response = restClient.deleteRequest(EndPoints.DELETE_PRODUCT, QueryParameters.DEFAULT_PRODUCT_ID);

        // Reuse validation utility for status code
        ValidationUtils.validateStatusCode(response, 200);
    }
}
