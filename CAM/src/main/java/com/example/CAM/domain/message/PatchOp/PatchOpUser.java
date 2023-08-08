package com.example.CAM.domain.message.PatchOp;

//import com.example.CAM.domain.message.PatchOp.jsonOperationConverter.JsonPatchOperation;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
public class PatchOpUser {
    private List <String> schemas ;
    private Long userId;
    @JsonProperty("Operations")
//    @JsonPatchOperation
    private List<Operation> Operations;

    public PatchOpUser() {}

    public PatchOpUser(List<String> schemas,Long userId, List<Operation> operations) {
        this.schemas = schemas;
        this.userId = userId;
        this.Operations = operations;
    }
    public List<String> getSchemas() {return schemas;}
    public void setSchemas(List<String> schemas) {this.schemas = schemas;}
    public Long getUserId() {return userId;}
    public void setUserId(Long userId) {this.userId = userId;}
    public List<Operation> getOperations() {return Operations;}
    public void setOperations(List<Operation> operations) {Operations = operations;}
}
