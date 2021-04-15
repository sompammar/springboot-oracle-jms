package com.example.springboot.dbservice;

import com.example.springboot.entity.BatchRequest;
import org.springframework.beans.factory.annotation.Autowired;

public class BatchDBService implements IBatchDBService {

    @Autowired
    BatchRequestRepository batchRequestRepository;

    @Override
    public BatchRequest saveBatchRequest(BatchRequest batchRequest) {
        return batchRequestRepository.save(batchRequest);
    }

}
