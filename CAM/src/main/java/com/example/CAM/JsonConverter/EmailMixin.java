package com.example.CAM.JsonConverter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class EmailMixin {
    @JsonCreator
    public EmailMixin(@JsonProperty("value") String value, @JsonProperty("type") String type, @JsonProperty("primary") Boolean primary) {
        // Empty constructor body, this mixin class is just used for deserialization
    }
}
