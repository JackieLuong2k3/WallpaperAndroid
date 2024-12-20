package com.example.mock2.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mock2.R;

public class SettingFragment extends Fragment implements TimePickerBottomSheetFragment.TimeSelectedListener {
    private TextView countdownTextView;
    private CountdownTimerManager timerManager;
    private Button btnTest;

    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        // Initialize countdown views and timer
        countdownTextView = view.findViewById(R.id.countdown);
        btnTest = view.findViewById(R.id.btnTest);
        initializeTimer();

        // Find and set up all the layout buttons
        LinearLayout rateUs = view.findViewById(R.id.layoutRateUs);
        LinearLayout feedback = view.findViewById(R.id.layoutFeedback);
        LinearLayout shareApp = view.findViewById(R.id.layoutShareApp);
        LinearLayout privacyPolicy = view.findViewById(R.id.layoutPrivacyPolicy);

        // Set up click listeners for all features
        rateUs.setOnClickListener(v -> openPlayStore());
        feedback.setOnClickListener(v -> sendFeedbackEmail());
        shareApp.setOnClickListener(v -> shareAppLink());
        privacyPolicy.setOnClickListener(v -> openPrivacyPolicy());
        btnTest.setOnClickListener(v -> {
            if (!timerManager.isTimerRunning()) {
                showPickertime();
            } else {
                timerManager.cancelTimer();
                btnTest.setText("Start Timer");
            }
        });

        return view;
    }

    private void initializeTimer() {
        timerManager = new CountdownTimerManager(countdownTextView);
        timerManager.setTimerStateListener(new CountdownTimerManager.TimerStateListener() {
            @Override
            public void onTimerFinish() {
                if (getActivity() != null) {
                    getActivity().runOnUiThread(() -> {
                        btnTest.setText("Start Timer");
                        Toast.makeText(requireContext(), "Timer finished!", Toast.LENGTH_SHORT).show();
                    });
                }
            }

            @Override
            public void onTimerTick(long millisUntilFinished) {
                // Optional: Add any additional tick handling here
            }
        });
    }

    private void showPickertime() {
        TimePickerBottomSheetFragment bottomSheetFragment = TimePickerBottomSheetFragment.newInstance();
        bottomSheetFragment.setTimeSelectedListener(this);
        bottomSheetFragment.show(getChildFragmentManager(), bottomSheetFragment.getTag());
    }

    @Override
    public void onTimeSelected(int hour, int minute, int second) {
        timerManager.startTimer(hour, minute, second);
        btnTest.setText("Stop Timer");
        Toast.makeText(requireContext(),
                String.format("Timer started: %02d:%02d:%02d", hour, minute, second),
                Toast.LENGTH_SHORT).show();
    }

    // Existing methods for other features
    private void openPlayStore() {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + requireContext().getPackageName())));
        } catch (android.content.ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + requireContext().getPackageName())));
        }
    }

    private void sendFeedbackEmail() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:developer@example.com")); // Replace with your email
        intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback for App");
        try {
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getContext(), "No email app found", Toast.LENGTH_SHORT).show();
        }
    }

    private void shareAppLink() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        String shareMessage = "Check out this app: https://play.google.com/store/apps/details?id=" + requireContext().getPackageName();
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }

    private void openPrivacyPolicy() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.example.com/privacy-policy")); // Replace with your privacy policy URL
        startActivity(browserIntent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (timerManager != null) {
            timerManager.cancelTimer();
        }
    }
}