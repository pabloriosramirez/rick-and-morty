package com.pabloriosramirez.api.rickandmorty.domain.impl;

import com.pabloriosramirez.api.rickandmorty.domain.Mapping;

public class DynamicFieldMapping extends Mapping {
    private PayloadReference payloadReference;

    public DynamicFieldMapping() {
        super.mappingType = HeaderContentMapping.class.getName();
    }

    public DynamicFieldMapping(PayloadReference payloadReference) {
        this.payloadReference = payloadReference;
    }

    public PayloadReference getPayloadReference() {
        return payloadReference;
    }

    public void setPayloadReference(PayloadReference payloadReference) {
        this.payloadReference = payloadReference;
    }
}
