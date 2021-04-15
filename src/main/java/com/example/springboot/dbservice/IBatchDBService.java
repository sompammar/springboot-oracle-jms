package com.example.springboot.dbservice;

import com.example.springboot.entity.BatchRequest;

public interface IBatchDBService {
    BatchRequest saveBatchRequest(BatchRequest batchRequest);
}
