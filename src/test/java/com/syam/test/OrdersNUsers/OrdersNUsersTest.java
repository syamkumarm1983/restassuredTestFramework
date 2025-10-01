package com.syam.test.OrdersNUsers;

import com.syam.test.base.BaseTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class OrdersNUsersTest extends BaseTest {

    @Test
    public void sampleBoth(){
        given().spec(getOrderServices()).get("/products/1");
        given().spec(getUserServices()).get("/api/clerk/subscription-status");
    }
    @Test
    public void sample2() {
        given().spec(getUserServices()).get("/api/clerk/payment-status");
        given().spec(getOrderServices()).get("/products/10");
    }

}
