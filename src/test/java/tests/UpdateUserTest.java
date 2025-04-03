package tests;

import base.BaseTest;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class UpdateUserTest extends BaseTest {

    @Test
    @Title("Update a user")
    public void testUpdateUser() {
        // Prepare user data as JSON object
        JSONObject userJson = new JSONObject();
        userJson.put("name", "John Updated");
        userJson.put("job", "Senior QA");

        // Send request to update a user
        sendPutRequest("/api/users/2", userJson.toString());

        // Verify response status and content
        verifyStatusCode(200);

        // Verificar valores directamente
        String actualName = response.jsonPath().getString("name");
        Assert.assertEquals("John Updated", actualName);

        String actualJob = response.jsonPath().getString("job");
        Assert.assertEquals("Senior QA", actualJob);

        // Verificar que existe el campo updatedAt
        verifyFieldExists("updatedAt");
    }
}
