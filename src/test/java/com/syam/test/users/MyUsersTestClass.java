package com.syam.test.users;

import com.syam.test.base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class MyUsersTestClass extends BaseTest {
    @Test(priority = 2)
    public void thisIsUserTestMethod() {
        given().spec(getUserServices()).when().get("/users");
    }
}
