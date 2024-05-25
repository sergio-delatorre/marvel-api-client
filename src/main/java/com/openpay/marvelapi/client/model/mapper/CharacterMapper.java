package com.openpay.marvelapi.client.model.mapper;

import com.openpay.marvelapi.client.model.api.ResultResponse;
import com.openpay.marvelapi.client.model.dto.CharacterResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CharacterMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public CharacterMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CharacterResponse mapToCharacterResponse(ResultResponse resultResponse) {
        CharacterResponse character = modelMapper.map(resultResponse.getData().getResults().get(0), CharacterResponse.class);
        character.setImageUrl(resultResponse.getData().getResults().get(0).getThumbnail().getPath()
                + "." + resultResponse.getData().getResults().get(0).getThumbnail().getExtension());
        return character;
    }
}
