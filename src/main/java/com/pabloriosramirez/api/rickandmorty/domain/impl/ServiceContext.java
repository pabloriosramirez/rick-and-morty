package com.pabloriosramirez.api.rickandmorty.domain.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class ServiceContext extends ConfigurationElement {

    private String servicePath;
    private String serviceCallMethod;
    private Map<String, String> serviceHeaders;
    private String applicationId;

    @JsonCreator
    public ServiceContext(@JsonProperty("configurationElementId") String configurationElementId){
        super(configurationElementId);
        super.setElementType(ServiceContext.class.getName());
    }

    public ServiceContext() {
    }

    public String getServicePath() {
        return servicePath;
    }

    public void setServicePath(String servicePath) {
        this.servicePath = servicePath;
    }

    public String getServiceCallMethod() {
        return serviceCallMethod;
    }

    public void setServiceCallMethod(String serviceCallMethod) {
        this.serviceCallMethod = serviceCallMethod;
    }

    public Map<String, String> getServiceHeaders() {
        return serviceHeaders;
    }

    public void setServiceHeaders(Map<String, String> serviceHeaders) {
        this.serviceHeaders = serviceHeaders;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }
}
