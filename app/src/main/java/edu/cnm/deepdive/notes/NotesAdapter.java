package edu.cnm.deepdive.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import edu.cnm.deepdive.notes.databinding.ItemNoteBinding;
import edu.cnm.deepdive.notes.model.entity.Note;
import java.util.List;

public class NotesAdapter extends Adapter<ViewHolder> {

  private final LayoutInflater inflater;
  private final List<Note> notes;

  public NotesAdapter(Context context, List<Note> notes) {
    this.notes = notes;
    inflater = LayoutInflater.from(context);
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
    ItemNoteBinding binding = ItemNoteBinding.inflate(inflater, viewGroup, false);
//WHEN THIS method gets invoked the viewGroup is passed, inflater checks viewGroup for themes
    return new Holder(binding); //and pass that binding we just attached to it // DONE: 2025-02-13 Create and return an instance of Holder.
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    ((Holder) holder).bind(notes.get(position));
    // TODO: 2025-02-13 Invoke holder.bind with the object in position i.
    //cast holder to use as an instance from Holder class in another class by placing it in parentheses
  }

  @Override
  public int getItemCount() {
    return notes.size(); // DONE: 2025-02-13 Return the number of Note instances that can be displayed in this view.
  }

  private static class Holder extends ViewHolder {

    private ItemNoteBinding binding;

    public Holder(@NonNull ItemNoteBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
      // TODO: 2025-02-13 Initialize any fields.
    }

    public void bind(Note note) {
      binding.title.setText(note.getTitle());
      binding.modifiedOn.setText(note.getModifiedOn().toString());
      // TODO: 2025-02-13 Use data from item to populate widgets in itemView.
      //purpose of binding process is to bind model objects to widgets
    }

  }

}
