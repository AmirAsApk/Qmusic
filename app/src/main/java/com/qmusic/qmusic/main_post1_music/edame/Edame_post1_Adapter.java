package com.qmusic.qmusic.main_post1_music.edame;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qmusic.qmusic.R;
import com.qmusic.qmusic.main_post1_music.Main_post1_Model_music;
import com.qmusic.qmusic.playerMusic.Play_music;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.qmusic.qmusic.main_post1_music.Main_post1_Model_music.BASE_URL;

public class Edame_post1_Adapter extends RecyclerView.Adapter<Edame_post1_Adapter.holder> {
    List<Main_post1_Model_music> models;
    Context context;

    public Edame_post1_Adapter(List<Main_post1_Model_music> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ui_recycle_main_post1, parent, false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final holder holder, int position) {

        final Main_post1_Model_music model = models.get(position);
        if (model == null) return;
        Picasso.with(context).load(BASE_URL + "photo/" + model.getNameIdPhoto()).placeholder(R.drawable.ic_gallery).into(holder.image);
        holder.tv_nameMusic.setText(model.getNameMusic());
        holder.tv_nameSinger.setText(model.getNameSinger());
        holder.tv_view.setText(model.getViewMusic() + "بازدید :");

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
        ImageView image;
        TextView tv_nameMusic, tv_nameSinger, tv_view;
        LinearLayout layout;

        public holder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.ui_edame_image);
            tv_nameMusic = itemView.findViewById(R.id.ui_edame_tv_name_music);
            tv_nameSinger = itemView.findViewById(R.id.ui_edame_tv_name_singer);
            tv_view = itemView.findViewById(R.id.ui_edame_tv_view);
            layout = itemView.findViewById(R.id.ui_edame_layout);
        }

    }

}
