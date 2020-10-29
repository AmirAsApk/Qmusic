package com.qmusic.qmusic.User;

import android.os.Bundle;
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

import java.util.HashMap;
import java.util.Map;


public class Fragment_register extends Fragment implements View.OnClickListener {
    EditText et_name, et_email, et_number, et_pass;
    Button btn_register;
    RequestQueue queue;
    String URL_loginForm = BASE_URL.getBASE_URL() + "mysite/music_post1/databse_qmusic/register_form.php";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        et_name = view.findViewById(R.id.fragment_register_et_name);
        et_email = view.findViewById(R.id.fragment_register_et_email);
        et_number = view.findViewById(R.id.fragment_register_et_numberPhone);
        et_pass = view.findViewById(R.id.fragment_register_et_pass);
        btn_register = view.findViewById(R.id.fragment_register_btn_register);
        btn_register.setOnClickListener(this);
        queue = Volley.newRequestQueue(getContext());
        return view;
    }

    @Override
    public void onClick(View view) {
        final String st_name = et_name.getText().toString().trim();
        final String st_email = et_email.getText().toString().trim();
        final String st_number = et_number.getText().toString().trim();
        final String st_pass = et_pass.getText().toString().trim();

        if (st_name.isEmpty() || st_name.length() < 5 || st_name.startsWith("@") || st_name.startsWith("*") || st_name.startsWith("$") || st_name.startsWith("#") || st_name.startsWith(".")) {
            et_name.setError("نام وارد شده معتبر نیست");
        } else if (st_email.isEmpty() || !st_email.contains("@") || !st_email.contains(".") || !st_email.contains(".com") || st_email.startsWith("@")) {
            et_email.setError("ایمیل وارد شده معتبر نیست");
        } else if (st_number.isEmpty() || st_number.length() < 11) {
            et_number.setError("شماره تلفن وارد شده معتبر نیست");
        } else if (st_pass.isEmpty() || st_pass.length() < 4 || et_pass.length() > 8) {
            et_pass.setError("رمز وارد شده معتبر نیست");
        } else {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_loginForm, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                }
            }, null) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("name", st_name);
                    params.put("email", st_email);
                    params.put("numberPhone", st_number);
                    params.put("password", st_pass);
                    return params;
                }
            };
            queue.add(stringRequest);
            Save_pref save_pref = new Save_pref(getContext());
            save_pref.setPref(true,st_name,st_email,st_number);
            getActivity().finish();
        }

    }
}
