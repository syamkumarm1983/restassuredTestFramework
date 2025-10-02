package com.syam.test.base;
import com.aventstack.extentreports.Status;
import com.syam.test.utils.report.MyExtentReporter;
import  io.restassured.*;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class BaseRequestFilter implements Filter {

    StringBuffer request = new StringBuffer();
    StringBuffer response = new StringBuffer();

    @Override
    public Response filter(FilterableRequestSpecification requestFilterSpec, FilterableResponseSpecification resPonseSpec, FilterContext filterContext) {
        request.setLength(0);
        response.setLength(0);
        request.append("<details><summary>Request Info</summary><p>");
        request.append("<br/>URL: "+requestFilterSpec.getURI()+"\n");
        request.append("<br/>Method: "+requestFilterSpec.getMethod()+"\n");
        request.append("<br/>Request Headers: "+requestFilterSpec.getHeaders());
        if(requestFilterSpec.getBody()!=null)
            request.append("<br/>Request Body: "+requestFilterSpec.getBody());
        request.append("</p></details>");
        Response res = filterContext.next(requestFilterSpec,resPonseSpec);
        response.append("<details><summary>Response Info</summary><p>");
        response.append("<br/>Status Code: "+res.getStatusCode()+"\n");
        response.append("<br/>Status Headers: "+res.getHeaders()+"\n");
        response.append("<br/>Status Body: "+res.body().asString()+"\n");


        MyExtentReporter.extentTest.get().log(Status.INFO,"Request "+request.toString());
        MyExtentReporter.extentTest.get().log(Status.INFO,"Response "+response.toString());

        return  res;

    }

    public String getRequestLogs() {
        return  request.toString();
    }

    public  String getResponseLogs() {
        return  response.toString();
    }
}
