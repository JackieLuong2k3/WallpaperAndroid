package com.example.mock2.Fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.mock2.R;

public class AmbienceFragment extends Fragment {
    private Button datePickerButton;
    private TextView tvShowNumbers;
    private NumberPicker numberPickerHour;
    private NumberPicker numberPickerMinute;
    private NumberPicker numberPickerSecond;

    public AmbienceFragment() {
        // Required empty public constructor
    }

//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.pickertime, container, false);
//
//        // Find views
//        tvShowNumbers = view.findViewById(R.id.tvShowNumbers);
//        numberPickerHour = view.findViewById(R.id.numberPickerHour);
//        numberPickerMinute = view.findViewById(R.id.numberPickerMinute);
//        numberPickerSecond = view.findViewById(R.id.numberPickerSecond);
//
//        // Set up Hour NumberPicker
//        numberPickerHour.setMinValue(0);
//        numberPickerHour.setMaxValue(23);
//        numberPickerHour.setFormatter(value -> String.format("%02d", value));
//
//        // Set up Minute NumberPicker
//        numberPickerMinute.setMinValue(0);
//        numberPickerMinute.setMaxValue(59);
//        numberPickerMinute.setFormatter(value -> String.format("%02d", value));
//
//        // Set up Second NumberPicker
//        numberPickerSecond.setMinValue(0);
//        numberPickerSecond.setMaxValue(59);
//        numberPickerSecond.setFormatter(value -> String.format("%02d", value));
//
//        // Set up value change listeners
//        numberPickerHour.setOnValueChangedListener((picker, oldVal, newVal) -> updateDisplayTime());
//        numberPickerMinute.setOnValueChangedListener((picker, oldVal, newVal) -> updateDisplayTime());
//        numberPickerSecond.setOnValueChangedListener((picker, oldVal, newVal) -> updateDisplayTime());
//
//        // Button to trigger modal dialog
//
//        btnMoon.setOnClickListener(v -> showAddProductDialog());
//
//        return view;
//    }
//
//    // Method to display the modal dialog
//    private void showAddProductDialog() {
//        // Implement your dialog logic here
//        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
//        // Customize your dialog
//        builder.setTitle("Add Product")
//                .setMessage("Dialog content goes here")
//                .setPositiveButton("OK", (dialog, which) -> {
//                    // Handle OK button click
//                })
//                .setNegativeButton("Cancel", (dialog, which) -> {
//                    // Handle Cancel button click
//                })
//                .create()
//                .show();
//    }
//
//    private void updateDisplayTime() {
//        int hour = numberPickerHour.getValue();
//        int minute = numberPickerMinute.getValue();
//        int second = numberPickerSecond.getValue();
//
//        String timeDisplay = String.format("Time: %02d:%02d:%02d", hour, minute, second);
//        tvShowNumbers.setText(timeDisplay);
//    }
}