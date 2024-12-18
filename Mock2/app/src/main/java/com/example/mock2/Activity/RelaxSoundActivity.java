package com.example.mock2.Activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mock2.Model.RelaxSoundModel;
import com.example.mock2.R;

public class RelaxSoundActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private TextView soundNameTextView, startTimeTextView, endTimeTextView;
    private ImageView backBtn, pauseBtn, editBtn, selecteBtn;
    private SeekBar seekBar;

    private Handler handler = new Handler();
    private int duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_player);

        soundNameTextView = findViewById(R.id.textView2);
        startTimeTextView = findViewById(R.id.start_time);
        endTimeTextView = findViewById(R.id.end_time);
        seekBar = findViewById(R.id.seekBar);

        backBtn = findViewById(R.id.back_btn);
        pauseBtn = findViewById(R.id.pause_btn);
        editBtn = findViewById(R.id.edit_btn);
        selecteBtn = findViewById(R.id.selecte_btn);

        // Lấy thông tin từ Intent
        RelaxSoundModel relaxSound = (RelaxSoundModel) getIntent().getSerializableExtra("sound");

        if (relaxSound != null) {
            soundNameTextView.setText(relaxSound.getName());


            int soundResource = getResources().getIdentifier(relaxSound.getName().toLowerCase(), "raw", getPackageName());
            mediaPlayer = MediaPlayer.create(this, soundResource);
            mediaPlayer.start();

            // Thiết lập độ dài của bài nhạc
            duration = mediaPlayer.getDuration();
            endTimeTextView.setText(formatTime(duration));

            // Cập nhật SeekBar
            seekBar.setMax(duration);
            handler.postDelayed(updateSeekBar, 1000);

            // Cập nhật thời gian và SeekBar
            pauseBtn.setOnClickListener(v -> {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    pauseBtn.setImageResource(R.drawable.play);
                } else {
                    mediaPlayer.start();
                    pauseBtn.setImageResource(R.drawable.pause);
                }
            });

            // Xử lý sự kiện quay lại Activity trước
            backBtn.setOnClickListener(v -> finish());
        }

        // Cập nhật thời gian phát
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                    startTimeTextView.setText(formatTime(progress));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }


    private Runnable updateSeekBar = new Runnable() {
        @Override
        public void run() {
            int currentPosition = mediaPlayer.getCurrentPosition();
            seekBar.setProgress(currentPosition);
            startTimeTextView.setText(formatTime(currentPosition));
            handler.postDelayed(this, 1000);
        }
    };

    private String formatTime(int timeInMilliseconds) {
        int minutes = (timeInMilliseconds / 1000) / 60;
        int seconds = (timeInMilliseconds / 1000) % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}
