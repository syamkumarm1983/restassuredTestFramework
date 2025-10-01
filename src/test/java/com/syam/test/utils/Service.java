package com.syam.test.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class Service {
    private String user_service;
    private String order_service;

    private String restful_api;

    public String getUser_service() {
        return user_service;
    }

    public void setUser_service(String user_service) {
        this.user_service = user_service;
    }

    public String getOrder_service() {
        return order_service;
    }

    public void setOrder_service(String order_service) {
        this.order_service = order_service;
    }

    public String getRestful_api() {
        return restful_api;
    }

    public void setRestful_api(String restful_api) {
        this.restful_api = restful_api;
    }

    @Override
    public String toString() {
        return "Service{" +
                "user_service='" + user_service + '\'' +
                ", order_service='" + order_service + '\'' +
                ", restful_api='" + restful_api + '\'' +
                '}';
    }
}
