package com.example.CAM.domain.message.PatchOp;

public class Operation {
    private String op ;
    private String path;
    private String value;
    public Operation(){}
    public Operation(String op, String path, String value) {
        this.op = op;
        this.path = path;
        this.value = value;
    }
    public String getOp() { return op;}
    public void setOp(String op) {
        this.op = op;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

}
