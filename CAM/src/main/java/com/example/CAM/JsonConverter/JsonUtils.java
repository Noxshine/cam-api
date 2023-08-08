package com.example.CAM.JsonConverter;

import com.example.CAM.domain.message.AddUser.EmailDTO;
import com.example.CAM.domain.message.PatchOp.Operation;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
    private static ObjectMapper objectMapper;

    public static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();

            // Register the custom mixin for Operation class
            SimpleModule module = new SimpleModule();

            module.setMixInAnnotation(Operation.class, OperationMixin.class);
            objectMapper.registerModule(module);

            module.setMixInAnnotation(EmailDTO.class, EmailMixin.class);
            objectMapper.registerModule(module);
        }
        return objectMapper;
    }
}
