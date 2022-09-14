package com.bae.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.bae.entities.Spell;
import com.bae.repos.SpellRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
public class SpellControllerSystemIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private SpellRepository spellRepository;

	private List<Spell> spellsInDb;
	private Spell expectedSpell;

	@BeforeEach
	public void init() {
		List<Spell> spells = List.of(new Spell(1L, "Wish", 9, "Conjuration"), new Spell(2L, "Blade Ward", 0, "Abjuration"),
				new Spell(3L, "Prismatic Wall", 9, "Abjuration"));
		spellsInDb = new ArrayList<>();
		spellsInDb.addAll(spellRepository.saveAll(spells));
		expectedSpell = new Spell(3L, "Prismatic Wall", 9, "Abjuration");
		System.out.println(expectedSpell);
		System.out.println(spells);
	}

	@Test
	public void getAllSpellsTest() throws Exception {
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/spell");

		mockRequest.accept(MediaType.APPLICATION_JSON);

		String spells = objectMapper.writeValueAsString(spellsInDb);

		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contMatcher = MockMvcResultMatchers.content().json(spells);

		mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contMatcher);
	}

	@Test
	public void getAllSpellsByNameTest() throws Exception {
		List<Spell> nameOrder = spellsInDb.stream().sorted(Comparator.comparing(Spell::getName))
				.collect(Collectors.toList());
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/spell/name");

		mockRequest.accept(MediaType.APPLICATION_JSON);

		String spells = objectMapper.writeValueAsString(nameOrder);

		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contMatcher = MockMvcResultMatchers.content().json(spells);

		mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contMatcher);
	}

	@Test
	public void getAllSpellsByLevelTest() throws Exception {
		List<Spell> levelOrder = spellsInDb.stream().sorted(Comparator.comparing(Spell::getLevel))
				.collect(Collectors.toList());
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/spell/lv");

		mockRequest.accept(MediaType.APPLICATION_JSON);

		String spells = objectMapper.writeValueAsString(levelOrder);

		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contMatcher = MockMvcResultMatchers.content().json(spells);

		mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contMatcher);
	}

	@Test
	public void getAllSpellsBySchoolTest() throws Exception {
		List<Spell> schoolOrder = spellsInDb.stream().sorted(Comparator.comparing(Spell::getSchool))
				.collect(Collectors.toList());

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/spell/school");

		mockRequest.accept(MediaType.APPLICATION_JSON);

		String spells = objectMapper.writeValueAsString(schoolOrder);

		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contMatcher = MockMvcResultMatchers.content().json(spells);

		mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contMatcher);
	}

	@Test
	public void getSpellByNameTest() throws Exception {
		String name = expectedSpell.getName();
		Spell spellToFind = spellRepository.getByName(name);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/spell/" + name);

		mockRequest.contentType(MediaType.APPLICATION_JSON);

		mockRequest.content(objectMapper.writeValueAsString(spellToFind));

		mockRequest.accept(MediaType.APPLICATION_JSON);

		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(expectedSpell));

		mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}
	
	@Test
	public void getSpellByIdTest() throws Exception {
		Long id = expectedSpell.getId();
		Spell spellToFind = spellRepository.getById(id);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/spell/id/" + id);

		mockRequest.contentType(MediaType.APPLICATION_JSON);

		mockRequest.content(objectMapper.writeValueAsString(spellToFind));

		mockRequest.accept(MediaType.APPLICATION_JSON);

		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(expectedSpell));

		mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}

	@Test
	void updateSpellByNameTest() throws Exception {
		// TODO
		String name = expectedSpell.getName();
		Spell updatesToMake = new Spell(expectedSpell.getName(), expectedSpell.getLevel() - 1, expectedSpell.getSchool());
		Spell updatedSpell = new Spell(expectedSpell.getId(), expectedSpell.getName(), expectedSpell.getLevel() - 1,
				expectedSpell.getSchool());

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.PUT, "/spell/" + name);

		mockRequest.contentType(MediaType.APPLICATION_JSON);

		mockRequest.content(objectMapper.writeValueAsString(updatesToMake));

		mockRequest.accept(MediaType.APPLICATION_JSON);

		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isAccepted();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(updatedSpell));

		mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}

	@Test
	void deleteSpellByNameTest() throws Exception {
		String name = expectedSpell.getName();

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE, "/spell/" + name);

		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isAccepted();

		mockMvc.perform(mockRequest).andExpect(statusMatcher);
	}
	
	@Test
	void deleteSpellByIDTest() throws Exception {
		String id = "1";

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE, "/spell?id=" + id);

		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isAccepted();

		mockMvc.perform(mockRequest).andExpect(statusMatcher);
	}

}
