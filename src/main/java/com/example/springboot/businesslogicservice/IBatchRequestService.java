package com.example.springboot.businesslogicservice;

import com.example.springboot.entity.BatchRequest;

public interface IBatchRequestService {
    void executeReq(BatchRequest req);
}
