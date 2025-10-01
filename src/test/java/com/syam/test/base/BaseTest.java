package com.syam.test.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.syam.test.utils.Root;
import com.syam.test.utils.Service;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.io.File;

public class BaseTest {
    private static Service serVicesToRun;
    BaseRequestFilter requestFilter;

    //When we use parallel run it will not work (RequestSpecification) properly so we need use object for each thread
    private static ThreadLocal<RequestSpecification> orderService = new ThreadLocal<>();
    private static ThreadLocal<RequestSpecification> useService = new ThreadLocal<>();
    private static ThreadLocal<RequestSpecification> restFulservices = new ThreadLocal<>();
    @BeforeSuite(alwaysRun = true)
    @Parameters("env")
    public void beforeSuite(String env) {
        System.out.println(env);
        String path = getClass().getClassLoader().getResource("config.json").getPath();
        System.out.println(path);

        JSONParser parser = new JSONParser();
        try{
            //FileReader read = new FileReader(path);
            ObjectMapper mapper = new ObjectMapper();

            Root environment = mapper.readValue(new File(path),Root.class);
            System.out.println(environment.toString());
            if(env!=null && env.equalsIgnoreCase("qa")) {
                serVicesToRun = environment.getEnvironment().getQa();
            } else {
                serVicesToRun = environment.getEnvironment().getStg();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public RequestSpecification getOrderServices() {
        if(orderService.get()==null){
            requestFilter = new BaseRequestFilter();
            RequestSpecification order = new RequestSpecBuilder()
                .setBaseUri(serVicesToRun.getOrder_service())
                .addFilter(requestFilter)
                .build();
            orderService.set(order);
        }
        return orderService.get();
    }

    public RequestSpecification getUserServices() {
        if(useService.get()==null){
            requestFilter = new BaseRequestFilter();
        RequestSpecification users = new RequestSpecBuilder()
                .setBaseUri(serVicesToRun.getUser_service())
                .addFilter(requestFilter)
                .addHeader("x-api-key","reqres-free-v1")
                .build();
        useService.set(users);
        }
        return useService.get();
    }

    public RequestSpecification getResfullServices() {
        if(restFulservices.get()==null){
            requestFilter = new BaseRequestFilter();
            RequestSpecification users = new RequestSpecBuilder()
                    .setBaseUri(serVicesToRun.getRestful_api())
                    .addFilter(requestFilter)
                    .build();
            restFulservices.set(users);
        }
        return restFulservices.get();
    }

}
