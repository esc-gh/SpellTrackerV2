package com.bae.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bae.entities.Spell;
import com.bae.service.SpellService;

@WebMvcTest(SpellController.class)
public class SpellControllerWebIntegrationTest {

	@Autowired
	private SpellController controller;

	@MockBean
	private SpellService spellService;

	private List<Spell> spells;
	private Spell newSpell;
	private Spell expectedSpell;
	private String name;
	private int level;
	private String school;

	@BeforeEach
	public void init() {
		spells = List.of(new Spell("Wish", 9, "Conjuration"), new Spell("Blade Ward", 0, "Abjuration"),
				new Spell("Prismatic Wall", 9, "Abjuration"));
		newSpell = new Spell("Scrying", 5, "Divination");
		name = newSpell.getName();
		level = newSpell.getLevel();
		school = newSpell.getSchool();
		expectedSpell = new Spell(newSpell.getName(), newSpell.getLevel(), newSpell.getSchool());
	}

	@Test
	public void getSpellsTest() {
		ResponseEntity<List<Spell>> expected = new ResponseEntity<List<Spell>>(spells, HttpStatus.OK);
		when(spellService.getAll()).thenReturn(spells);

		ResponseEntity<List<Spell>> actual = controller.getSpells();
		assertThat(expected).isEqualTo(actual);

		verify(spellService).getAll();
	}

	@Test
	public void getSpellsByNameTest() {
		List<Spell> nameOrder = spells.stream().sorted(Comparator.comparing(Spell::getName))
				.collect(Collectors.toList());
		ResponseEntity<List<Spell>> expected = new ResponseEntity<List<Spell>>(nameOrder, HttpStatus.OK);
		when(spellService.getAllByName()).thenReturn(nameOrder);

		ResponseEntity<List<Spell>> actual = controller.getSpellsByName();
		assertThat(expected).isEqualTo(actual);

		verify(spellService).getAllByName();
	}

	@Test
	public void getSpellsByLevelTest() {
		List<Spell> levelOrder = spells.stream().sorted(Comparator.comparing(Spell::getLevel))
				.collect(Collectors.toList());
		ResponseEntity<List<Spell>> expected = new ResponseEntity<List<Spell>>(levelOrder, HttpStatus.OK);
		when(spellService.getAllByLevel()).thenReturn(levelOrder);

		ResponseEntity<List<Spell>> actual = controller.getSpellsByLevel();
		assertThat(expected).isEqualTo(actual);

		verify(spellService).getAllByLevel();
	}

	@Test
	public void getSpellsBySchoolTest() {
		List<Spell> schoolOrder = spells.stream().sorted(Comparator.comparing(Spell::getSchool))
				.collect(Collectors.toList());
		ResponseEntity<List<Spell>> expected = new ResponseEntity<List<Spell>>(schoolOrder, HttpStatus.OK);
		when(spellService.getAllBySchool()).thenReturn(schoolOrder);

		ResponseEntity<List<Spell>> actual = controller.getSpellsBySchool();
		assertThat(expected).isEqualTo(actual);

		verify(spellService).getAllBySchool();
	}

	@Test
	public void getSpellByNameTest() {
		ResponseEntity<Spell> expected = ResponseEntity.of(Optional.of(expectedSpell));
		when(spellService.getByName(name)).thenReturn(expectedSpell);

		ResponseEntity<Spell> actual = controller.getSpellByName(name);
		assertThat(expected).isEqualTo(actual);

		verify(spellService).getByName(name);
	}

	@Test
	public void getSpellByLevelTest() {
		List<Spell> levelOrder = spells.stream().sorted(Comparator.comparing(Spell::getLevel))
				.collect(Collectors.toList());
		ResponseEntity<List<Spell>> expected = new ResponseEntity<List<Spell>>(levelOrder, HttpStatus.OK);
		when(spellService.getByLevel(level)).thenReturn(levelOrder);

		ResponseEntity<List<Spell>> actual = controller.getSpellByLevel(level);
		assertThat(expected).isEqualTo(actual);

		verify(spellService).getByLevel(level);
	}

	@Test
	public void getSpellBySchoolTest() {
		List<Spell> schoolOrder = spells.stream().sorted(Comparator.comparing(Spell::getSchool))
				.collect(Collectors.toList());
		ResponseEntity<List<Spell>> expected = new ResponseEntity<List<Spell>>(schoolOrder, HttpStatus.OK);
		when(spellService.getBySchool(school)).thenReturn(schoolOrder);

		ResponseEntity<List<Spell>> actual = controller.getSpellBySchool(school);
		assertThat(expected).isEqualTo(actual);

		verify(spellService).getBySchool(school);
	}

	@Test
	public void createSpellTest() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/spell/" + String.valueOf(expectedSpell.getName()));
		ResponseEntity<Spell> expected = new ResponseEntity<Spell>(expectedSpell, headers, HttpStatus.CREATED);
		when(spellService.create(newSpell)).thenReturn(expectedSpell);

		ResponseEntity<Spell> actual = controller.createSpell(newSpell);
		assertThat(expected).isEqualTo(actual);

		verify(spellService).create(newSpell);
	}

	@Test
	public void updateSpellTest() {
		String name = expectedSpell.getName();
		Spell updatesToMake = new Spell(expectedSpell.getName(), expectedSpell.getLevel() + 1, expectedSpell.getSchool());
		Spell updatedSpell = new Spell(expectedSpell.getName(), expectedSpell.getLevel() + 1,
				expectedSpell.getSchool());
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/spell/" + String.valueOf(updatedSpell.getName()));

		ResponseEntity<Spell> expected = new ResponseEntity<Spell>(updatedSpell, headers, HttpStatus.ACCEPTED);
		when(spellService.updateByName(name, updatesToMake)).thenReturn(updatedSpell);

		ResponseEntity<Spell> actual = controller.updateSpell(name, updatesToMake);
		assertThat(expected).isEqualTo(actual);

		verify(spellService).updateByName(name, updatesToMake);
	}

	@Test
	void deleteSpellTest() {
		String name = expectedSpell.getName();
		ResponseEntity<?> expected = ResponseEntity.accepted().build();
		ResponseEntity<?> actual = controller.deleteSpell(name);

		assertThat(expected).isEqualTo(actual);

		verify(spellService).deleteByName(name);

	}

}
