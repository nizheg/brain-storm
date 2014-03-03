package me.nizheg.domain;

import java.io.Serializable;

public class Word implements Serializable {

	private final Long id;
	private final String value;

	public Word(Long id, String value) {
		this.id = id;
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}
}
