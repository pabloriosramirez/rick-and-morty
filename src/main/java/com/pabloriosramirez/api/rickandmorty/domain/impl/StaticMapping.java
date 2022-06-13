package com.pabloriosramirez.api.rickandmorty.domain.impl;

import com.pabloriosramirez.api.rickandmorty.domain.Mapping;

public class StaticMapping extends Mapping {
    private PayloadReference payloadReference;

    public StaticMapping() {
        super.mappingType = HeaderContentMapping.class.getName();
    }

    public StaticMapping(PayloadReference payloadReference) {
        this.payloadReference = payloadReference;
    }

    public PayloadReference getPayloadReference() {
        return payloadReference;
    }

    public void setPayloadReference(PayloadReference payloadReference) {
        this.payloadReference = payloadReference;
    }
}
