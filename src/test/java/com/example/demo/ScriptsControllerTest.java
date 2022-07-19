package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ScriptsControllerTest {

//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate controller;



    @Test
    void newScript() throws Exception {
//        assertThat(this.controller.getForObject("http://localhost:" + port + "/scripts/1",
//                Scripts.class));
    }

    @Test
    void one() {
    }

    @Test
    void replaceScript() {
    }

    @Test
    void deleteScript() {
    }
}