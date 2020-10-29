package com.qmusic.qmusic.playerMusic.comment;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
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
import com.qmusic.qmusic.parserText.JsonParser;
import com.qmusic.qmusic.ui.category.Category_Adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment_playMusic_comment extends Fragment implements View.OnClickListener {
    ImageView btn_plus_comment;
    CardView cardView;
    Button btn_send;
    EditText et_name, et_email, et_txt;
    RequestQueue queue;
    String id;
    String url_comment = BASE_URL.getBASE_URL() + "mysite/music_post1/databse_qmusic/comment_music.php";
    RecyclerView recyclerView;
ProgressBar progressBar;
TextView tv_comment_empty;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_play_music_comment, container, false);
        id = getArguments().getString("id");
progressBar=root.findViewById(R.id.fragment_play_music_comment_progressBar);
        btn_plus_comment = root.findViewById(R.id.fragment_play_music_comment_btn_plus);
        btn_plus_comment.setOnClickListener(this);
        cardView = root.findViewById(R.id.fragment_play_music_comment_cardView);
        btn_send = root.findViewById(R.id.fragment_play_music_comment_btn_send);
        btn_send.setOnClickListener(this);
        queue = Volley.newRequestQueue(getContext());
tv_comment_empty=root.findViewById(R.id.fragment_play_music_tv_comment_empty);
        et_name = root.findViewById(R.id.fragment_play_music_comment_et_name);
        et_email = root.findViewById(R.id.fragment_play_music_comment_et_email);
        et_txt = root.findViewById(R.id.fragment_play_music_comment_et_txt);

        recyclerView = root.findViewById(R.id.fragment_play_music_comment_recycle);
        manageGetComment();

        return root;
    }

    private void manageGetComment() {
        StringRequest request = new StringRequest(Request.Method.POST, url_comment, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<Comment_model> models= JsonParser.pars_comment(response);
                if (models.size()==0){
                    tv_comment_empty.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                    recyclerView.setVisibility(View.INVISIBLE);
                }else {
                    Comment_Adapter adapter = new Comment_Adapter(models);
                    LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setAdapter(adapter);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        }, null) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("status", "get");
                params.put("id", id);
                return params;
            }
        };
        queue.add(request);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == btn_plus_comment.getId()) {
            if (cardView.getVisibility() == View.GONE) {
                cardView.setVisibility(View.VISIBLE);
            } else {
                cardView.setVisibility(View.GONE);
            }
        }
        if (view.getId() == btn_send.getId()) {
            manageSetComment();
        }
    }

    private void manageSetComment() {
        final String str_name = et_name.getText().toString().trim();
        final String str_email = et_email.getText().toString().trim();
        final String str_txt = et_txt.getText().toString().trim();

        if (str_name.isEmpty() || str_name.length() > 15 || str_name.length() < 5) {
            et_name.setError("نام وارد شده صحیح نیست");
        } else if (str_email.isEmpty() || !str_email.contains("@") || !str_email.contains(".") || str_email.length() < 10) {
            et_email.setError("ایمیل وارد شده صحیح نیست");
        } else if (str_txt.isEmpty()) {
            et_txt.setError("متن وارد شده صحیح نیست");
        } else {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url_comment, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    recyclerView.invalidate();
                }
            }, null) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("status", "set");
                    params.put("id", id);
                    params.put("name", str_name);
                    params.put("email", str_email);
                    params.put("comment", str_txt);
                    return params;
                }
            };
            queue.add(stringRequest);
            Toast.makeText(getContext(), "نظر شما با موفقیت ارسال گردید...", Toast.LENGTH_SHORT).show();
        }
    }
}
