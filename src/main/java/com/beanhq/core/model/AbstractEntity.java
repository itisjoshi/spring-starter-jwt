package com.beanhq.core.model;

/**
 * Created by Prathap Manohar Joshi
 */
public abstract class AbstractEntity {

    private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}