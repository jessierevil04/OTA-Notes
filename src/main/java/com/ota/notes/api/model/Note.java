package com.ota.notes.api.model;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

public class Note implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(name = "Note ID", example = "0")
	private int id;

	@Schema(name = "Note Content", example = "Sample Note Content")
	private String content;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
