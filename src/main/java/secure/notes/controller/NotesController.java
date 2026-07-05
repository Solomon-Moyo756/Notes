package secure.notes.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import secure.notes.model.Note;
import secure.notes.service.NoteServiceImpl;

import java.util.List;

@RestController
@RequestMapping("notes/api/")
public class NotesController {


    private final NoteServiceImpl noteService;

    public NotesController(NoteServiceImpl noteService) {
        this.noteService = noteService;
    }

    @PostMapping("create")
    public ResponseEntity<Note> createNote(@RequestBody String content, @AuthenticationPrincipal UserDetails userDetails){
        Note note = noteService.createNoteForUser(userDetails.getUsername(),content);
        return ResponseEntity.ok(note);
    }
    @PutMapping("update/{noteId}")
    public ResponseEntity<Note> updateNote(@PathVariable long noteId, @RequestBody String content, @AuthenticationPrincipal UserDetails userDetails){
        Note note = noteService.updateNoteForUser(noteId, userDetails.getUsername(),content);
        return ResponseEntity.ok(note);
    }
    @DeleteMapping("delete/{noteId}")
    public void deleteNote(@PathVariable long noteId, @AuthenticationPrincipal UserDetails userDetails){
        noteService.deleteNoteForUser(noteId,userDetails.getUsername());
    }
    @GetMapping("notes")
    public ResponseEntity<List<Note>> getNotesByUsername(@AuthenticationPrincipal UserDetails userDetails){
        List<Note> notes = noteService.getNotesForUser(userDetails.getUsername());
        return ResponseEntity.ok(notes);
    }
}
