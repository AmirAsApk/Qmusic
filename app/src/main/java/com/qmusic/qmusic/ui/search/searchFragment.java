package com.qmusic.qmusic.ui.search;

import android.app.DownloadManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class searchFragment extends Fragment {
    RequestQueue queue;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    TextView tv_empty;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        EditText et_search = root.findViewById(R.id.fragment_search_et);
        queue = Volley.newRequestQueue(root.getContext());
        recyclerView = root.findViewById(R.id.fragment_search_recycle);
        progressBar = root.findViewById(R.id.fragment_search_progress_bar);
        tv_empty = root.findViewById(R.id.fragment_search_isEmpty);
        et_search.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(final CharSequence charSequence, int i, int i1, int i2) {
                progressBar.setVisibility(View.VISIBLE);
                if (charSequence.toString().isEmpty()) {
                    tv_empty.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                    progressBar.setVisibility(View.INVISIBLE);
                    return;
                } else {
                    tv_empty.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);

                    StringRequest request = new StringRequest(Request.Method.POST, BASE_URL.getBASE_URL() + "mysite/music_post1/databse_qmusic/search_muisc.php", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("false")) {
                                tv_empty.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.GONE);
                                progressBar.setVisibility(View.INVISIBLE);
                                return;
                            } else {
                                tv_empty.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                            }
                            List<Main_post1_Model_music> model = JsonParser.parsMain_post_model_music(response);
                            Search_Adapter adapter = new Search_Adapter(model);
                            LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                            recyclerView.setLayoutManager(manager);
                            recyclerView.setAdapter(adapter);
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }, null) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("key", charSequence.toString());
                            return params;
                        }
                    };
                    queue.add(request);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return root;
    }
}