package com.example.mock2.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mock2.R;

public class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder> {

    @NonNull
    @Override
    public OnboardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new OnboardingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.screen_one, parent, false));
        } else if (viewType == 1) {
            return new OnboardingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.screen_two, parent, false));
        } else {
            return new OnboardingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.screen_three, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class OnboardingViewHolder extends RecyclerView.ViewHolder {
        public OnboardingViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
