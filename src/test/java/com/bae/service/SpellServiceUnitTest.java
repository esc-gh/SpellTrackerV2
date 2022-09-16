package com.bae.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bae.entities.Spell;
import com.bae.repos.SpellRepository;
import com.bae.exceptions.SpellNotFoundException;

@ExtendWith(MockitoExtension.class)
public class SpellServiceUnitTest {

	@Mock
	private SpellRepository spellRepository;

	@InjectMocks
	private SpellService spellService;

	private List<Spell> spells;
	private Spell expectedSpell;

	@BeforeEach
	public void init() {
		spells = List.of(new Spell("Wish", 9, "Conjuration"), new Spell("Blade Ward", 0, "Abjuration"),
				new Spell("Prismatic Wall", 9, "Abjuration"));
		expectedSpell = new Spell("Prismatic Wall", 9, "Abjuration");
	}

	@Test
	public void getAllSpellsTest() {
		when(spellRepository.findAll()).thenReturn(spells);
		assertThat(spellService.getAll()).isEqualTo(spells);
		verify(spellRepository).findAll();
	}

	@Test
	public void getAllSpellsByNameTest() {
		List<Spell> schoolOrder = spells.stream().sorted(Comparator.comparing(Spell::getName))
				.collect(Collectors.toList());

		when(spellRepository.findAll()).thenReturn(spells);
		assertThat(spellService.getAllByName()).isEqualTo(schoolOrder);
		verify(spellRepository).findAll();
	}

	@Test
	public void getAllSpellsByNameLevel() {
		List<Spell> nameOrder = spells.stream().sorted(Comparator.comparing(Spell::getName))
				.collect(Collectors.toList());
		List<Spell> levelOrder = nameOrder.stream().sorted(Comparator.comparing(Spell::getLevel))
				.collect(Collectors.toList());

		when(spellRepository.findAll()).thenReturn(spells);
		assertThat(spellService.getAllByLevel()).isEqualTo(levelOrder);
		verify(spellRepository).findAll();
	}

	@Test
	public void getAllSpellsByNameSchool() {
		List<Spell> schoolOrder = spells.stream().sorted(Comparator.comparing(Spell::getSchool))
				.collect(Collectors.toList());

		when(spellRepository.findAll()).thenReturn(spells);
		assertThat(spellService.getAllBySchool()).isEqualTo(schoolOrder);
		verify(spellRepository).findAll();
	}

	@Test
	public void getByNameTest() {
		String name = expectedSpell.getName();

		when(spellRepository.findSpellByName(name)).thenReturn(Optional.of(expectedSpell));
		assertThat(spellService.getByName(name)).isEqualTo(expectedSpell);
		verify(spellRepository).findSpellByName(name);
	}

	@Test
	public void getByNameInvalidTest() {
		String name = "Afkjesgjskfj";

		when(spellRepository.findSpellByName(name)).thenReturn(Optional.empty());

		SpellNotFoundException snfe = Assertions.assertThrows(SpellNotFoundException.class, () -> {
			spellService.getByName(name);
		});

		String expected = "Spell with name '" + name + "' cannot be found";
		assertThat(snfe.getMessage()).isEqualTo(expected);

		verify(spellRepository).findSpellByName(name);
	}
	
//	@Test
//	public void getByIdInvalidTest() {
//		Long id = 17990381L;
//
//		when(spellRepository.findSpellById(id)).thenReturn(Optional.empty());
//
//		SpellNotFoundException snfe = Assertions.assertThrows(SpellNotFoundException.class, () -> {
//			spellService.getById(id);
//		});
//
//		String expected = "Spell with ID '" + id + "' cannot be found";
//		assertThat(snfe.getMessage()).isEqualTo(expected);
//
//		verify(spellRepository).findSpellById(id);
//	}

	@Test
	public void getByLevelTest() {
		List<Spell> levelOrder = spells.stream().sorted(Comparator.comparing(Spell::getName))
				.collect(Collectors.toList());
		int level = expectedSpell.getLevel();

		when(spellRepository.findSpellByLevel(level)).thenReturn(levelOrder);
		assertThat(spellService.getByLevel(level)).isEqualTo(levelOrder);
		verify(spellRepository).findSpellByLevel(level);
	}

