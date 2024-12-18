package com.example.mock2.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.mock2.R;

public class SettingFragment extends Fragment {

    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        // Find the buttons by their IDs
        Button btnTest = view.findViewById(R.id.btnTest);

        // Set up the button click listeners
        btnTest.setOnClickListener(v -> showPickertime());
        return view;
    }

    private void showPickertime() {
        TimePickerBottomSheetFragment bottomSheetFragment = TimePickerBottomSheetFragment.newInstance();
        bottomSheetFragment.setTimeSelectedListener((hour, minute, second) -> {
            // Handle the selected time
            String selectedTime = String.format("%02d:%02d:%02d", hour, minute, second);
            Toast.makeText(requireContext(), "Selected Time: " + selectedTime, Toast.LENGTH_SHORT).show();
        });
        bottomSheetFragment.show(getChildFragmentManager(), bottomSheetFragment.getTag());
    }
}