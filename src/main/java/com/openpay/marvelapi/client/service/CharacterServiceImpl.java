package com.openpay.marvelapi.client.service;

import com.openpay.marvelapi.client.util.Constants;
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

    @Autowired
    public CharacterServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public String getCharacterById(String characterId) {
        String url = UriComponentsBuilder.fromHttpUrl(Constants.BASE_URL + Constants.CHARACTERS_URI)
                .queryParam("ts", getTimeStamp())
                .queryParam("apikey", publicKey)
                .queryParam("hash", HashUtil.generateMD5Hash(getTimeStamp() + privateKey + publicKey))
                .toUriString();

        return restTemplate.getForObject(url, String.class);
    }

    private String getTimeStamp() {
        return String.valueOf(Instant.now().getEpochSecond());
    }

}
