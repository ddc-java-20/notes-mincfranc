package edu.cnm.deepdive.notes.model.entity;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.time.Instant;

//Unique key constraints are created here ie indices, compound
//Here the note name must be unique from all other notes
@Entity(
    tableName = "note",
    indices = {
        @Index(value = {"title"}, unique = true)
    }
)
public class Note {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name="note_id")
  private long id;

  @NonNull
  private String title = "";
  //Code indicates title index is case insensitive and can be searched in multiple cases.
  @ColumnInfo(collate= ColumnInfo.NOCASE, index = true)


//we are setting default values for each field
//ie title & String will be empty upon opening new note
  @NonNull
  private String content= "";

  private Uri image;

  @ColumnInfo(name = "created_on", index = true)
  @NonNull
  private Instant createdOn= Instant.now();

  @ColumnInfo(name = "modified_on", index = true)
  @NonNull
  private Instant modifiedOn= Instant.now();

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @NonNull
  public String getTitle() {
    return title;
  }

  public void setTitle(@NonNull String title) {
    this.title = title;
  }

  @NonNull
  public String getContent() {
    return content;
  }

  public void setContent(@NonNull String content) {
    this.content = content;
  }

  public Uri getImage() {
    return image;
  }

  public void setImage(Uri image) {
    this.image = image;
  }

  @NonNull
  public Instant getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(@NonNull Instant createdOn) {
    this.createdOn = createdOn;
  }

  @NonNull
  public Instant getModifiedOn() {
    return modifiedOn;
  }

  public void setModifiedOn(@NonNull Instant modifiedOn) {
    this.modifiedOn = modifiedOn;
  }
}
