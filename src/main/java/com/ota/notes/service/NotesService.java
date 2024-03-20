package com.ota.notes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.ota.notes.api.model.Note;
import com.ota.notes.dao.NotesDAO;

@Service
public class NotesService {

	@Autowired
	NotesDAO notesDao;

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

	@Caching(evict = { @CacheEvict(value = "noteList", allEntries = true), @CacheEvict(value = "note", key = "#id") })
	public Optional<Note> updateNote(int id, String content) {
		return notesDao.updateNote(id, content);
	}

	@CacheEvict(value = "noteList", allEntries = true)
	public Optional<Note> deleteNote(int id) {
		return notesDao.deleteNote(id);
	}

}
