package com.pabloriosramirez.api.rickandmorty.domain.impl;

import com.pabloriosramirez.api.rickandmorty.domain.Mapping;

public class HeaderContentMapping extends Mapping {

    private PayloadReference payloadReference;

    public HeaderContentMapping() {
        super.mappingType = HeaderContentMapping.class.getName();
    }

    public HeaderContentMapping(PayloadReference payloadReference) {
        this.payloadReference = payloadReference;
    }

    public PayloadReference getPayloadReference() {
        return payloadReference;
    }

    public void setPayloadReference(PayloadReference payloadReference) {
        this.payloadReference = payloadReference;
    }
}
