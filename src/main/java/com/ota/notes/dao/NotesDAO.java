package com.ota.notes.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.ota.notes.api.model.Note;

@Repository
public class NotesDAO {

	private AtomicInteger idCounter = new AtomicInteger();
	private List<Note> noteList = new ArrayList<>();

	public List<Note> getNoteList() {
		return noteList;
	}

	public Optional<Note> getNote(int id) {
		return noteList.stream().filter(note -> note.getId() == id).findAny();
	}

	public synchronized Note addNote(Note note) {
		note.setId(idCounter.getAndIncrement());

		noteList.add(note);

		return note;
	}

	public synchronized Optional<Note> updateNote(Note pNote) {
		Optional<Note> optionalNote = getNote(pNote.getId());

		optionalNote.ifPresent(note -> {

			if (StringUtils.isNotBlank(pNote.getTitle()))
				note.setTitle(pNote.getTitle());

			if (StringUtils.isNotBlank(pNote.getBody()))
				note.setBody(pNote.getBody());
			
		});

		return optionalNote;
	}

	public synchronized Optional<Note> deleteNote(int id) {
		Optional<Note> optionalNote = getNote(id);

		optionalNote.ifPresent(note -> noteList.remove(note));

		return optionalNote;
	}

}
