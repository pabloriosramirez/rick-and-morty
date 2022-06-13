package com.pabloriosramirez.api.rickandmorty.domain.impl;

import com.pabloriosramirez.api.rickandmorty.domain.Mapping;

public class DynamicMapping extends Mapping {
    private PayloadReference payloadReference;

    public DynamicMapping() {
        super.mappingType = HeaderContentMapping.class.getName();
    }

    public DynamicMapping(PayloadReference payloadReference) {
        this.payloadReference = payloadReference;
    }

    public PayloadReference getPayloadReference() {
        return payloadReference;
    }

    public void setPayloadReference(PayloadReference payloadReference) {
        this.payloadReference = payloadReference;
    }
}
