package edu.cnm.deepdive.notes.controller;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import dagger.hilt.android.AndroidEntryPoint;
import edu.cnm.deepdive.notes.R;
import edu.cnm.deepdive.notes.databinding.FragmentEditBinding;
import edu.cnm.deepdive.notes.databinding.FragmentHomeBinding;
import edu.cnm.deepdive.notes.model.NoteViewModel;
import edu.cnm.deepdive.notes.model.entity.Note;


//This EditFragment is a bottom sheet dialog that allows users to view & edit a note in a
// note-taking app. It connects to a NoteViewModel, listens for updates to a Note, and
// updates the UI dynamically.

@AndroidEntryPoint
public class EditFragment extends BottomSheetDialogFragment {

  private static final String TAG = EditFragment.class.getSimpleName();

  private FragmentEditBinding binding;
  private NoteViewModel viewModel;
  private long noteId;
  
//  @ColorInt
//  private int cancelColor;
//  @ColorInt
//  private int saveColor;
// DONE cancelColor = getThemeColor(R.attr.colorCancel);
//    saveColor= getThemeColor(R.attr.colorSave);

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    noteId = EditFragmentArgs.fromBundle(getArguments()).getNoteId();
 // TODO: 2/18/25 Read any input arguments.
  }

  //THIS IS WHERE MOST OF THE WORK OCCURS
  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    // TODO: 2/18/25 Inflate layout and construct & return dialog containing layout.
    return super.onCreateDialog(savedInstanceState);
  }
  // this code sets up the layout for a fragment by inflating a layout resource using data
  // binding and returning the root view of the inflated layout. The layout will be used
  // to display the fragment's user interface.
  //This is being invoked by inherited activity by the lifecycles of the fragments being hosted by the activity
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = FragmentEditBinding.inflate(inflater, container,  false);
    return binding.getRoot();
    // DONE: 2/18/25 Return root element of layout.
    // TODO: 2025-02-18 Attach listeners to UI widgets.
  }

  //This fragment listens for changes in the NoteViewModel.
  //When a Note is updated, it dynamically updates the UI.
  //It ensures that UI elements only display when necessary (e.g., hiding the image if none exists).
  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(requireActivity()).get(NoteViewModel.class);
    if (noteId !=0) {
      viewModel.fetch(noteId);
      viewModel
          .getNote()
          .observe(getViewLifecycleOwner(), this::handleNote);
    } else {
      // TODO: 2025-02-18 Configure UI for a new note, vs. editing an existing note.
      // DONE: 2/18/25 Connect to viewmodel(s) and observe LiveData.
      binding.image.setVisibility(View.GONE);
    }
  }

  @Override
  public void onDestroyView() {
    binding = null;
    // TODO: 2/18/25 Set binding references to null.
    super.onDestroyView();
  }

  private void handleNote(Note note) {
    binding.title.setText(note.getTitle());
    binding.content.setText(note.getContent());
    Uri imageURI = note.getImage();
    if (imageURI != null) {
      binding.image.setImageURI(imageURI);
      binding.image.setVisibility(View.VISIBLE);
    } else {
      binding.image.setVisibility(View.GONE);
    }
  }

  @ColorInt
  private int getThemeColor(int colorAttr) {
    TypedValue typedValue = new TypedValue(); //container to read an attribute
    requireContext().getTheme().resolveAttribute(colorAttr, typedValue, true); //this is how we look up the value of an attribute.
    return typedValue.data;
  }
}
