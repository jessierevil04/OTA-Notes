package com.ota.notes.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ota.notes.api.model.Note;
import com.ota.notes.dao.NotesDAO;

@Service
public class NotesService {

	@Autowired
	NotesDAO notesDao;

	@Cacheable(value = "noteValidation")
	public void validateNoteUpdate(String title, String body) {

		if (StringUtils.isAllBlank(title, body))
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Note Title or Note Body is mandatory.");

	}

	@Cacheable(value = "noteList")
	public List<Note> getNoteList() {
		return notesDao.getNoteList();
	}

	@Cacheable(value = "note", key = "#id")
	public Optional<Note> getNote(int id) {
		return notesDao.getNote(id);
	}

	@Caching(evict = { @CacheEvict(value = "noteList", allEntries = true),
			@CacheEvict(value = "note", allEntries = true) })
	public Note addNote(Note note) {
		return notesDao.addNote(note);
	}

	@Caching(evict = { @CacheEvict(value = "noteList", allEntries = true),
			@CacheEvict(value = "note", key = "#pNote.id") })
	public Optional<Note> updateNote(Note pNote) {
		return notesDao.updateNote(pNote);
	}

	@Caching(evict = { @CacheEvict(value = "noteList", allEntries = true), @CacheEvict(value = "note", key = "#id") })
	public Optional<Note> deleteNote(int id) {
		return notesDao.deleteNote(id);
	}

}
