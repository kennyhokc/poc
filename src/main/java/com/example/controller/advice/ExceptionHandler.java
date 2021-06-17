package com.example.controller.advice;

import com.example.poc.model.GenericApiResponse;
import com.networknt.schema.JsonSchemaException;
import com.networknt.schema.ValidationMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler({JsonSchemaException.class})
    @ResponseBody
    public ResponseEntity<GenericApiResponse> handleJsonSchemaException(JsonSchemaException exception) {
        log.error("[handleJsonSchemaException]: {}", exception.getMessage());
        List<String> messages = exception.getValidationMessages()
                .stream()
                .map(ValidationMessage::getMessage)
                .collect(Collectors.toList());
        HashMap<String, Object> messageMap = new HashMap<String, Object>();
        messageMap.put("message", "json schema is not valid");
        messageMap.put("details", messages);

        GenericApiResponse errorMessage = new GenericApiResponse();
        errorMessage.setCode((long) HttpStatus.BAD_REQUEST.value());
        errorMessage.setMessage(messageMap.toString());

        return ResponseEntity
                .badRequest()
                .body(errorMessage);
    }

}
