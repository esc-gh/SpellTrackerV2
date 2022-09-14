package com.bae.entities;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class SpellClassTest {

	private Spell spell;

	@BeforeEach
	public void init() {
		spell = new Spell("Prismatic Wall", 9, "Abjuration");
	}

	@Test
	public void toStringTest() {
		String expected = "Spell: " + spell.getName() + ", lv: " + spell.getLevel() + ", School: " + spell.getSchool();
		assertThat(spell.toString()).isEqualTo(expected);
	}

	@Test
	public void hashCodeTest() {
		Object expected = Objects.hash(spell.getName(), spell.getLevel(), spell.getSchool());
		assertThat(spell.hashCode()).isEqualTo(expected);
	}

	@Test
	public void equalsTest() {
		Spell spell2 = new Spell();
		Integer i = 1;
		assertThat(spell.equals(spell2)).isEqualTo(false);
		assertThat(spell.equals(null)).isEqualTo(false);
		assertThat(spell.equals(i)).isEqualTo(false);
	}

}
