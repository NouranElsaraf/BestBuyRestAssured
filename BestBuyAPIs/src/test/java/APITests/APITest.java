package APITests;

import Base.BaseTest;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.EndPoints;
import utils.JsonDataReader;
import utils.ValidationUtils;

public class APITest extends BaseTest {

    // Data provider method to load JSON data
    @DataProvider(name = "productDataFromJson")
    public Object[][] getProductDataFromJson() {
        JsonDataReader jsonDataReader = new JsonDataReader();
        JSONArray products = jsonDataReader.getJsonArray("products.json", "products");

        Object[][] testData = new Object[products.size()][5];
        for (int i = 0; i < products.size(); i++) {
            JSONObject product = (JSONObject) products.get(i);
            testData[i][0] = product.get("name");
            testData[i][1] = product.get("type");
            testData[i][2] = Double.parseDouble(product.get("price").toString());
            testData[i][3] = product.get("upc");
            testData[i][4] = product.get("description");
            testData[i][5] = product.get("manufacturer");
        }

        return testData;
    }

    @Test(dataProvider = "productDataFromJson")
    public void createProductTest(String name, String type, double price, String upc, String description, String manufacturer) {
        String productBody = createProductJson(name, type, price, upc, description, manufacturer);

        Response response = restClient.postRequest(EndPoints.CREATE_PRODUCT, productBody);

        ValidationUtils.validateStatusCode(response, 201);
        ValidationUtils.validateFieldEquals(response, "name", name, "Product name");
        ValidationUtils.validateFieldEquals(response, "type", type, "Product type");
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
}
