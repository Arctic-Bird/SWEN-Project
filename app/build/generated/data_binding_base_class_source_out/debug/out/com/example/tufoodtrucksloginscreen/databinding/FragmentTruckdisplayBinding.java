// Generated by view binder compiler. Do not edit!
package com.example.tufoodtrucksloginscreen.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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

public final class FragmentTruckdisplayBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView TruckImage;

  @NonNull
  public final TextView TruckName;

  @NonNull
  public final TextView locationName;

  @NonNull
  public final Button menuViewButton;

  @NonNull
  public final TextView ratingScore;

  @NonNull
  public final TextView scheduleFormat;

  @NonNull
  public final TextView textView11;

  @NonNull
  public final TextView textView12;

  @NonNull
  public final TextView textView13;

  @NonNull
  public final TextView textView15;

  @NonNull
  public final TextView textView16;

  @NonNull
  public final TextView textView7;

  @NonNull
  public final TextView textView9;

  @NonNull
  public final TextView websiteLink;

  private FragmentTruckdisplayBinding(@NonNull ConstraintLayout rootView,
      @NonNull ImageView TruckImage, @NonNull TextView TruckName, @NonNull TextView locationName,
      @NonNull Button menuViewButton, @NonNull TextView ratingScore,
      @NonNull TextView scheduleFormat, @NonNull TextView textView11, @NonNull TextView textView12,
      @NonNull TextView textView13, @NonNull TextView textView15, @NonNull TextView textView16,
      @NonNull TextView textView7, @NonNull TextView textView9, @NonNull TextView websiteLink) {
    this.rootView = rootView;
    this.TruckImage = TruckImage;
    this.TruckName = TruckName;
    this.locationName = locationName;
    this.menuViewButton = menuViewButton;
    this.ratingScore = ratingScore;
    this.scheduleFormat = scheduleFormat;
    this.textView11 = textView11;
    this.textView12 = textView12;
    this.textView13 = textView13;
    this.textView15 = textView15;
    this.textView16 = textView16;
    this.textView7 = textView7;
    this.textView9 = textView9;
    this.websiteLink = websiteLink;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentTruckdisplayBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentTruckdisplayBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_truckdisplay, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentTruckdisplayBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.TruckImage;
      ImageView TruckImage = ViewBindings.findChildViewById(rootView, id);
      if (TruckImage == null) {
        break missingId;
      }

      id = R.id.TruckName;
      TextView TruckName = ViewBindings.findChildViewById(rootView, id);
      if (TruckName == null) {
        break missingId;
      }

      id = R.id.locationName;
      TextView locationName = ViewBindings.findChildViewById(rootView, id);
      if (locationName == null) {
        break missingId;
      }

      id = R.id.menuViewButton;
      Button menuViewButton = ViewBindings.findChildViewById(rootView, id);
      if (menuViewButton == null) {
        break missingId;
      }

      id = R.id.ratingScore;
      TextView ratingScore = ViewBindings.findChildViewById(rootView, id);
      if (ratingScore == null) {
        break missingId;
      }

      id = R.id.scheduleFormat;
      TextView scheduleFormat = ViewBindings.findChildViewById(rootView, id);
      if (scheduleFormat == null) {
        break missingId;
      }

      id = R.id.textView11;
      TextView textView11 = ViewBindings.findChildViewById(rootView, id);
      if (textView11 == null) {
        break missingId;
      }

      id = R.id.textView12;
      TextView textView12 = ViewBindings.findChildViewById(rootView, id);
      if (textView12 == null) {
        break missingId;
      }

      id = R.id.textView13;
      TextView textView13 = ViewBindings.findChildViewById(rootView, id);
      if (textView13 == null) {
        break missingId;
      }

      id = R.id.textView15;
      TextView textView15 = ViewBindings.findChildViewById(rootView, id);
      if (textView15 == null) {
        break missingId;
      }

      id = R.id.textView16;
      TextView textView16 = ViewBindings.findChildViewById(rootView, id);
      if (textView16 == null) {
        break missingId;
      }

      id = R.id.textView7;
      TextView textView7 = ViewBindings.findChildViewById(rootView, id);
      if (textView7 == null) {
        break missingId;
      }

      id = R.id.textView9;
      TextView textView9 = ViewBindings.findChildViewById(rootView, id);
      if (textView9 == null) {
        break missingId;
      }

      id = R.id.websiteLink;
      TextView websiteLink = ViewBindings.findChildViewById(rootView, id);
      if (websiteLink == null) {
        break missingId;
      }

      return new FragmentTruckdisplayBinding((ConstraintLayout) rootView, TruckImage, TruckName,
          locationName, menuViewButton, ratingScore, scheduleFormat, textView11, textView12,
          textView13, textView15, textView16, textView7, textView9, websiteLink);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
