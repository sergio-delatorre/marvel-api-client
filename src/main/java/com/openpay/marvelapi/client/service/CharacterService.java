package com.openpay.marvelapi.client.service;

import com.openpay.marvelapi.client.model.dto.CharacterResponse;

/**
 * Define los métodos asociados al consumo de los servicios de 'characters'
 *
 * @author Sergio de la Torre
 * @see CharacterResponse
 * @version 1.0
 * @since 2024-05-25
 */
public interface CharacterService {


    /**
     * Obtiene la información de un personaje a partir de su ID.
     *
     * @param characterId el ID del personaje a buscar
     * @return la respuesta con los detalles del personaje
     */
    CharacterResponse getCharacterById(Long characterId);
}
