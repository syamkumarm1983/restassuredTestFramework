package com.syam.test.orders;

import com.syam.test.base.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class MyOrdersTestClass extends BaseTest {



    @Test(priority = 1,groups = {"smoke1", "sk"})
    public void thisIsOrderTestMethod(){
        given().spec(getOrderServices()).when().get("/products");
    }



    @Test(priority = 3,groups =  "sk")
    public void thisIsOrderTestUser(){
        given().spec(getOrderServices()).when().get("/users");
    }

    @Test(groups = "sk")
    public void thisisPost() {
        String reqBody = "{\n" +
                "  \"id\": 101,\n" +
                "  \"title\": \"This is Sample\",\n" +
                "  \"price\": 0.1,\n" +
                "  \"description\": \"this is my description\",\n" +
                "  \"category\": \"no catagiry\",\n" +
                "  \"image\": \"http://example.com\"\n" +
                "}";
        Response create = given().spec(getOrderServices().header("Content-Type","application/json")).body(reqBody).when().post("/products")
                .then()
                .assertThat()
                .statusCode(201)
                .body("id",notNullValue())
                .extract().response();
        Response getUser = given().spec(getOrderServices()).pathParam("id",create.getBody().jsonPath().getString("id")).when().get("/users/{id}")
                .then().assertThat().body("id",equalTo(create.getBody().jsonPath().getString("id"))).extract().response();

    }

}
