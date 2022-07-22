package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class ScriptsControllerTest {

    @Autowired
    private ScriptsController controller;

    @Autowired
    private MockMvc mockMvc;


    private ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void all() throws Exception {
        mockMvc.perform(get("/scripts"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void newScript() throws Exception {
        Scripts script = new Scripts("return \"test\"", "test");

        mockMvc.perform(post("/scripts")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(script)))
                        .andExpect(status().isCreated());
    }

    @Test
    void runScript() throws Exception{
        Map<String,Object> body = new HashMap<>();
        body.put("arr","[1, 2, 3]");

        mockMvc.perform(post("/scripts/run/addition")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("6")));
    }

    @Test
    void one() throws Exception {
        mockMvc.perform(get("/scripts/{id}", 2))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"script\":\"return $x + $y\"")));
    }

    @Test
    void replaceScript() throws Exception {
        Scripts script = new Scripts("return \"test\"", "test");

        mockMvc.perform(put("/scripts/{id}", 1)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(script)))
                .andExpect(content().string(containsString("\"script\":\"return \\\"test\\\"\",\"name\":\"test\"")))
                .andExpect(status().isCreated());
    }

    @Test
    void deleteScript() throws Exception{
        mockMvc.perform(delete("/scripts/{id}", 1)
                        .contentType("application/json"))
                .andExpect(status().isNoContent());
    }
}