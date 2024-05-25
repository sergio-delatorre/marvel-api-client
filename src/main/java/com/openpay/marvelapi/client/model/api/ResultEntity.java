package com.openpay.marvelapi.client.model.api;

import lombok.Data;

@Data
public class ResultEntity {

    private Long id;
    private String name;
    private String description;
    private String modified;
    private Thumbnail thumbnail;
}
