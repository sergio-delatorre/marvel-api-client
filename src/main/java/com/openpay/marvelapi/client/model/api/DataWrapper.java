package com.openpay.marvelapi.client.model.api;

import lombok.Data;

import java.util.List;

@Data
public class DataWrapper {

    private Integer offset;
    private Integer limit;
    private Integer total;
    private Integer count;
    private List<ResultEntity> results;
}
