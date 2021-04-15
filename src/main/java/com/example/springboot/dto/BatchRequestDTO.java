// tag::sample[]
package com.example.springboot.dto;

public class BatchRequestDTO {

    private Long id;

    BatchReqType requestType;

    BaseBatchReqDef objectDef;

    public BatchRequestDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BatchReqType getRequestType() {
        return requestType;
    }

    public void setRequestType(BatchReqType requestType) {
        this.requestType = requestType;
    }

    public BaseBatchReqDef getObjectDef() {
        return objectDef;
    }

    public void setObjectDef(BaseBatchReqDef objectDef) {
        this.objectDef = objectDef;
    }
}

