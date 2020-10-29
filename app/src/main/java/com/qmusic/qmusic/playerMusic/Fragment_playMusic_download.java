package com.qmusic.qmusic.playerMusic;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.RenderScript;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.qmusic.qmusic.R;

public class Fragment_playMusic_download extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_play_music_download, container, false);
        TextView tv_down=root.findViewById(R.id.fragment_play_music_tv_download);
        tv_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             String s =  getArguments().getString("link");
                Intent intent_down=new Intent(Intent.ACTION_VIEW);
                intent_down.setData(Uri.parse(s));
                startActivity(intent_down);
            }
        });
        return root;
    }
}
