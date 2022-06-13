package com.pabloriosramirez.api.rickandmorty.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.pabloriosramirez.api.rickandmorty.domain.impl.*;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT, property = "mappingType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DynamicMapping.class, name = "DynamicMapping"),
        @JsonSubTypes.Type(value = DynamicFieldMapping.class, name = "DynamicFieldMapping"),
        @JsonSubTypes.Type(value = StaticMapping.class, name = "StaticMapping"),
        @JsonSubTypes.Type(value = HeaderContentMapping.class, name = "HeaderContentMapping")
})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class Mapping {

    protected String destinationPath;
    protected String operation;
    protected String mappingType;

    public String getDestinationPath() {
        return destinationPath;
    }

    public void setDestinationPath(String destinationPath) {
        this.destinationPath = destinationPath;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getMappingType() {
        return mappingType;
    }

    public void setMappingType(String mappingType) {
        this.mappingType = mappingType;
    }
}
