package com.example.mock2.Activity;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;

import com.example.mock2.Fragment.AmbienceFragment;
import com.example.mock2.Fragment.MusicFragment;
import com.example.mock2.R;
import com.example.mock2.Fragment.RelaxSoundFragment;
import com.example.mock2.Fragment.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new RelaxSoundFragment())
                .commit();
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            int itemId = item.getItemId();
            if (itemId == R.id.nav_movie) {
                selectedFragment = new RelaxSoundFragment();

            } else if (itemId == R.id.nav_favourite) {
                selectedFragment = new AmbienceFragment();

            } else if (itemId == R.id.nav_setting) {
                selectedFragment = new MusicFragment();

            } else if (itemId == R.id.nav_about) {
                selectedFragment = new SettingFragment();

            }
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }
            return true;
        });
    }
}