package com.openpay.marvelapi.client;

import com.openpay.marvelapi.client.model.dto.CharacterResponse;
import com.openpay.marvelapi.client.service.CharacterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MarvelApiClientApplicationTests {

	@Autowired
	private CharacterService characterService;

	@Test
	void getCharacterById_ValidId() {
		Long characterId = 1017100L; // A-Bomb (HAS)

		CharacterResponse response = characterService.getCharacterById(characterId);

		Assertions.assertNotNull(response);
		Assertions.assertNotNull(response.getName());
		Assertions.assertNotNull(response.getDescription());
		Assertions.assertNotNull(response.getImageUrl());
		Assertions.assertEquals(characterId,response.getId());
	}

	@Test
	void getCharacters_ValidCharacters() {
		List<CharacterResponse> response = characterService.getCharacters();

		Assertions.assertNotNull(response);
		Assertions.assertTrue(response.size() > 0);

		CharacterResponse firstCharacter = response.get(0);
		Assertions.assertNotNull(firstCharacter.getName());
		Assertions.assertNotNull(firstCharacter.getImageUrl());
		Assertions.assertTrue(firstCharacter.getId() > 0);
	}

}
