package com.example.exception;

import com.networknt.schema.JsonSchemaException;
import com.networknt.schema.ValidationMessage;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
public class SchemaException extends JsonSchemaException {
    private Set<ValidationMessage> validationMessages;

    public SchemaException(ValidationMessage validationMessage) {
        super(validationMessage);
    }

    public SchemaException(String message) {
        super(message);
    }

    public SchemaException(Throwable throwable) {
        super(throwable);
    }

    public SchemaException(String message, Set<ValidationMessage> validationMessages) {
        super(message);
        this.validationMessages = validationMessages;
    }
}
