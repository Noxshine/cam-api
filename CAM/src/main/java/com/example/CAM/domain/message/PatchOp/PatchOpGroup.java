package com.example.CAM.domain.message.PatchOp;

//import com.example.CAM.domain.message.PatchOp.jsonOperationConverter.JsonPatchOperation;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PatchOpGroup {
    List <String> schemas ;
    private Long groupId;
    @JsonProperty("Operations")
//    @JsonPatchOperation
    private List<Operation> Operations;

    public Long getGroupId() {
        return groupId;
    }

    public void setUserId(Long userId) {this.groupId = userId;}

    public List<Operation> getOperations() {
        return Operations;
    }

    public void setOperations(List<Operation> operations) {
        Operations = operations;
    }
}
