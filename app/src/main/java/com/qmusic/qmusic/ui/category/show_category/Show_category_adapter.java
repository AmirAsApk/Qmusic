package com.qmusic.qmusic.ui.category.show_category;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qmusic.qmusic.BASE_URL;
import com.qmusic.qmusic.R;
import com.qmusic.qmusic.main_post1_music.Main_post1_Model_music;
import com.qmusic.qmusic.playerMusic.Play_music;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Show_category_adapter extends RecyclerView.Adapter<Show_category_adapter.holder> {
    List<Main_post1_Model_music> models;

    public Show_category_adapter(List<Main_post1_Model_music> models) {
        this.models = models;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ui_recycle_show_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final holder holder, int position) {
        final Main_post1_Model_music model = models.get(position);
        Picasso.with(holder.image.getContext()).load(BASE_URL.getBASE_URL() + "mysite/music_post1/photo/" + model.getNameIdPhoto()).placeholder(R.drawable.ic_gallery).into(holder.image);
        holder.tv_name.setText(model.getNameMusic());
        holder.tv_singer.setText(model.getNameSinger());
        holder.tv_like.setText(model.getLikeMusic());
        holder.tv_view.setText(model.getViewMusic());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.layout.getContext(), Play_music.class);
                intent.putExtra("model", model);
                holder.layout.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    class holder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView tv_name, tv_singer, tv_view, tv_like;
        LinearLayout layout;

        public holder(@NonNull View v) {
            super(v);
            image = v.findViewById(R.id.ui_recycle_show_category_image);
            tv_name = v.findViewById(R.id.ui_recycle_show_category_tv_name);
            tv_singer = v.findViewById(R.id.ui_recycle_show_category_tv_name_singer);
            tv_view = v.findViewById(R.id.ui_recycle_show_category_tv_view);
            tv_like = v.findViewById(R.id.ui_recycle_show_category_tv_like);
            layout = v.findViewById(R.id.ui_recycle_show_category_layout);
        }
    }
}
