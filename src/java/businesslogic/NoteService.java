package businesslogic;

import dataaccess.NotesDB;
import dataaccess.UserDB;
import domainmodel.Note;
import domainmodel.User;
import java.util.List;

import java.util.List;
import java.util.Date;

public class NoteService {

    private NotesDB notesDB;

    public NoteService() {
        notesDB = new NotesDB();
    }

    public Note get(int noteId) throws Exception {
        return notesDB.getId(noteId);
    }
    public List<Note> getNotes(String username) throws Exception
    {
        UserDB userDB = new UserDB();
        User user = userDB.getUser(username);
        return notesDB.getNotes(user);
    }

    public List<Note> getAll() throws Exception {
        return notesDB.getAll();
    }

    public int update(int noteId,String title,String comments) throws Exception {
        Note note = new Note(noteId,new Date(),title,comments);
        return notesDB.update(note);
    }

    public int delete(int noteId) throws Exception {
        Note note = notesDB.getId(noteId);
        
        return notesDB.delete(note);
    }

    public int insert(String title,String comments,User user) throws Exception {
        return notesDB.insert(new Note(0,new Date(),title,comments,user));
    }
}
