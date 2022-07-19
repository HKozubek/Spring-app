package com.example.demo;

public class ScriptNotFoundException extends RuntimeException {

    ScriptNotFoundException(Integer id) {
        super("Could not find script " + id);
    }
    ScriptNotFoundException(String name) {
        super("Could not find script named " + name);
    }
}
