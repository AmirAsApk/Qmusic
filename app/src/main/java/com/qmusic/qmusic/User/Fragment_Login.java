package com.qmusic.qmusic.User;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.qmusic.qmusic.BASE_URL;
import com.qmusic.qmusic.R;
import com.qmusic.qmusic.parserText.JsonParser;
import com.qmusic.qmusic.settings.setting_user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment_Login extends Fragment {
    EditText et_email, et_pass;
    Button btn_login;
    RequestQueue queue;
    String url_login = BASE_URL.getBASE_URL() + "mysite/music_post1/databse_qmusic/login.php";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        et_email = view.findViewById(R.id.fragment_login_et_email);
        et_pass = view.findViewById(R.id.fragment_login_et_pass);
        btn_login = view.findViewById(R.id.fragment_login_btn_login);
        queue = Volley.newRequestQueue(getContext());
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               StringRequest stringRequest = new StringRequest(Request.Method.POST, url_login, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Save_pref save_pref = new Save_pref(getActivity());
                        List<setting_user> models = JsonParser.pars_setting_user(response);
                        setting_user model = models.get(0);
                        if (response.length() > 10) {
                            save_pref.setPref(true, model.getName(), model.getEmail(), model.getNumberPhone());
                            getActivity().finish();
                        }
                    }
                }, null) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<>();
                        map.put("key_email", et_email.getText().toString().trim());
                        map.put("key_pass", et_pass.getText().toString().trim());
                        return map;
                    }
                };
                queue.add(stringRequest);
            }
        });

        return view;

    }
}
