package com.bae.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.jdbc.Sql;

import com.bae.entities.Spell;
import com.bae.repos.SpellRepository;

@SpringBootTest
@AutoConfigureMockMvc
//@Sql(scripts = { "classpath:testSchema.sql",
//        "classpath:testData.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
@Transactional
public class SpellServiceIntegrationTest {

	@Autowired
	private SpellService spellService;

	@Autowired
	private SpellRepository spellRepository;

	private List<Spell> spellsInDb;
	private List<Spell> nameOrder;

	@BeforeEach
	public void init() {
		List<Spell> spells = List.of(new Spell("Wish", 9, "Conjuration"), new Spell("Blade Ward", 0, "Abjuration"),
				new Spell("Prismatic Wall", 9, "Abjuration"));
		spellsInDb = new ArrayList<>();
		spellsInDb.addAll(spellRepository.saveAll(spells));
		nameOrder = spellsInDb.stream().sorted(Comparator.comparing(Spell::getName)).collect(Collectors.toList());
	}

	@Test
	public void getAllSpellsTest() {
		assertThat(nameOrder).isEqualTo(spellService.getAllByName());
	}

	@Test
	public void getAllByNameTest() {
		assertThat(nameOrder).isEqualTo(spellService.getAllByName());
	}

	@Test
	public void getAllByLevelTest() {
		List<Spell> levelOrder = nameOrder.stream().sorted(Comparator.comparing(Spell::getLevel))
				.collect(Collectors.toList());
		assertThat(levelOrder).isEqualTo(spellService.getAllByLevel());
	}

	@Test
	public void getAllBySchoolTest() {
		List<Spell> schoolOrder = nameOrder.stream().sorted(Comparator.comparing(Spell::getSchool))
				.collect(Collectors.toList());
		assertThat(schoolOrder).isEqualTo(spellService.getAllBySchool());
	}

	@Test
	public void createSpellTest() {
		Spell newSpell = new Spell("Scrying", 5, "Divination");
		Spell expectedSpell = new Spell(newSpell.getName(), newSpell.getLevel(), newSpell.getSchool());

		assertThat(expectedSpell).isEqualTo(spellService.create(newSpell));
	}

	@Test
	public void getSpellByNameTest() {
		Spell expectedSpell = new Spell("Prismatic Wall", 9, "Abjuration");
		String spellName = "Prismatic Wall";

		assertThat(expectedSpell).isEqualTo(spellService.getByName(spellName));
	}

	@Test
	public void getSpellByLevelTest() {
		List<Spell> expectedSpells = List.of(new Spell("Prismatic Wall", 9, "Abjuration"),
				new Spell("Wish", 9, "Conjuration"));
		int spellLevel = 9;

		assertThat(expectedSpells).isEqualTo(spellService.getByLevel(spellLevel));
	}

	@Test
	public void getSpellBySchoolTest() {
		List<Spell> expectedSpells = List.of(new Spell("Blade Ward", 0, "Abjuration"),
				new Spell("Prismatic Wall", 9, "Abjuration"));
		String spellSchool = "Abjuration";

		assertThat(expectedSpells).isEqualTo(spellService.getBySchool(spellSchool));
	}

	@Test
	public void updateSpellTest() {
		Spell updatedSpell = new Spell("Prismatic Wall", 7, "Necromancy");
		Spell toUpdateWith = new Spell("Prismatic Wall", 7, "Necromancy");
		String spellName = "Prismatic Wall";

		assertThat(updatedSpell).isEqualTo(spellService.updateByName(spellName, toUpdateWith));
	}

	@Test
	public void deleteSpellTest() {
		Spell expectedSpell = spellsInDb.get(0);
		String spellName = expectedSpell.getName();

		spellService.deleteByName(spellName);

		assertThat(spellRepository.findSpellByName(spellName)).isEqualTo(Optional.empty());
	}

}
