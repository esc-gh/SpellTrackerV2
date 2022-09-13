package com.bae.exceptions;

import javax.persistence.EntityNotFoundException;

public class SpellNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1;

	public SpellNotFoundException() {
		super();
	}

}
