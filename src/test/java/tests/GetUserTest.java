package tests;

import base.BaseTest;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GetUserTest extends BaseTest {

    @Test
    @Title("Get a list of users")
    public void testGetUserList() {
        sendGetRequest("/api/users?page=2");
        verifyStatusCode(200);
        int actualPageValue = response.jsonPath().getInt("page");
        Assert.assertEquals(2, actualPageValue);
        int dataSize = response.jsonPath().getList("data").size();
        Assert.assertTrue("Expected at least one user in the response", dataSize > 0);
    }

    @Test
    @Title("Get a single user")
    public void testGetSingleUser() {
        sendGetRequest("/api/users/2");
        verifyStatusCode(200);
        int actualId = response.jsonPath().getInt("data.id");
        Assert.assertEquals(2, actualId);
        String emailValue = response.jsonPath().getString("data.email");
        Assert.assertNotNull("Field 'email' should not be null", emailValue);
    }
}
