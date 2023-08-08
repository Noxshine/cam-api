package com.example.CAM.domain.response.error;

import java.util.List;

public class ErrorResponse {
    private List<String> schemas = List.of(new String[]{"urn:ietf:params:scim:api:messages:2.0:Error"});
    private String detail;
    private int status;

    public ErrorResponse(String detail, int status) {
        this.detail = detail;
        this.status = status;
    }

    public List<String> getSchemas() {
        return schemas;
    }

    public void setSchemas(List<String> schemas) {
        this.schemas = schemas;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
