package com.pabloriosramirez.api.rickandmorty.activator.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pabloriosramirez.api.rickandmorty.activator.ServiceActivator;
import com.pabloriosramirez.api.rickandmorty.configuration.domain.ApplicationProperties;
import com.pabloriosramirez.api.rickandmorty.configuration.domain.Service;
import com.pabloriosramirez.api.rickandmorty.constant.PayloadTypeEnum;
import com.pabloriosramirez.api.rickandmorty.domain.impl.ServiceContext;
import com.pabloriosramirez.api.rickandmorty.exception.domain.ConfigurationException;
import com.pabloriosramirez.api.rickandmorty.exception.domain.RestServiceActivatorException;
import com.pabloriosramirez.api.rickandmorty.util.DurationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;


public class BasicRestServiceActivator implements ServiceActivator {

    private final static Logger LOGGER = LoggerFactory.getLogger(BasicRestServiceActivator.class);
    private ServiceContext serviceContext;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    ApplicationProperties applicationProperties;

    @Override
    public Map<String, Object> invoke(@Payload Map<String, Object> payload, @Headers Map<String, Object> headers) throws Exception {
        Date serviceActivatorExecutionStartTime = new Date();
        LOGGER.info("Executing Basic Rest Service Activator for Service %s and application %s", this.serviceContext.getConfigurationElementId(), this.serviceContext.getApplicationId());
        try {
            JsonNode currentResponse = (JsonNode) payload.get("CurrentResponse");
            this.addServiceRequestToResponseMap(payload, currentResponse, this.serviceContext.getConfigurationElementId());
            final HttpEntity<Object> httpEntity = this.buildHttpEntity(currentResponse, headers, this.serviceContext);
            final Map<String, String> uriVariables = createUriVariables(currentResponse);
            ResponseEntity<?> response = this.executeRequest(
                    this.serviceContext,
                    httpEntity,
                    uriVariables);
            LOGGER.info(String.format("Finished %s Service Activator", this.serviceContext.getConfigurationElementId()));
            this.addServiceResponseToResponseMap(payload, (JsonNode) response.getBody(), this.serviceContext.getConfigurationElementId());
        } catch (Exception exception) {
            LOGGER.error("An unexpected error happened during execution of service activator: " + exception.getMessage());
            throw exception;
        } finally {
            DurationUtil.log(serviceActivatorExecutionStartTime, LOGGER, "Basic Service Activator Execution.");
        }
        return payload;
    }

    private Map<String, String> createUriVariables(JsonNode currentResponse) {
        Map<String, String> uri = new HashMap<>();
        final Iterator<String> iterator = currentResponse.fieldNames();
        iterator.forEachRemaining(field -> {
            if (!currentResponse.get(field).isMissingNode() && !currentResponse.get(field).isArray() && !currentResponse.get(field).isObject()) {
                uri.put(field, currentResponse.get(field).asText());
            }
        });
        return uri;
    }

    public ResponseEntity<Object> executeRequest(ServiceContext serviceContext, HttpEntity<Object> httpEntity, Map<String, String> uriVariables) throws Exception {
        Date requestExecutionStartTime = new Date();
        ResponseEntity response;
        try {
            response = this.restTemplate.exchange(
                    this.getApplicationUrl(serviceContext),
                    HttpMethod.resolve(serviceContext.getServiceCallMethod()),
                    httpEntity,
                    JsonNode.class,
                    uriVariables);
        } catch (RestClientResponseException restClientResponseException) {
            throw new RestServiceActivatorException(this.buildErrorMessage(serviceContext, restClientResponseException));
        } catch (Exception exception) {
            throw new RestServiceActivatorException(exception);
        }
        DurationUtil.log(requestExecutionStartTime, LOGGER, "Request execution - Exchange.");
        return response;
    }

    public HttpEntity<Object> buildHttpEntity(JsonNode payload, Map<String, Object> headers, ServiceContext serviceContext) throws Exception {
        HttpHeaders requestHeader = this.buildHeaders(headers, serviceContext);
        if (HttpMethod.resolve(serviceContext.getServiceCallMethod()).equals(HttpMethod.GET)) {
            return new HttpEntity<>(requestHeader);
        } else {
            JsonNode requestBody = this.buildBody(payload, serviceContext);
            return new HttpEntity<>(requestBody, requestHeader);
        }
    }

    private HttpHeaders buildHeaders(Map<String, Object> headers, ServiceContext serviceContext) {
        String authentication = (String) headers.get("authorization");
        return this.createHeaders(serviceContext, authentication);
    }

    private String getApplicationUrl(ServiceContext serviceContext) {
        Service service = this.retrieveServiceInfo(serviceContext.getApplicationId());
        return service.getHomePageUrl().concat(serviceContext.getServicePath());
    }

    private Service retrieveServiceInfo(String applicationId) {
        List<Service> services = this.applicationProperties.getServices();
        for (Service service : services) {
            if (applicationId.equals(service.getApplicationIdentifier())) {
                return service;
            }
        }
        throw new ConfigurationException("Service don't configured");
    }

    private JsonNode buildBody(JsonNode payload, ServiceContext serviceContext) throws Exception {
        Date buildBodyStartTime = new Date();
        JsonNode requestData = (JsonNode) payload.get(serviceContext.getConfigurationElementId() + "_" + PayloadTypeEnum.REQUEST);
        if (requestData == null) {
            requestData = (JsonNode) payload.get("CurrentResponse");
            if (requestData == null) {
                requestData = (JsonNode) payload.get("INITIAL_REQUEST");
                if (requestData == null) {
                    throw new RuntimeException("Couldn´t find a request for the servie activator: " + serviceContext.getConfigurationElementId());
                }
            }
        }
        DurationUtil.log(buildBodyStartTime, LOGGER, "Body creation");
        return requestData;
    }

    private String buildErrorMessage(ServiceContext serviceContext, RestClientResponseException e) throws IOException {
        JsonNode jsonNode = this.objectMapper.readTree(e.getResponseBodyAsString());
        return String.format("An error occurred executing %s service activator: %s (status: %d)",
                serviceContext.getApplicationId(),
                jsonNode.get("message") != null ? jsonNode.get("message").asText() : e.getMessage(),
                e.getRawStatusCode());
    }

    private void addServiceRequestToResponseMap(Map<String, Object> payload, Object requestBody, String serviceIdentifier) {
        payload.put(serviceIdentifier + "_" + PayloadTypeEnum.REQUEST, requestBody);
    }

    private void addServiceResponseToResponseMap(Map<String, Object> payload, JsonNode responseBody, String serviceIdentifier) {
        payload.put(serviceIdentifier + "_" + PayloadTypeEnum.RESPONSE, responseBody);
        payload.put("CurrentResponse", responseBody);
    }

    private HttpHeaders createHeaders(ServiceContext serviceContext, String authentication) {
        Date createHeaderStartTime = new Date();
        HttpHeaders headers = new HttpHeaders();
        serviceContext.getServiceHeaders().forEach((k, v) -> {
            headers.add(k.toLowerCase(), v);
        });
        if (headers.get("authorization") == null && authentication != null) {
            headers.add("authorization", authentication);
        }
        headers.add("correlation-id", MDC.get("correlationId"));
        DurationUtil.log(createHeaderStartTime, LOGGER, "Headers creation");
        return headers;
    }

    public ServiceContext getServiceContext() {
        return serviceContext;
    }

    public void setServiceContext(ServiceContext serviceContext) {
        this.serviceContext = serviceContext;
    }
}
