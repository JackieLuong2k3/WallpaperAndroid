package com.example.mock2.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mock2.Adapter.RelaxSoundAdapter;
import com.example.mock2.Model.RelaxSoundModel;
import com.example.mock2.R;

import java.util.ArrayList;
import java.util.List;

public class RelaxSoundFragment extends Fragment {

    private RecyclerView recyclerView;
    private RelaxSoundAdapter adapter;
    private List<RelaxSoundModel> soundList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_relax_sound, container, false);

        recyclerView = view.findViewById(R.id.recyclerRelaxSound);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        soundList = new ArrayList<>();
        soundList.add(new RelaxSoundModel("Piano", R.drawable.piano));
        soundList.add(new RelaxSoundModel("Relax Time", R.drawable.relaxtime));
        soundList.add(new RelaxSoundModel("Heavy Rain", R.drawable.heavyrain));
        soundList.add(new RelaxSoundModel("Spring Rain", R.drawable.springrain));
        soundList.add(new RelaxSoundModel("Memories", R.drawable.memories));
        soundList.add(new RelaxSoundModel("Light Rain", R.drawable.lightrain));
        soundList.add(new RelaxSoundModel("Zither", R.drawable.zither));
        soundList.add(new RelaxSoundModel("Clock", R.drawable.clock));
        soundList.add(new RelaxSoundModel("Autumn Jungle", R.drawable.autumjungle));
        soundList.add(new RelaxSoundModel("Rain on Roof", R.drawable.rainonroof));
        soundList.add(new RelaxSoundModel("City Life", R.drawable.citylife));
        soundList.add(new RelaxSoundModel("Lullaby Music Box", R.drawable.lulababy));
        soundList.add(new RelaxSoundModel("Beach", R.drawable.beach));



        adapter = new RelaxSoundAdapter(getContext(), soundList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
