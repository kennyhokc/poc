package com.example.api;

import com.example.poc.api.UsersApiDelegate;
import com.example.poc.model.UserResponse;
import com.example.service.ValidateService;
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

    ValidateService validateService;

    @Autowired
    public UserApi (final ValidateService validateService) {
        this.validateService = validateService;
    }

    private String schema;

     {
        schema = null;
        try {
            schema = FileUtils.readFileToString(new File("src/main/sample/example.json"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResponseEntity<UserResponse> getUsers(Long userId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> addUsers(Object body) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(body);
            validateService.validateContext(jsonString, schema);

        return ResponseEntity.noContent().build();
    }
}
