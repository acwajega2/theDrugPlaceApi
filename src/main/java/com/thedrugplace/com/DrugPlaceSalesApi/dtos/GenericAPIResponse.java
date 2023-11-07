package com.thedrugplace.com.DrugPlaceSalesApi.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenericAPIResponse<T> {
    @JsonProperty("status")
    private String status;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private T data;

    public GenericAPIResponse(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}