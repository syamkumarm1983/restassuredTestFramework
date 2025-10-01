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
        request.append("URL: "+requestFilterSpec.getURI()+"\n");
        request.append("Method: "+requestFilterSpec.getMethod()+"\n");
        request.append("Request Headers: "+requestFilterSpec.getHeaders());
        Response res = filterContext.next(requestFilterSpec,resPonseSpec);
        response.append("Status Code: "+res.getStatusCode()+"\n");
        response.append("Status Headers: "+res.getHeaders()+"\n");
        response.append("Status Body: "+res.body().asString()+"\n");

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
