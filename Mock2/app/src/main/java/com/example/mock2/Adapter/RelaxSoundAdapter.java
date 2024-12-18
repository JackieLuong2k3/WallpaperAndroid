package com.example.mock2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mock2.Activity.RelaxSoundActivity;
import com.example.mock2.Model.RelaxSoundModel;
import com.example.mock2.R;

import java.util.List;

public class RelaxSoundAdapter extends RecyclerView.Adapter<RelaxSoundAdapter.ViewHolder> {

    private Context context;
    private List<RelaxSoundModel> soundList;

    public RelaxSoundAdapter(Context context, List<RelaxSoundModel> soundList) {
        this.context = context;
        this.soundList = soundList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewholder_relaxsounditem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RelaxSoundModel sound = soundList.get(position);
        holder.imageView.setImageResource(sound.getImageResId());
        holder.textView.setText(sound.getName());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, RelaxSoundActivity.class);
            intent.putExtra("sound_name", sound.getName());
            intent.putExtra("sound_image", sound.getImageResId());


            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return soundList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
