package secure.notes.service;

import secure.notes.model.Note;

import java.util.List;

public interface NoteService {
    //CRUD
    Note createNoteForUser(String username, String content);
    Note updateNoteForUser(Long noteId,String username, String content);
    void deleteNoteForUser(Long noteId, String username);
    List<Note> getNotesForUser(String username);
}
