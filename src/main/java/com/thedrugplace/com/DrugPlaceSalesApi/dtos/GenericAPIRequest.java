package com.thedrugplace.com.DrugPlaceSalesApi.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenericAPIRequest<T> {
    @JsonProperty("data")
    private T data;
}