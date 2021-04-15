package com.example.springboot.controller;

import com.example.springboot.entity.BatchRequest;
import com.example.springboot.businesslogicservice.BatchRequestService;
import com.example.springboot.jms.JmsActionType;
import com.example.springboot.jms.JmsMessageSender;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class HelloController {

	@Autowired
	BatchRequestService batchRequestService;



	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}


	@PostMapping(value = "/req")
	public String execute(@RequestBody BatchRequest batchRequest) {
		batchRequestService.executeReq(batchRequest);
		return UUID.randomUUID().toString();
	}


	@Autowired
	JmsMessageSender messageSender;



	@PostMapping("/send")
	@ApiOperation(value = "Submit the request for csvservice")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "csvservice request submitted successfully"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public void sendMessage(@RequestParam(name = "msg") String msg) throws Exception {
		messageSender.sendMessage(msg, UUID.randomUUID().toString(), JmsActionType.Action1);
	}

}
