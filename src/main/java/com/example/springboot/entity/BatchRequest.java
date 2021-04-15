// tag::sample[]
package com.example.springboot.entity;

import com.example.springboot.*;
import com.example.springboot.dto.BaseBatchReqDef;
import com.example.springboot.dto.BatchReqDef1;
import com.example.springboot.dto.BatchReqDef2;
import com.example.springboot.dto.BatchReqType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;

@Entity
public class BatchRequest {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    BatchReqType requestType;

    @Lob
    @Column(name = "REQUEST_DEFN_BYTE", length = 10000, nullable = false)
    @JsonIgnore
    private byte[] requestDefBytes;

    public BatchRequest() {
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

    public byte[] getRequestDefBytes() {
        return requestDefBytes;
    }

    public void setRequestDefBytes(byte[] requestDefBytes) {
        this.requestDefBytes = requestDefBytes;
    }

    public <T extends BaseBatchReqDef> T getReqDef() {
        T baseBatchReqDef = null;
        if(this.requestDefBytes != null){
            final String requestDefnStr = new String(GzipUtil.gzipUncompress(this.requestDefBytes), StandardCharsets.UTF_8);
            switch (requestType){
                case Request1:
                    baseBatchReqDef = (T) JsonUtil.contentToObject(requestDefnStr, BatchReqDef1.class);
                    break;
                case Request2:
                    baseBatchReqDef = (T) JsonUtil.contentToObject(requestDefnStr, BatchReqDef2.class);
                    break;
            }
        }
        return baseBatchReqDef;
    }

    public <T extends BaseBatchReqDef> void setReqDef(Object baseBatchReqDef) {
        final String requestDefnStr = JsonUtil.toJson(baseBatchReqDef);
        if(StringUtils.hasText(requestDefnStr)){
            this.requestDefBytes = GzipUtil.gzipCompress(requestDefnStr.getBytes(StandardCharsets.UTF_8));
        }
    }

}

