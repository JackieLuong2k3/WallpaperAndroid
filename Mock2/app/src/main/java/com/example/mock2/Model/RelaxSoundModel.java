package com.example.mock2.Model;

public class RelaxSoundModel {
    private String name;
    private int imageResId;

    public RelaxSoundModel(String name, int imageResId) {
        this.name = name;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }
}

