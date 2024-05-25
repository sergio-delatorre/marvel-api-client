package com.openpay.marvelapi.client.service;

import com.openpay.marvelapi.client.model.dto.CharacterResponse;

public interface CharacterService {

    CharacterResponse getCharacterById(Long characterId);
}