	@Test
	public void getBySchoolTest() {
		List<Spell> schoolOrder = spells.stream().sorted(Comparator.comparing(Spell::getSchool))
				.collect(Collectors.toList());
		String school = expectedSpell.getSchool();

		when(spellRepository.findSpellBySchool(school)).thenReturn(schoolOrder);
		assertThat(spellService.getBySchool(school)).isEqualTo(schoolOrder);
		verify(spellRepository).findSpellBySchool(school);
	}

	@Test
	public void createSpellTest() {
		when(spellRepository.save(expectedSpell)).thenReturn(expectedSpell);
		assertThat(spellService.create(expectedSpell)).isEqualTo(expectedSpell);
		verify(spellRepository).save(expectedSpell);
	}

	@Test
	public void updateSpellTest() {
		String name = expectedSpell.getName();
		Spell updatesToMake = new Spell(expectedSpell.getName(), expectedSpell.getLevel() + 1, expectedSpell.getSchool());
		Spell updatedSpell = new Spell(expectedSpell.getId(), expectedSpell.getName(), expectedSpell.getLevel() + 1,
				expectedSpell.getSchool());

		when(spellRepository.existsByName(name)).thenReturn(true);
		when(spellRepository.getByName(name)).thenReturn(expectedSpell);
		when(spellRepository.save(expectedSpell)).thenReturn(updatedSpell);

		assertThat(spellService.updateByName(name, updatesToMake)).isEqualTo(updatedSpell);

		verify(spellRepository).existsByName(name);
		verify(spellRepository).getByName(name);
		verify(spellRepository).save(expectedSpell);
	}

	@Test
	public void updateSpellByNameInvalidTest() {
		String name = "Afkjesgjskfj";

		when(spellRepository.existsByName(name)).thenReturn(false);

		SpellNotFoundException snfe = Assertions.assertThrows(SpellNotFoundException.class, () -> {
			spellService.updateByName(name, expectedSpell);
		});

		String expected = "Spell with name '" + name + "' cannot be found";
		assertThat(snfe.getMessage()).isEqualTo(expected);

		verify(spellRepository).existsByName(name);
	}

	@Test
	public void updateSpellByIdInvalidTest() {
		Long id = 17990381L;

		when(spellRepository.existsById(id)).thenReturn(false);

		SpellNotFoundException snfe = Assertions.assertThrows(SpellNotFoundException.class, () -> {
			spellService.updateById(id, expectedSpell);
		});

		String expected = "Spell with ID '" + id + "' cannot be found";
		assertThat(snfe.getMessage()).isEqualTo(expected);

		verify(spellRepository).existsById(id);
	}
	
	@Test
	public void deleteSpellTest() {
		String name = expectedSpell.getName();
		when(spellRepository.existsByName(name)).thenReturn(true);

		spellService.deleteByName(name);

		assertThat(spellRepository.findSpellByName(name)).isEqualTo(Optional.empty());

		verify(spellRepository).existsByName(name);
		verify(spellRepository).deleteByName(name);
	}

	@Test
	public void deleteSpellByNameInvalidTest() {
		String name = "Afkjesgjskfj";

		when(spellRepository.existsByName(name)).thenReturn(false);

		SpellNotFoundException snfe = Assertions.assertThrows(SpellNotFoundException.class, () -> {
			spellService.deleteByName(name);
		});

		String expected = "Spell with name '" + name + "' cannot be found";
		assertThat(snfe.getMessage()).isEqualTo(expected);

		verify(spellRepository).existsByName(name);
	}
	
	@Test
	public void deleteSpellByIdInvalidTest() {
		Long id = 17990381L;

		when(spellRepository.existsById(id)).thenReturn(false);

		SpellNotFoundException snfe = Assertions.assertThrows(SpellNotFoundException.class, () -> {
			spellService.deleteById(id);
		});

		String expected = "Spell with ID '" + id + "' cannot be found";
		assertThat(snfe.getMessage()).isEqualTo(expected);

		verify(spellRepository).existsById(id);
	}
}
