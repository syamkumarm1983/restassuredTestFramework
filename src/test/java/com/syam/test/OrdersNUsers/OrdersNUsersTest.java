package com.syam.test.OrdersNUsers;

import com.syam.test.base.BaseTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class OrdersNUsersTest extends BaseTest {

    @Test
    public void sampleBoth(){
        given().spec(getOrderServices()).get("/products/1");
        given().spec(getUserServices()).get("/clerk/subscription-status");
    }
    @Test(groups = "smoke")
    public void sample2() {
        given().spec(getUserServices()).get("/clerk/payment-status");
        given().spec(getOrderServices()).get("/products/10");
    }

}
