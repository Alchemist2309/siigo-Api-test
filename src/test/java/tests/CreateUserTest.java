package tests;

import base.BaseTest;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class CreateUserTest extends BaseTest {

    @Test
    @Title("Create a new user")
    public void testCreateUser() {
        JSONObject userJson = new JSONObject();
        userJson.put("name", "John Doe");
        userJson.put("job", "QA Engineer");
        sendPostRequest("/api/users", userJson.toString());
        verifyStatusCode(201);
        String actualName = response.jsonPath().getString("name");
        Assert.assertEquals("John Doe", actualName);
        String actualJob = response.jsonPath().getString("job");
        Assert.assertEquals("QA Engineer", actualJob);
        verifyFieldExists("id");
        verifyFieldExists("createdAt");
    }
}
