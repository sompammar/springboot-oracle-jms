package com.example.springboot.dbservice;

import com.example.springboot.entity.BatchRequest;
import org.springframework.data.repository.CrudRepository;

public interface BatchRequestRepository extends CrudRepository<BatchRequest, Long> {

}
