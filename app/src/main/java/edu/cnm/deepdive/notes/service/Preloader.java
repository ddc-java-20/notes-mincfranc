package edu.cnm.deepdive.notes.service;

import androidx.annotation.NonNull;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import edu.cnm.deepdive.notes.model.dao.NoteDao;
import edu.cnm.deepdive.notes.model.entity.Note;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;

public class Preloader extends RoomDatabase.Callback {

  private final Provider<NoteDao> noteDaoProvider;

  @Inject
  Preloader(Provider<NoteDao> noteDaoProvider) { //our parameters are our dependencies
    this.noteDaoProvider = noteDaoProvider;
  }

  @Override
  public void onCreate(@NonNull SupportSQLiteDatabase db) { //the moment this is instantiated, regardless
    // of source of activation, the Preloader is activated to deliver noteDao WHEN and if it is needed
    super.onCreate(db);

    NoteDao noteDao = noteDaoProvider.get(); //Provider is a promise to deliver something later, at
    // the moment we create the preloader we don't need the noteDao, only a promise

    Note note1 = new Note();
    note1.setTitle("Test Note 1");
    note1.setContent("Blah blah blah");

    Note note2 = new Note();
    note2.setTitle("Test Note 2");
    note2.setContent("This is another note");

    noteDao
        .insert(note1, note2)
        .subscribeOn(Schedulers.io())
        .subscribe();  //now we can create an instance of this; when the physical database is created // we will insert 2 notes
  }
}
