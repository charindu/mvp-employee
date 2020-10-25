package com.govtech.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.govtech.model.Employee;
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

    @Autowired
    ObjectMapper objectMapper;

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
        Employee mockEmployee = new Employee( "e002","testtest","Test Test",4000 );
        RequestBuilder request = MockMvcRequestBuilders.patch("/users/2")
                .content(objectMapper.writeValueAsString(mockEmployee))
                .accept(MediaType.APPLICATION_JSON_VALUE);
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    void getEmployeeById() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/users/2").accept(MediaType.APPLICATION_JSON_VALUE);
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    void deleteEmployee() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.delete("/users/2").accept(MediaType.APPLICATION_JSON_VALUE);
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
}