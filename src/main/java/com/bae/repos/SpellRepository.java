package com.bae.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bae.entities.Spell;

public interface SpellRepository extends JpaRepository<Spell, Long> {
	
//	Optional<Spell> findSpellByName(String name);
//	
//	List<Spell> findSpellByLevel(int level);
//
//	List<Spell> findSpellBySchool(String school);
//	
//	Optional<Spell> existsByName(String Name);

}
