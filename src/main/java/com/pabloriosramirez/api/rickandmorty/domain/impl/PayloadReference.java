package com.pabloriosramirez.api.rickandmorty.domain.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pabloriosramirez.api.rickandmorty.constant.PayloadTypeEnum;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PayloadReference {

    private String configurationElementId;
    private PayloadTypeEnum type;
    private String path;

    public PayloadReference() {
    }

    public PayloadReference(String configurationElementId, PayloadTypeEnum type, String path) {
        this.configurationElementId = configurationElementId;
        this.type = type;
        this.path = path;
    }

    public String getConfigurationElementId() {
        return configurationElementId;
    }

    public void setConfigurationElementId(String configurationElementId) {
        this.configurationElementId = configurationElementId;
    }

    public PayloadTypeEnum getType() {
        return type;
    }

    public void setType(PayloadTypeEnum type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
