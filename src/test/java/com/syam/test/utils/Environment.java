package com.syam.test.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class Environment {
    private Service qa;
    private Service stg;

    public Service getQa() {
        return qa;
    }

    public void setQa(Service qa) {
        this.qa = qa;
    }

    public Service getStg() {
        return stg;
    }

    public void setStg(Service stg) {
        this.stg = stg;
    }

    @Override
    public String toString() {
        return "Environment{" +
                "qa=" + qa +
                ", stg=" + stg +
                '}';
    }
}