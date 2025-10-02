package com.syam.test.orders;

import com.aventstack.extentreports.Status;
import com.syam.test.base.BaseTest;
import com.syam.test.utils.report.MyExtentReporter;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class MyOrdersTestClass extends BaseTest {



    @Test(priority = 1,groups = {"smoke1", "sk"})
    public void thisIsOrderTestMethod(){
        MyExtentReporter.extentTest.get().log(Status.INFO,"This is get all Product Request");
        given().spec(getOrderServices()).when().get("/products");
    }



    @Test(priority = 3,groups =  "sk")
    public void thisIsOrderTestUser(){
        MyExtentReporter.extentTest.get().log(Status.INFO,"This is get all users in Request");
        given().spec(getOrderServices()).when().get("/users");
    }

    @Test(groups = "sk")
    public void thisisPost() {
        MyExtentReporter.extentTest.get().log(Status.INFO,"This is Put Request For Product");
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
        MyExtentReporter.extentTest.get().log(Status.INFO,"This is get all Product Request");
        given().spec(getOrderServices()).when().get("/products?page=2");
        MyExtentReporter.extentTest.get().log(Status.INFO,"This is get single Product Request");
        Response getUser = given().spec(getOrderServices()).pathParam("id",11).when().get("/products/{id}")
                .then().assertThat().body("id",equalTo(11))
                .body("price",equalTo(109)).extract().response();

    }

}
