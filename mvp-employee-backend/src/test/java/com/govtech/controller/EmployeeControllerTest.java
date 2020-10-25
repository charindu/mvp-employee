package com.govtech.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void uploadUsers() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/users/upload").accept(MediaType.APPLICATION_JSON_VALUE);
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    void getUsersExt() {
    }

    @Test
    void createEmployee() {
    }

    @Test
    void updateEmployee() throws Exception {

    }

    @Test
    void getEmployeeById() {
    }

    @Test
    void deleteEmployee() {
    }
}