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
        JSONObject userJson = new JSONObject();
        userJson.put("name", "Javier Morales");
        userJson.put("job", "Senior QA");
        sendPutRequest("/api/users/2", userJson.toString());
        verifyStatusCode(200);
        String actualName = response.jsonPath().getString("name");
        Assert.assertEquals("Javier Morales", actualName);
        String actualJob = response.jsonPath().getString("job");
        Assert.assertEquals("Senior QA", actualJob);
        verifyFieldExists("updatedAt");
    }
}
