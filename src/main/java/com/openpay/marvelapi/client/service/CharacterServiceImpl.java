package com.openpay.marvelapi.client.service;

import com.openpay.marvelapi.client.model.api.ResultResponse;
import com.openpay.marvelapi.client.model.dto.CharacterResponse;
import com.openpay.marvelapi.client.model.mapper.CharacterMapper;
import com.openpay.marvelapi.client.util.MarvelApiConstants;
import com.openpay.marvelapi.client.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Instant;

@Service
public class CharacterServiceImpl implements CharacterService{

    @Value("${marvel.api.public.key}")
    private String publicKey;

    @Value("${marvel.api.private.key}")
    private String privateKey;

    private final RestTemplate restTemplate;
    private final CharacterMapper characterMapper;

    @Autowired
    public CharacterServiceImpl(RestTemplate restTemplate, CharacterMapper characterMapper) {
        this.restTemplate = restTemplate;
        this.characterMapper = characterMapper;
    }
    @Override
    public CharacterResponse getCharacterById(Long characterId) {
        String url = UriComponentsBuilder.fromHttpUrl(MarvelApiConstants.BASE_URL + MarvelApiConstants.CHARACTERS_URI + "/" + characterId)
                .queryParam("ts", getTimeStamp())
                .queryParam("apikey", publicKey)
                .queryParam("hash", HashUtil.generateMD5Hash(getTimeStamp() + privateKey + publicKey))
                .toUriString();

        ResultResponse response = restTemplate.getForObject(url, ResultResponse.class);
        return characterMapper.mapToCharacterResponse(response);
    }

    private String getTimeStamp() {
        return String.valueOf(Instant.now().getEpochSecond());
    }

}
