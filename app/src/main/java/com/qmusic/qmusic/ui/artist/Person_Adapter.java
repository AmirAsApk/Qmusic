package com.qmusic.qmusic.ui.artist;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.qmusic.qmusic.BASE_URL;
import com.qmusic.qmusic.R;
import com.qmusic.qmusic.User.Save_pref;
import com.qmusic.qmusic.ui.artist.show_info_artist.Show_info_artist;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class Person_Adapter extends RecyclerView.Adapter<Person_Adapter.holder> {
    List<Person_model> models;
    String photo_url = BASE_URL.getBASE_URL() + "mysite/music_post1/photo/";
    RequestQueue queue;
    public static String flw_url = BASE_URL.getBASE_URL() + "mysite/music_post1/databse_qmusic/artist_flw.php";
    Context context;
    Save_pref pref;

    public Person_Adapter(List<Person_model> models, Context context) {
        this.models = models;
        this.context = context;
        queue = Volley.newRequestQueue(context);
        pref = new Save_pref(context);
    }
    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ui_person_recycle, parent, false);
        return new holder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final holder holder, int position) {
        final Person_model model = models.get(position);
        Picasso.with(holder.image.getContext()).load(photo_url + model.getIdPhoto()).placeholder(R.drawable.ic_gallery).into(holder.image);
        holder.tv_name.setText(model.getName());
        holder.tv_field.setText(model.getField());
        context = holder.layout.getContext();
        StringRequest request = new StringRequest(Request.Method.POST, BASE_URL.getBASE_URL() + "mysite/music_post1/databse_qmusic/artist_flw.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                holder.tv_flower.setText("دنبال کننده : " + response);
            }
        }, null) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("status", "get_num_artist");
                param.put("artist", model.getId());
                return param;
            }
        };
        queue.add(request);

        holder.btn_flower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handelBtn_flw(context, holder.btn_flower, model);
            }
        });
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.layout.getContext(), Show_info_artist.class);
                intent.putExtra("model", model);
                holder.layout.getContext().startActivity(intent);
            }
        });
    }

    public void handelBtn_flw(Context context, Button btn_flower, final Person_model model) {
        String id = model.getId();
        if (btn_flower.getTag().equals("1")) {
            btn_flower.setBackground(context.getResources().getDrawable(R.drawable.bg_btn_flow2));
            btn_flower.setTextColor(Color.WHITE);
            btn_flower.setTag("2");
            manage_flw(id,"set");
        } else {
            btn_flower.setBackground(context.getResources().getDrawable(R.drawable.bg_btn_flow));
            btn_flower.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            btn_flower.setTag("1");
            manage_flw(id,"delete");
        }

    }

    private void manage_flw(final String id, final String status) {
        if (pref.getPref()) {
            StringRequest request = new StringRequest(Request.Method.POST, flw_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e("flw",response);
                }
            }, null) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("status", status);
                    params.put("artist", id);
                    params.put("user", pref.getNumberPhone());
                    return params;
                }
            };
            queue.add(request);
        } else {
            Toast.makeText(context, "این قابلیت فقط برای اعضای برنامه هست", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    class holder extends RecyclerView.ViewHolder {
        CircleImageView image;
        TextView tv_name, tv_field, tv_flower;
        Button btn_flower;
        RelativeLayout layout;

        public holder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.ui_person_recycle_layout);
            image = itemView.findViewById(R.id.ui_person_recycle_circleImage);
            tv_name = itemView.findViewById(R.id.ui_person_recycle_tv_name);
            tv_field = itemView.findViewById(R.id.ui_person_recycle_tv_field);
            tv_flower = itemView.findViewById(R.id.ui_person_recycle_tv_flow);
            btn_flower = itemView.findViewById(R.id.ui_person_recycle_btn_flow);


        }

    }
}
