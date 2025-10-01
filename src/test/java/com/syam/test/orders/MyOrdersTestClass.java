package com.syam.test.orders;

import com.syam.test.base.BaseTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;


public class MyOrdersTestClass extends BaseTest {



    @Test(priority = 1)
    public void thisIsOrderTestMethod(){
        given().spec(getOrderServices()).when().get("/products");
    }



    @Test(priority = 3)
    public void thisIsOrderTestUser(){
        given().spec(getOrderServices()).when().get("/users");
    }

}
