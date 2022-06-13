package com.pabloriosramirez.api.rickandmorty.domain.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pabloriosramirez.api.rickandmorty.domain.Mapping;

import java.util.List;

public class Transformer extends ConfigurationElement{

    private String targetElementKey;
    private List<Mapping> mappings;

    @JsonCreator
    public Transformer (@JsonProperty("configurationElementId") String configurationElementId){
        super(configurationElementId);
        super.setElementType(Transformer.class.getName());
    }

    public Transformer() {
    }

    public String getTargetElementKey() {
        return targetElementKey;
    }

    public void setTargetElementKey(String targetElementKey) {
        this.targetElementKey = targetElementKey;
    }

    public List<Mapping> getMappings() {
        return mappings;
    }

    public void setMappings(List<Mapping> mappings) {
        this.mappings = mappings;
    }
}
