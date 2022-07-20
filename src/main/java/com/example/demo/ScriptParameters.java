package com.example.demo;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.LinkedHashMap;
import java.util.Map;

public class ScriptParameters {
    private final Map<String, Object> parameters = new LinkedHashMap<>();

    @JsonAnySetter
    void setParameters(String key, Object value){
        parameters.put(key, value);
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }
}
