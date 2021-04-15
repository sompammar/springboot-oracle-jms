package com.example.springboot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class JsonUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);


    public static <T> T resourceToObject(String resourceName, Class<T> tClass) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        InputStream in = JsonUtil.class.getResourceAsStream(resourceName);
        if(in == null){
            in = new FileInputStream(resourceName);
        }
        return objectMapper.readValue(in, tClass);
    }

    public static <T> T resourceToObject(String resourceName, Class<T> tClass, boolean suppressFileNotFoundExc) throws IOException {
        InputStream in = JsonUtil.class.getResourceAsStream(resourceName);
        if(in == null){
            in = new FileInputStream(resourceName);
        }
        if(in != null){
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return objectMapper.readValue(in, tClass);

        } else if(!suppressFileNotFoundExc) {
            throw new FileNotFoundException("Not able to find resource with path - " +resourceName);
        }
        logger.info("Not able to find resource with path - " +resourceName);
        return null;
    }

    public static <T> T resourceToObject(InputStream in, Class<T> tClass) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(in, tClass);
    }


    public static <T> T contentToObject(final String content, Class<T> tClass) {
        if(StringUtils.isEmpty(content)){
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return objectMapper.readValue(content, tClass);
        } catch (IOException e) {
            logger.error("Error while generating Object from Json string : ",e);
        }
        return null;
    }

    public static String toJson(Object obj) {
        if(obj == null){
            return null;
        }
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("Error while generating Json string from Object : ",e);
        }
        return null;
    }



    

}
