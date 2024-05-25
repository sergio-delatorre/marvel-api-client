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
import java.util.List;
import java.util.stream.Collectors;

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
        String url = buildUri(MarvelApiConstants.BASE_URL + MarvelApiConstants.CHARACTERS_URI + "/" + characterId);

        ResultResponse response = restTemplate.getForObject(url, ResultResponse.class);
        return characterMapper.mapToCharacterResponse(response.getData().getResults().get(0));
    }

    @Override
    public List<CharacterResponse> getCharacters() {
        String url = buildUri(MarvelApiConstants.BASE_URL + MarvelApiConstants.CHARACTERS_URI);

        ResultResponse response = restTemplate.getForObject(url, ResultResponse.class);
        return response.getData().getResults().stream()
                .map(characterMapper::mapToCharacterResponse)
                .collect(Collectors.toList());
    }

    private String getTimeStamp() {
        return String.valueOf(Instant.now().getEpochSecond());
    }

    private String buildUri(String httpUrl){
        return UriComponentsBuilder.fromHttpUrl(httpUrl)
                .queryParam(MarvelApiConstants.QUERY_PARAM_TIME_STAMP, getTimeStamp())
                .queryParam(MarvelApiConstants.QUERY_PARAM_API_KEY, publicKey)
                .queryParam(MarvelApiConstants.QUERY_PARAM_HASH, HashUtil.generateMD5Hash(getTimeStamp() + privateKey + publicKey))
                .toUriString();
    }

}
