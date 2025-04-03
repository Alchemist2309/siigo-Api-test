package base;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;


public class BaseTest {
    private static final String BASE_URL = "https://reqres.in";
    protected Response response;
    protected RequestSpecification givenBaseSpec(){
        return SerenityRest.given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json");
    }

    @Step("Sende Get request to {0}")
    public void sendGetRequest(String endpoint){
        response = givenBaseSpec().when().get(endpoint);
    }

    @Step("send POST request to {0} with body")
    public void sendPostRequest(String endpoint, Object requestBody){
        response = givenBaseSpec()
                .body(requestBody)
                .when()
                .post(endpoint);
    }

    @Step("send PUT request to {0} whit body")
    public void sendPutRequest(String endpoint, Object requestBody){
        response = givenBaseSpec()
                .body(requestBody)
                .when()
                .put(endpoint);
    }

    @Step("send Delete request to {0}")
    public void sendDeleteRequest(String endpoint){
        response = givenBaseSpec()
                .when()
                .delete(endpoint);
    }

    @Step("verify response status code is {0}")
    public void verifyStatusCode(int expectedStatusCode){
        response.then().statusCode(expectedStatusCode);
    }

    @Step("verify field {0} exists in response")
    public void verifyFieldExists(String fieldName){
        Object fieldValue = response.jsonPath().get(fieldName);
        Assert.assertNotNull("Field '" + fieldName + "' should not be null", fieldValue);
    }
}