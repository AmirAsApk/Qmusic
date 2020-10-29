package com.qmusic.qmusic.main_post1_music.edame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.qmusic.qmusic.BASE_URL;
import com.qmusic.qmusic.R;
import com.qmusic.qmusic.main_post1_music.Main_post1_Model_music;
import com.qmusic.qmusic.parserText.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class Edame_post1 extends AppCompatActivity {
    RecyclerView recyclerView;
    String url = BASE_URL.getBASE_URL() + "mysite/music_post1/databse_qmusic/database_main_post1.php";
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edame_post1);
        recyclerView = findViewById(R.id.edame_post1_recycle_view);
        queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("data_edame",response);
                List<Main_post1_Model_music> model_musics = JsonParser.parsMain_post_model_music(response);
                for (Main_post1_Model_music model:model_musics){
                    Log.e("data_edame",model.getNameSinger());
                }
                LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
                Edame_post1_Adapter adapter = new Edame_post1_Adapter(model_musics, Edame_post1.this);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);
            }
        }, null);
        queue.add(stringRequest);
    }
}