package com.openpay.marvelapi.client.model.dto;

import lombok.Data;

@Data
public class CharacterResponse {

    private Long id;
    private String name;
    private String description;
    private String imageUrl;
}
