package com.example.mock2.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mock2.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class TimePickerBottomSheetFragment extends BottomSheetDialogFragment {
    private NumberPicker numberPickerHour;
    private NumberPicker numberPickerMinute;
    private NumberPicker numberPickerSecond;
    private TextView tvShowNumbers;

    // Interface to handle time selection
    public interface TimeSelectedListener {
        void onTimeSelected(int hour, int minute, int second);
    }

    private TimeSelectedListener listener;

    public static TimePickerBottomSheetFragment newInstance() {
        return new TimePickerBottomSheetFragment();
    }

    public void setTimeSelectedListener(TimeSelectedListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pickertime, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Find views
        numberPickerHour = view.findViewById(R.id.numberPickerHour);
        numberPickerMinute = view.findViewById(R.id.numberPickerMinute);
        numberPickerSecond = view.findViewById(R.id.numberPickerSecond);
        tvShowNumbers = view.findViewById(R.id.tvShowNumbers);

        // Set up Hour NumberPicker
        numberPickerHour.setMinValue(0);
        numberPickerHour.setMaxValue(23);
        numberPickerHour.setFormatter(value -> String.format("%02d", value));

        // Set up Minute NumberPicker
        numberPickerMinute.setMinValue(0);
        numberPickerMinute.setMaxValue(59);
        numberPickerMinute.setFormatter(value -> String.format("%02d", value));

        // Set up Second NumberPicker
        numberPickerSecond.setMinValue(0);
        numberPickerSecond.setMaxValue(59);
        numberPickerSecond.setFormatter(value -> String.format("%02d", value));

        // Set up value change listeners
        NumberPicker.OnValueChangeListener valueChangeListener = (picker, oldVal, newVal) ->
                updateDisplayTime();

        numberPickerHour.setOnValueChangedListener(valueChangeListener);
        numberPickerMinute.setOnValueChangedListener(valueChangeListener);
        numberPickerSecond.setOnValueChangedListener(valueChangeListener);

        // Initial display update
        updateDisplayTime();

        // OK button
        Button btnOk = view.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(v -> {
            if (listener != null) {
                int hour = numberPickerHour.getValue();
                int minute = numberPickerMinute.getValue();
                int second = numberPickerSecond.getValue();
                listener.onTimeSelected(hour, minute, second);
                dismiss();
            }
        });

        // Cancel button
        Button btnCancel = view.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(v -> dismiss());
    }

    private void updateDisplayTime() {
        int hour = numberPickerHour.getValue();
        int minute = numberPickerMinute.getValue();
        int second = numberPickerSecond.getValue();

        String timeDisplay = String.format("Time: %02d:%02d:%02d", hour, minute, second);
        tvShowNumbers.setText(timeDisplay);
    }
}