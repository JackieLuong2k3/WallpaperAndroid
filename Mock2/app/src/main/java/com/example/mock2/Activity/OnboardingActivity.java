package com.example.mock2.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mock2.Adapter.OnboardingAdapter;
import com.example.mock2.R;

public class OnboardingActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        viewPager = findViewById(R.id.viewPager);
        btnNext = findViewById(R.id.btnNext);

        viewPager.setAdapter(new OnboardingAdapter());
        viewPager.setUserInputEnabled(false);

        btnNext.setOnClickListener(v -> {
            int currentItem = viewPager.getCurrentItem();
            if (currentItem < 2) {
                viewPager.setCurrentItem(currentItem + 1);
            } else {
                startActivity(new Intent(OnboardingActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}



