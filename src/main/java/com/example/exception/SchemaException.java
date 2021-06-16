package com.example.exception;

import com.networknt.schema.JsonSchemaException;
import com.networknt.schema.ValidationMessage;

public class SchemaException extends JsonSchemaException {

    public SchemaException(ValidationMessage validationMessage) {
        super(validationMessage);
    }

    public SchemaException(String message) {
        super(message);
    }

    public SchemaException(Throwable throwable) {
        super(throwable);
    }
}
