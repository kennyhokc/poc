package com.example.api;

import com.example.PocApplication;
import com.example.poc.model.User;
import com.example.service.ValidateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PocApplication.class)
@SpringBootTest
public class UserApiTest {
    private MockMvc mockMvc;
    private ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Autowired
    private WebApplicationContext wac;

    @Mock
    ValidateService validateService;

    @InjectMocks
    UserApi controller;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testGetUsers() throws Exception {
        mockMvc.perform(get("/users/{id}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void testAddUser() throws Exception {
        User user = new User()
                .name("Kenny")
                .age(-1)
                .email("abc@gmail.com");

        mockMvc.perform(post("/users")
                .content(objectWriter.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.NO_CONTENT.value()))
                .andDo(MockMvcResultHandlers.print());


    }
}