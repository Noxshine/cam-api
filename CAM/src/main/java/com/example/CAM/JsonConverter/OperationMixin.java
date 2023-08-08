package com.example.CAM.JsonConverter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class OperationMixin {
    @JsonCreator
    public OperationMixin(@JsonProperty("op") String op, @JsonProperty("path") String path, @JsonProperty("value") Object value) {
        // Empty constructor body, this mixin class is just used for deserialization
    }
}
