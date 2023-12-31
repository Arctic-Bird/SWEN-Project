// Generated by view binder compiler. Do not edit!
package com.example.tufoodtrucksloginscreen.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.tufoodtrucksloginscreen.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentLoginBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ConstraintLayout container;

  @NonNull
  public final Button login;

  @NonNull
  public final EditText password;

  @NonNull
  public final TextView swipeLeft;

  @NonNull
  public final ProgressBar swipeRight;

  @NonNull
  public final EditText username;

  private FragmentLoginBinding(@NonNull ConstraintLayout rootView,
      @NonNull ConstraintLayout container, @NonNull Button login, @NonNull EditText password,
      @NonNull TextView swipeLeft, @NonNull ProgressBar swipeRight, @NonNull EditText username) {
    this.rootView = rootView;
    this.container = container;
    this.login = login;
    this.password = password;
    this.swipeLeft = swipeLeft;
    this.swipeRight = swipeRight;
    this.username = username;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_login, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentLoginBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      ConstraintLayout container = (ConstraintLayout) rootView;

      id = R.id.login;
      Button login = ViewBindings.findChildViewById(rootView, id);
      if (login == null) {
        break missingId;
      }

      id = R.id.password;
      EditText password = ViewBindings.findChildViewById(rootView, id);
      if (password == null) {
        break missingId;
      }

      id = R.id.swipeLeft;
      TextView swipeLeft = ViewBindings.findChildViewById(rootView, id);
      if (swipeLeft == null) {
        break missingId;
      }

      id = R.id.swipeRight;
      ProgressBar swipeRight = ViewBindings.findChildViewById(rootView, id);
      if (swipeRight == null) {
        break missingId;
      }

      id = R.id.username;
      EditText username = ViewBindings.findChildViewById(rootView, id);
      if (username == null) {
        break missingId;
      }

      return new FragmentLoginBinding((ConstraintLayout) rootView, container, login, password,
          swipeLeft, swipeRight, username);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
