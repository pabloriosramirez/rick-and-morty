package com.pabloriosramirez.api.rickandmorty.domain.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.pabloriosramirez.api.rickandmorty.exception.domain.ConfigurationException;

import java.util.ArrayList;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT, property = "elementType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ServiceContext.class, name = "ServiceActivator"),
        @JsonSubTypes.Type(value = Transformer.class, name = "Transformer")
})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ConfigurationElement {

    private String configurationElementId;
    private String elementType;
    private String startingElement;
    private String endingElement;
    private List<String> adyacencies = new ArrayList<String>();
    private List<String> inputs = new ArrayList<String>();
    private List<String> outputs = new ArrayList<String>();

    protected ConfigurationElement(String configurationElementId) {
        if ((configurationElementId == null) || configurationElementId.isEmpty()) {
            throw new ConfigurationException("Can not create a configuration element with null or blank id");
        }
        this.configurationElementId = configurationElementId;
    }

    public ConfigurationElement() {
    }

    public String getConfigurationElementId() {
        return configurationElementId;
    }

    public void setConfigurationElementId(String configurationElementId) {
        this.configurationElementId = configurationElementId;
    }

    public String getElementType() {
        return elementType;
    }

    public void setElementType(String elementType) {
        this.elementType = elementType;
    }

    public String getStartingElement() {
        return startingElement;
    }

    public void setStartingElement(String startingElement) {
        this.startingElement = startingElement;
    }

    public String getEndingElement() {
        return endingElement;
    }

    public void setEndingElement(String endingElement) {
        this.endingElement = endingElement;
    }

    public List<String> getAdyacencies() {
        return adyacencies;
    }

    public void setAdyacencies(List<String> adyacencies) {
        this.adyacencies = adyacencies;
    }

    public List<String> getInputs() {
        return inputs;
    }

    public void setInputs(List<String> inputs) {
        this.inputs = inputs;
    }

    public List<String> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<String> outputs) {
        this.outputs = outputs;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof ConfigurationElement)) {
            return false;
        }
        final ConfigurationElement otherElement = (ConfigurationElement) other;
        if (configurationElementId.equals(otherElement.getConfigurationElementId())) {
            return true;
        } else {
            return false;
        }
    }
}
