package com.qmusic.qmusic.playerMusic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.qmusic.qmusic.R;

public class Fragment_playMusic_txt_music extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_play_music_txt_music, container, false);
        TextView txt = root.findViewById(R.id.fragment_play_music_txt);
        txt.setText(getArguments().getString("txt"));
        return root;
    }
}
