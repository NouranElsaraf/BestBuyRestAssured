package Client;

import utils.QueryParameters;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestClient {

    // Constructor to set base URI from ParameterFile
    public RestClient() {
        RestAssured.baseURI = QueryParameters.BASE_URI;
    }

    public io.restassured.response.Response getRequest(String endpoint) {
        return RestAssured
                .given()
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    public Response getRequestWithParam(String endpoint, int id) {
        return RestAssured
                .given()
                .pathParam("id", id)
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    public Response postRequest(String endpoint, String body) {
        return RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();
    }

    public Response deleteRequest(String endpoint, int id) {
        return RestAssured
                .given()
                .pathParam("id", id)
                .when()
                .delete(endpoint)
                .then()
                .extract()
                .response();
    }
}