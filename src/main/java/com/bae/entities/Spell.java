package com.bae.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "spell")
public class Spell {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, unique=true)
	@Length(min = 1, message = "Names cannot be empty")
	private String name;
	
	@Column(nullable = false)
	@Max(9)
	@Min(0)
	private Integer level;
	
	@Column(nullable = false)
	@Length(min = 1, message = "School of magic cannot be empty")
	private String school;
	
	public Spell() {
		super();
	}

	public Spell(String name, Integer level, String school) {
		super();
		this.name = name;
		this.level = level;
		this.school = school;
	}

	public Spell(Long id, String name, Integer level, String school) {
		super();
		this.id = id;
		this.name = name;
		this.level = level;
		this.school = school;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}
	
	@Override
	public String toString() {
		return "ID: " + id + ", Spell: " + name + ", lv: " + level + ", School: " + school;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, level, name, school);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Spell other = (Spell) obj;
		return Objects.equals(id, other.id) && Objects.equals(level, other.level) && Objects.equals(name, other.name)
				&& Objects.equals(school, other.school);
	}

}
