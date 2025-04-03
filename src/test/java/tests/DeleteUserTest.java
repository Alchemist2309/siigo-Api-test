package tests;

import base.BaseTest;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class DeleteUserTest extends BaseTest {

    @Test
    @Title("Delete a user")
    public void testDeleteUser() {
        sendDeleteRequest("/api/users/2");
        verifyStatusCode(204);
    }
}
