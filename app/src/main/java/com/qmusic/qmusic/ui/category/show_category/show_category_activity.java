package com.qmusic.qmusic.ui.category.show_category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.qmusic.qmusic.BASE_URL;
import com.qmusic.qmusic.R;
import com.qmusic.qmusic.main_post1_music.Main_post1_Model_music;
import com.qmusic.qmusic.parserText.JsonParser;
import com.qmusic.qmusic.ui.category.Category_Adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class show_category_activity extends AppCompatActivity {
    RecyclerView recyclerView;
    RequestQueue queue;
    TextView tv;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_category_activity);
        Bundle data = getIntent().getExtras();
        tv = findViewById(R.id.activity_show_category_activity_tv);
        progressBar = findViewById(R.id.activity_show_category_activity_progressBar);
        final String tag = data.getString("tag");
        recyclerView = findViewById(R.id.activity_show_category_activity_recycle);
        queue = Volley.newRequestQueue(getApplicationContext());

        StringRequest request = new StringRequest(Request.Method.POST, BASE_URL.getBASE_URL() + "mysite/music_post1/databse_qmusic/category.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Fragment_category", response);
                List<Main_post1_Model_music> models = JsonParser.parsMain_post_model_music(response);
                Show_category_adapter adapter = new Show_category_adapter(models);
                GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 3, RecyclerView.VERTICAL, false);
                if (adapter.getItemCount() == 0) {
                    tv.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                } else {

                    tv.setVisibility(View.INVISIBLE);
                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setAdapter(adapter);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        }, null) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("category", tag);
                return map;
            }
        };
        queue.add(request);
    }
}