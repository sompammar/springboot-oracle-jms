/*
 * Copyright 2018 (C) Oracle Corporation
 * 
 * Created on : 01-07-2018
 * 
 *
 *-----------------------------------------------------------------------------
 * Revision History (Release 1.0.0.0)
 *-----------------------------------------------------------------------------
 * VERSION     AUTHOR/      DESCRIPTION OF CHANGE
 * OLD/NEW     DATE                RFC NO
 *-----------------------------------------------------------------------------
 * --/1.0  | 01-07-2018    | Initial Create.
 *         |               |
 *---------|---------------|---------------------------------------------------
 *         | author        | Defect ID 1/Description
 *         | dd-mm-yy      | 
 *---------|---------------|---------------------------------------------------
 */
package com.example.springboot.controller;

import com.opencsv.CSVReader;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 * The Class TransformController.
 */

@RestController
public class CSVController {

	/** The logger. */
//	public static @Log Logger logger;

	private static final Logger logger = LoggerFactory.getLogger(CSVController.class);


	private static Map<String, Integer> buildHeaderIndexMap(String[] headerItems) {
		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i < headerItems.length; ++i) {
			map.put(headerItems[i], i);
		}
		return map;
	}

	public List<String[]> csvToList(InputStream inputStream, String[] cols) throws Exception {
		List<String[]> records = new ArrayList<>();
		CSVReader reader = new CSVReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
		int lnNum = 0;
		String[] nextLine;
		String[] headerLine = reader.readNext();
		Map<String, Integer> colIndexMap = buildHeaderIndexMap(headerLine);

		while ((nextLine = reader.readNext()) != null) {
			lnNum++;
			String[] vals = new String[cols.length];
			for (int i=0; i < cols.length; ++i) {
				String col = cols[i];
				Integer colIndex = colIndexMap.get(col);
				if (colIndex == null) {
					throw new Exception("No data for column" + col);
				}
				vals[i] = nextLine[colIndex];
			}
			records.add(vals);
		}
		return records;
	}


	@GetMapping("/csv")
	@ApiOperation(value = "Submit the request for csvservice")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "csvservice request submitted successfully"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public List<String[]>  getCSV() throws Exception {
		logger.debug("Entering Method");
		String [] cols = new String[] {"Zip", "FirstName","LastName","Street","City","State"};
		InputStream inputStream = CSVController.class.getResourceAsStream("/addresses.csv.gz");
		return csvToList(new GZIPInputStream(inputStream), cols);
	}
}
