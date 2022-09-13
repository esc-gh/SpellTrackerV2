package com.bae.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bae.service.SpellService;
import com.bae.entities.Spell;

@RestController
@RequestMapping(path = "/spell")
public class SpellController {
	
	private SpellService spellService;
	
	@Autowired
	public SpellController(SpellService spellService) {
		this.spellService = spellService;
	}
	
	@GetMapping
	public ResponseEntity<List<Spell>> getSpells() {
		ResponseEntity<List<Spell>> spells = ResponseEntity.ok(spellService.getAll());
		return spells;
	}

	@GetMapping("/name")
	public ResponseEntity<List<Spell>> getSpellsByName() {
		ResponseEntity<List<Spell>> spells = ResponseEntity.ok(spellService.getAllByName());
		return spells;
	}

	@GetMapping("/lv")
	public ResponseEntity<List<Spell>> getSpellsByLevel() {
		ResponseEntity<List<Spell>> spells = ResponseEntity.ok(spellService.getAllByLevel());
		return spells;
	}

	@GetMapping("/school")
	public ResponseEntity<List<Spell>> getSpellsBySchool() {
		ResponseEntity<List<Spell>> spells = ResponseEntity.ok(spellService.getAllBySchool());
		return spells;
	}

	@GetMapping("/{name}")
	public ResponseEntity<Spell> getSpellByName(@PathVariable("name") String name) {
		Spell savedSpell = spellService.getByName(name);

		ResponseEntity<Spell> response = ResponseEntity.status(HttpStatus.OK).body(savedSpell);
		return response;
	}

	@GetMapping("/lv/{level}")
	public ResponseEntity<List<Spell>> getSpellByLevel(@PathVariable("level") int level) {
		List<Spell> savedSpell = spellService.getByLevel(level);

		ResponseEntity<List<Spell>> response = ResponseEntity.status(HttpStatus.OK).body(savedSpell);
		return response;
	}

	@GetMapping("/school/{school}")
	public ResponseEntity<List<Spell>> getSpellBySchool(@PathVariable("school") String school) {
		List<Spell> savedSpell = spellService.getBySchool(school);

		ResponseEntity<List<Spell>> response = ResponseEntity.status(HttpStatus.OK).body(savedSpell);
		return response;
	}

	@PostMapping
	public ResponseEntity<Spell> createSpell(@Valid @RequestBody Spell spell) {
		Spell savedSpell = spellService.create(spell);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/spell/" + String.valueOf(savedSpell.getName()));

		return new ResponseEntity<Spell>(savedSpell, headers, HttpStatus.CREATED);
	}

	@PutMapping("/{name}")
	public ResponseEntity<Spell> updateSpell(@PathVariable("name") String name, @Valid @RequestBody Spell spell) {
		Spell updatedSpell = spellService.updateByName(name, spell);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/spell/" + String.valueOf(updatedSpell.getName()));

		return new ResponseEntity<Spell>(updatedSpell, headers, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Spell> updateSpell(@PathVariable("id") Long id, @Valid @RequestBody Spell spell) {
		Spell updatedSpell = spellService.updateById(id, spell);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/spell/" + String.valueOf(updatedSpell.getName()));

		return new ResponseEntity<Spell>(updatedSpell, headers, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{name}")
	public ResponseEntity<?> deleteSpell(@PathVariable("name") String name) {
		spellService.deleteByName(name);
		return ResponseEntity.accepted().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteSpell(@PathVariable("id") Long id) {
		spellService.deleteById(id);
		return ResponseEntity.accepted().build();
	}

}
