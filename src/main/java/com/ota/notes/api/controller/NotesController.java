package com.ota.notes.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ota.notes.api.model.Note;
import com.ota.notes.service.NotesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController("/notes")
@Tag(name = "Notes API")
public class NotesController {

	@Autowired
	NotesService noteService;

	@Operation(summary = "Creates a New Note", description = "Returns the newly created note")
	@ApiResponse(responseCode = "200", description = "Successfully Created a Note")
	@PostMapping
	public Note createNote(
			@RequestParam @Parameter(name = "content", description = "Note Content", example = "Sample Note Content") String content) {

		Note note = new Note();
		note.setContent(content);

		return noteService.addNote(note);
	}

	@Operation(summary = "Retrieves all the stored Notes", description = "Returns the list of stored Notes")
	@ApiResponse(responseCode = "200", description = "Successfully retrieves the list of Notes")
	@GetMapping
	public List<Note> getNotes() {
		return noteService.getNoteList();
	}

	@Operation(summary = "Retrieves a specific Note given a Note ID", description = "Returns the a Note given a Note ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfuly found a Note with the given a Note ID"),
			@ApiResponse(responseCode = "404", description = "Note not found") })
	@GetMapping("/{id}")
	public Note getNote(@PathVariable("id") @Parameter(name = "id", description = "Note ID", example = "0") int id) {
		Optional<Note> optionalNote = noteService.getNote(id);

		return optionalNote.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not Found"));
	}

	@Operation(summary = "Update the content of a specific Note given a Note ID", description = "Returns the updated Note")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfuly updated a Note with the given a Note ID"),
			@ApiResponse(responseCode = "404", description = "Note not found") })
	@PutMapping("/{id}")
	public Note updateNote(@PathVariable("id") @Parameter(name = "id", description = "Note ID", example = "0") int id,
			@RequestParam @Parameter(name = "content", description = "Note Content", example = "Sample Note Content") String content) {
		Optional<Note> optionalNote = noteService.updateNote(id, content);

		return optionalNote.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not Found"));
	}

	@Operation(summary = "Deletes a specific Note given a Note ID", description = "No object is being returned")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfuly deleted a Note with the given a Note ID"),
			@ApiResponse(responseCode = "404", description = "Note not found") })
	@DeleteMapping("/{id}")
	public void deleteNote(@PathVariable("id") @Parameter(name = "id", description = "Note ID", example = "0") int id) {
		Optional<Note> optionalNote = noteService.deleteNote(id);

		optionalNote.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not Found"));
	}

}
