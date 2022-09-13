package com.bae.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bae.entities.Spell;
import com.bae.repos.SpellRepository;
import com.bae.exceptions.SpellNotFoundException;

@Service
public class SpellService {
	
	private SpellRepository spellRepository;
	private SpellService spellService;

	@Autowired
	public SpellService(SpellRepository spellRepository) {
		this.spellRepository = spellRepository;
	}

	public List<Spell> getAll() {
		return spellRepository.findAll();
	}

	public List<Spell> getAllByName() {
		List<Spell> spells = spellRepository.findAll();
		List<Spell> nameOrder = spells.stream().sorted(Comparator.comparing(Spell::getName))
				.collect(Collectors.toList());
		return nameOrder;
	}

	public List<Spell> getAllByLevel() {
		List<Spell> spells = spellRepository.findAll();
		List<Spell> nameOrder = spells.stream().sorted(Comparator.comparing(Spell::getName))
				.collect(Collectors.toList());
		List<Spell> levelOrder = nameOrder.stream().sorted(Comparator.comparing(Spell::getLevel))
				.collect(Collectors.toList());
		return levelOrder;
	}

	public List<Spell> getAllBySchool() {
		List<Spell> spells = spellRepository.findAll();
		List<Spell> schoolOrder = spells.stream().sorted(Comparator.comparing(Spell::getSchool))
				.collect(Collectors.toList());
		return schoolOrder;
	}

	public Spell getById(Long id) {
		return spellRepository.findById(id).orElseThrow(() -> {
			return new SpellNotFoundException("Spell with ID '" + id + "' cannot be found");
		});
	}

	public Spell getByName(String name) {
		return spellRepository.findSpellByName(name).orElseThrow(() -> {
			return new SpellNotFoundException("Spell with name '" + name + "' cannot be found");
		});
	}
	
	public List<Spell> getByLevel(int level) {
		return spellRepository.findSpellByLevel(level);
	}

	public List<Spell> getBySchool(String school) {
		return spellRepository.findSpellBySchool(school);
	}

	public Spell create(Spell spell) {
		Spell savedUser = spellRepository.save(spell);
		return savedUser;
	}
	
	public Spell updateByName(String name, Spell spell) {
		Spell updatedSpell = spellService.getByName(name);
		Long id = updatedSpell.getId();
		
		if (spellRepository.existsById(id)) {
			Spell spellInDb = updatedSpell;
			spellInDb.setName(spell.getName());
			spellInDb.setLevel(spell.getLevel());
			spellInDb.setSchool(spell.getSchool());
			return spellRepository.save(spellInDb);
		} else {
			throw new SpellNotFoundException("Spell with name '" + name + "' cannot be found");
		}
	}
	
	public Spell updateById(Long id, Spell spell) {
		if (spellRepository.existsById(id)) {
			Spell spellInDb = spellService.getById(id);
			spellInDb.setLevel(spell.getLevel());
			spellInDb.setSchool(spell.getSchool());
			return spellRepository.save(spellInDb);
		} else {
			throw new SpellNotFoundException("Spell with ID '" + id + "' cannot be found");
		}
	}
	
	public void deleteByName(String name) {
		Spell updatedSpell = spellService.getByName(name);
		Long id = updatedSpell.getId();
		
		if (spellRepository.existsById(id)) {
			spellRepository.deleteById(id);
		} else {
			throw new SpellNotFoundException("Spell with name '" + name + "' cannot be found");
		}
	}
	
	public void deleteById(Long id) {
		if (spellRepository.existsById(id)) {
			spellRepository.deleteById(id);
		} else {
			throw new SpellNotFoundException("Spell with ID '" + id + "' cannot be found");
		}
	}

}
