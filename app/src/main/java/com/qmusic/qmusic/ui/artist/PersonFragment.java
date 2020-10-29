package com.qmusic.qmusic.ui.artist;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.qmusic.qmusic.BASE_URL;
import com.qmusic.qmusic.R;
import com.qmusic.qmusic.parserText.JsonParser;

import java.util.List;

public class PersonFragment extends Fragment {
    RequestQueue queue;
    RecyclerView recyclerView;
    String artist_url = BASE_URL.getBASE_URL() + "mysite/music_post1/databse_qmusic/database_artist.php";
ProgressBar progressBar;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_person, container, false);
        recyclerView = root.findViewById(R.id.fragment_person_recycle);
        progressBar=root.findViewById(R.id.fragment_person_progressBar);
        queue = Volley.newRequestQueue(root.getContext());
        StringRequest stringRequest = new StringRequest(artist_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<Person_model> models = JsonParser.pars_person_model(response);
                Log.e("dataArtist",response);
                Person_Adapter adapter = new Person_Adapter(models,getContext());
                LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
            }
        }, null);
        queue.add(stringRequest);
        return root;
    }
}
