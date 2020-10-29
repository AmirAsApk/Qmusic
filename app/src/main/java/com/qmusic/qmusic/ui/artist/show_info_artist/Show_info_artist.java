package com.qmusic.qmusic.ui.artist.show_info_artist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.qmusic.qmusic.BASE_URL;
import com.qmusic.qmusic.R;
import com.qmusic.qmusic.User.Save_pref;
import com.qmusic.qmusic.ui.artist.Person_Adapter;
import com.qmusic.qmusic.ui.artist.Person_model;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class Show_info_artist extends AppCompatActivity implements View.OnClickListener {
    TextView tv_bio;
    String photoUrl = BASE_URL.getBASE_URL() + "mysite/music_post1/photo/";
    Button btn_flw;
    Person_model model;
    public static String flw_url = BASE_URL.getBASE_URL() + "mysite/music_post1/databse_qmusic/artist_flw.php";
    int current_flw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_info_artist);
        Bundle bundle = getIntent().getExtras();
        model = bundle.getParcelable("model");
        ImageView image = findViewById(R.id.activity_show_info_image);
        current_flw = Integer.parseInt(model.getFlower());
        Picasso.with(getApplicationContext()).load(photoUrl + model.getIdPhoto()).placeholder(R.drawable.ic_gallery).into(image);
        TextView tv_flower = findViewById(R.id.activity_show_info_tv_flower);
        tv_flower.setText(model.getFlower() + "دنبال کننده : ");
        tv_bio = findViewById(R.id.activity_show_info_tv_bio);
        tv_bio.setText(model.getBio());
        btn_flw = findViewById(R.id.activity_show_info_btn_flow);
        btn_flw.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.activity_show_info_btn_bio) {
            if (tv_bio.getVisibility() == View.GONE)
                tv_bio.setVisibility(View.VISIBLE);
            else
                tv_bio.setVisibility(View.GONE);
        }
        if (view.getId() == R.id.activity_show_info_btn_flow) {
            handelBtn_flw(getApplicationContext(), btn_flw, model);
        }
    }

    public void handelBtn_flw(Context context, Button btn_flower, final Person_model model) {
        if (new Save_pref(context).getPref()) {
            if (btn_flower.getTag().equals("1")) {
                btn_flower.setBackground(context.getResources().getDrawable(R.drawable.bg_btn_flow2));
                btn_flower.setTextColor(Color.WHITE);
                btn_flower.setTag("2");
                current_flw = current_flw + 1;
            } else {
                btn_flower.setBackground(context.getResources().getDrawable(R.drawable.bg_btn_flow));
                btn_flower.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                btn_flower.setTag("1");
                current_flw = current_flw - 1;
            }
            RequestQueue queue = Volley.newRequestQueue(context);

            final int finalCurrent_flw = current_flw;
            StringRequest request = new StringRequest(Request.Method.POST, flw_url, null, null) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("id", model.getId());
                    params.put("flw", String.valueOf(finalCurrent_flw));
                    return params;
                }
            };
            queue.add(request);
        } else {
            Toast.makeText(context, "این قابلیت فقط برای اعضای برنامه هست", Toast.LENGTH_SHORT).show();
        }
    }
}