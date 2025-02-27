package edu.cnm.deepdive.notes.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.notes.databinding.FragmentLoginBinding;
import edu.cnm.deepdive.notes.viewmodel.LoginViewModel;

public class LoginFragment extends Fragment {

  private FragmentLoginBinding binding;
  private LoginViewModel viewModel;
  private ActivityResultLauncher<Intent> launcher;

  //attaching a listener to our button
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding= FragmentLoginBinding.inflate(inflater, container, false);
    binding.signIn.setOnClickListener((v) -> viewModel.startSignIn(launcher));
    return binding.getRoot();
  }
  
//this looks at account live data to see if it succeeded, 
  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    
    viewModel = new ViewModelProvider(requireActivity())
        .get(LoginViewModel.class);
    LifecycleOwner owner = getViewLifecycleOwner();
    viewModel
        .getAccount()
        .observe(owner, (account) -> {
          if (account != null) {
            // TODO: 2/27/25 Navigate to HomeFragment. 
          }
        });
    viewModel
        .getSignInThrowable()
        .observe(owner, (throwable -> {
          if (throwable != null) {
            // TODO: 2/27/25 Show SnackBar with signin failure message.
          }
        }));
    launcher = registerForActivityResult(new StartActivityForResult(),
        (result) -> viewModel.completeSignIn(result));
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
  }

}
