package com.syam.test.restfill;

import com.syam.test.base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class RestFullServicesTest extends BaseTest {

    @Test(groups = {"smoke","reg"})
    public void test1(){
        given().spec(getResfullServices()).get("/objects");
    }

    @Test
    public void test2(){
        given().spec(getResfullServices()).get("/objects?id=3&id=5&id=10");
    }

    @Test(groups = "smoke")
    public void test3(){
        given().spec(getResfullServices()).get("/objects?id=7");
    }

//    @Test
//    public void test3(){
//        given().spec(getResfullServices()).get("/objects?id=7");
//    }
}
