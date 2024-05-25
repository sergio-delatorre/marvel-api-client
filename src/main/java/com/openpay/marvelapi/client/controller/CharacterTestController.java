package com.openpay.marvelapi.client.controller;

import com.openpay.marvelapi.client.model.dto.CharacterResponse;
import com.openpay.marvelapi.client.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/marvel-api/characters")
public class CharacterTestController {

    private final CharacterService characterService;

    @Autowired
    public CharacterTestController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/{characterId}")
    public CharacterResponse getCharacterById(@PathVariable Long characterId) {
        return characterService.getCharacterById(characterId);
    }

    @GetMapping()
    public List<CharacterResponse> getCharacters() {
        return characterService.getCharacters();
    }
}
