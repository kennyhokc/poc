package com.example.api;

import com.example.poc.api.UsersApiDelegate;
import com.example.poc.model.User;
import com.example.service.GeneralService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class UserApi implements UsersApiDelegate {

    GeneralService generalService;

    @Autowired
    public UserApi (final GeneralService generalService) {
        this.generalService = generalService;
    }

    private String SAMPLE_SCHEMA = null;

    {
        try {
            SAMPLE_SCHEMA = FileUtils.readFileToString(new File("src/main/sample/example.json"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public ResponseEntity<User> getUsers(Long userId) {
        var user = new User();
        user.setId(1L);
        user.setName("Kenny Ho");
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<Void> addUsers(Object body) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            var jsonString = objectMapper.writeValueAsString(body);
            generalService.validateContext(jsonString, SAMPLE_SCHEMA);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.noContent().build();
    }
}
