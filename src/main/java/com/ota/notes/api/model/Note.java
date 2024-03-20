package com.ota.notes.api.model;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

public class Note implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(name = "id", description = "Note ID", example = "0")
	private Integer id;

	@Schema(name = "title", description = "Note Title", example = "Sample Note Title")
	private String title;

	@Schema(name = "body", description = "Note Body", example = "Sample Note Body")
	private String body;

	public Note(String title, String body) {
		this(null, title, body);
	}

	public Note(Integer id, String title, String body) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
