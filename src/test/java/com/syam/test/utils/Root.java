package com.syam.test.utils;

public class Root {
    private Environment environment;

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public String toString() {
        return "Root{" +
                "environment=" + environment +
                '}';
    }
}
