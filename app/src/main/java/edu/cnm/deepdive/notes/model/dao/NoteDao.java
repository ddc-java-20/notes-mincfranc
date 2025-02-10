package edu.cnm.deepdive.notes.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import edu.cnm.deepdive.notes.model.entity.Note;
import io.reactivex.rxjava3.core.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface NoteDao {

  @Insert
  Single<Long> insert(Note note);

  //we are creating this instead of .room so it does not need an @Insert annotation
  //we take the Note that was created upstream, then use the machinery to pass the note downstream with another
  // processing station hooked on to the id
  default Single<Note> insertAndReturn(Note note) {
    return insert(note)
        .map((id) -> {
          note.setId(id);
          return note;
        });
  }

  @Insert
  Single<List<Long>> insert(Collection<Note> notes);

  @Insert
  Single<List<Long>> insert(Note... notes);

}
