package secure.notes.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import secure.notes.model.Note;
import secure.notes.repository.NoteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class NoteServiceImpl implements NoteService{
    private NoteRepository noteRepository;
    @Override
    public Note createNoteForUser(String username, String content) {
        Note note = new Note();
        note.setOwnerUsername(username);
        System.out.println("content is: " + content);
        note.setContent(content);
        return noteRepository.save(note);
    }

    @Override
    public Note updateNoteForUser(Long noteId, String username, String content) {
        Optional<Note> noteToUpdate = noteRepository.findById(noteId);
        if(noteToUpdate.isPresent()){
            noteToUpdate.get().setOwnerUsername(username);
            noteToUpdate.get().setContent(content);
            noteRepository.saveAndFlush(noteToUpdate.get());
            return noteToUpdate.get();
        }
        return null;
    }

    @Override
    public void deleteNoteForUser(Long noteId, String username) {
        Optional<Note> noteToDelete = noteRepository.findById(noteId);
        noteToDelete.ifPresent(note -> noteRepository.delete(note));
    }

    @Override
    public List<Note> getNotesForUser(String username) {
        try{
            return noteRepository.findByOwnerUsername(username);
        }
        catch(RuntimeException exception){
            log.error("e: ", exception);
            return new ArrayList<>();
        }
    }
}
