package com.openpay.marvelapi.client.model.mapper;

import com.openpay.marvelapi.client.model.api.ResultEntity;
import com.openpay.marvelapi.client.model.dto.CharacterResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Componente para mapear objetos de respuesta de la API a un objeto Character con el formato y la información necesaria
 *
 * @author Sergio de la Torre
 * @see ResultEntity
 * @see CharacterResponse
 * @version 1.1
 * @since 2024-05-25
 */
@Component
public class CharacterMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public CharacterMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CharacterResponse mapToCharacterResponse(ResultEntity resultEntity) {
        CharacterResponse character = modelMapper.map(resultEntity, CharacterResponse.class);
        character.setImageUrl(resultEntity.getThumbnail().getPath()
                + "." + resultEntity.getThumbnail().getExtension());
        return character;
    }
}
