package com.example.service;

import com.example.exception.SchemaException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

@Component
public class ValidateService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    // create instance of the ObjectMapper class
    ObjectMapper objectMapper = new ObjectMapper();

    // create an instance of the JsonSchemaFactory using version flag
    JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V201909);

    public void validateContext(String context, String schema) {
        try {
            // read data from the stream and store it into JsonNode
            JsonNode json = objectMapper.readTree(context);

            // get schema from the schemaStream and store it into JsonSchema
            JsonSchema jsonSchema = schemaFactory.getSchema(schema);

            // create set of validation message and store result in it
            Set<ValidationMessage> validationResult = jsonSchema.validate(json);

            if (validationResult.isEmpty()) {
                logger.info("Json is valid");
            } else {
                throw new SchemaException("Json is invalid", validationResult);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
