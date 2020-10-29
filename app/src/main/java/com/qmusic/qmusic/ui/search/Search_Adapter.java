package com.qmusic.qmusic.ui.search;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qmusic.qmusic.BASE_URL;
import com.qmusic.qmusic.R;
import com.qmusic.qmusic.main_post1_music.Main_post1_Model_music;
import com.qmusic.qmusic.playerMusic.Play_music;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Search_Adapter extends RecyclerView.Adapter<Search_Adapter.holder> {
    List<Main_post1_Model_music> models;

    public Search_Adapter(List<Main_post1_Model_music> model) {
        this.models = model;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ui_recycle_search, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final holder holder, int position) {
        final Main_post1_Model_music model = models.get(position);
        final Context context = holder.layout.getContext();
        holder.tv_nameMusic.setText(model.getNameMusic());
        holder.tv_nameSinger.setText(model.getNameSinger());
        holder.tv_view.setText("بازدید : " + model.getViewMusic());
        holder.tv_like.setText(model.getLikeMusic());
        Picasso.with(context).load(BASE_URL.getBASE_URL() + "/mysite/music_post1/photo/" + models.get(position).getNameIdPhoto()).placeholder(R.drawable.ic_gallery).into(holder.circleImageView);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Play_music.class);
                intent.putExtra("model", model);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    class holder extends RecyclerView.ViewHolder {
        TextView tv_nameMusic, tv_nameSinger, tv_view, tv_like;
        CircleImageView circleImageView;
        RelativeLayout layout;

        public holder(@NonNull View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.ui_recycle_search_image);
            tv_nameMusic = itemView.findViewById(R.id.ui_recycle_search_tv_nameMusic);
            tv_nameSinger = itemView.findViewById(R.id.ui_recycle_search_tv_nameSinger);
            tv_view = itemView.findViewById(R.id.ui_recycle_search_tv_view);
            tv_like = itemView.findViewById(R.id.ui_recycle_search_tv_like);
            layout = itemView.findViewById(R.id.ui_recycle_search_layout);
        }

    }
}
