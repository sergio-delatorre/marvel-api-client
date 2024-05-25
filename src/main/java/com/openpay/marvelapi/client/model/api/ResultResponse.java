package com.openpay.marvelapi.client.model.api;

import lombok.Data;

@Data
public class ResultResponse {

    private Integer code;
    private String status;
    private String copyright;
    private String attributionText;
    private String attributionHTML;
    private String etag;
    private DataWrapper data;
}
