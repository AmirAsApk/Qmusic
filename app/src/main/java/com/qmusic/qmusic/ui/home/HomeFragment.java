package com.qmusic.qmusic.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.qmusic.qmusic.R;
import com.qmusic.qmusic.main_post1_music.Main_post1_Adapter;
import com.qmusic.qmusic.main_post1_music.Main_post1_Model_music;
import com.qmusic.qmusic.main_post1_music.edame.Edame_post1;
import com.qmusic.qmusic.parserText.JsonParser;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static com.qmusic.qmusic.main_post1_music.Main_post1_Model_music.BASE_URL;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    RequestQueue queue;
    List<Main_post1_Model_music> models;
    ProgressBar progressBar;
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        queue = Volley.newRequestQueue(root.getContext());
        progressBar=root.findViewById(R.id.fragment_home_progressBar);
        progressBar.setVisibility(View.VISIBLE);
        TextView tv_edame=root.findViewById(R.id.fragment_home_tv_edame);
        tv_edame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),com.qmusic.qmusic.ui.category.show_category.show_category_activity.class);
                intent.putExtra("tag","music_iran");
                startActivity(intent);
            }
        });
        StringRequest stringRequest = new StringRequest(BASE_URL + "databse_qmusic/database_main_post1.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    String decodedToUTF8 = new String(response.getBytes("ISO-8859-1"), "UTF-8");
                    models=JsonParser.parsMain_post_model_music(decodedToUTF8);

                    recyclerView = root.findViewById(R.id.ui_recycle_main_post1_recycle);
                    LinearLayoutManager manager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
                    Main_post1_Adapter adapter = new Main_post1_Adapter(models,getContext());
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setAdapter(adapter);
                    progressBar.setVisibility(View.INVISIBLE);
                    Log.i("data",decodedToUTF8);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }, null);
        queue.add(stringRequest);
        return root;
    }

}