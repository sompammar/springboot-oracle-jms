package com.example.springboot;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataflowPayload {

    @JsonProperty("arguments")
    private  List<String> arguments = new ArrayList<>();
    @JsonProperty("className")
    private  String className;
    @JsonProperty("compartmentId")
    private  String compartmentId;
    @JsonProperty("configuration")
    private  Map<String, String> configuration;
    @JsonProperty("definedTags")
    private  Map<String, Map<String, Object>> definedTags;
    @JsonProperty("description")
    private  String description = "No description provided";
    @JsonProperty("displayName")
    private  String displayName = "No displayName provided";
    @JsonProperty("driverShape")
    private  String driverShape = "VM.Standard2.2";
    @JsonProperty("executorShape")
    private  String executorShape = "VM.Standard2.2";
    @JsonProperty("fileUri")
    private  String fileUri;
    @JsonProperty("freeformTags")
    private  Map<String, String> freeformTags;
    @JsonProperty("language")
    private  String language = "SCALA";
    @JsonProperty("logsBucketUri")
    private  String logsBucketUri;
    @JsonProperty("numExecutors")
    private  Integer numExecutors = 2;
    @JsonProperty("parameters")
    private HashMap<String, String> parameters;
    @JsonProperty("sparkVersion")
    private  String sparkVersion = "2.4.4";
    @JsonProperty("warehouseBucketUri")
    private  String warehouseBucketUri;
    @JsonProperty("tenancy")
    private  String tenancy;
    @JsonProperty("user")
    private  String user;

    @JsonProperty("pemKey")
    private  String pemKey;
    @JsonProperty("Date")
    private  String date;

    @JsonProperty("runId")
    private  String runId;
    @JsonProperty("opcId")
    private  String opcId;

    @JsonProperty("fingerPrint")
    private  String fingerPrint;


    public String getPemKey() {
        return pemKey;
    }

    public void setPemKey(String pemKey) {
        this.pemKey = pemKey;
    }
    public String getRunId() {
        return runId;
    }

    public void setRunId(String runId) {
        this.runId = runId;
    }

    public String getOpcId() {
        return opcId;
    }

    public void setOpcId(String opcId) {
        this.opcId = opcId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @JsonProperty("appId")
    private  String appId;

    public String getTenancy() {
        return tenancy;
    }

    public void setTenancy(String tenancy) {
        this.tenancy = tenancy;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getArguments() {
        return arguments;
    }

    public void setArguments(List<String> arguments) {
        this.arguments = arguments;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCompartmentId() {
        return compartmentId;
    }

    public void setCompartmentId(String compartmentId) {
        this.compartmentId = compartmentId;
    }

    public Map<String, String> getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Map<String, String> configuration) {
        this.configuration = configuration;
    }

    public Map<String, Map<String, Object>> getDefinedTags() {
        return definedTags;
    }

    public void setDefinedTags(Map<String, Map<String, Object>> definedTags) {
        this.definedTags = definedTags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDriverShape() {
        return driverShape;
    }

    public void setDriverShape(String driverShape) {
        this.driverShape = driverShape;
    }

    public String getExecutorShape() {
        return executorShape;
    }

    public void setExecutorShape(String executorShape) {
        this.executorShape = executorShape;
    }

    public String getFileUri() {
        return fileUri;
    }

    public void setFileUri(String fileUri) {
        this.fileUri = fileUri;
    }

    public Map<String, String> getFreeformTags() {
        return freeformTags;
    }

    public void setFreeformTags(Map<String, String> freeformTags) {
        this.freeformTags = freeformTags;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLogsBucketUri() {
        return logsBucketUri;
    }

    public void setLogsBucketUri(String logsBucketUri) {
        this.logsBucketUri = logsBucketUri;
    }

    public Integer getNumExecutors() {
        return numExecutors;
    }

    public void setNumExecutors(Integer numExecutors) {
        this.numExecutors = numExecutors;
    }

    public HashMap<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(HashMap<String, String> parameters) {
        this.parameters = parameters;
    }

    public String getSparkVersion() {
        return sparkVersion;
    }

    public void setSparkVersion(String sparkVersion) {
        this.sparkVersion = sparkVersion;
    }

    public String getWarehouseBucketUri() {
        return warehouseBucketUri;
    }

    public void setWarehouseBucketUri(String warehouseBucketUri) {
        this.warehouseBucketUri = warehouseBucketUri;
    }
}


