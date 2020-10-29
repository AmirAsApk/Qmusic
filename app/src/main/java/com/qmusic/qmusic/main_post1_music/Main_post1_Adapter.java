package com.qmusic.qmusic.main_post1_music;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qmusic.qmusic.playerMusic.Play_music;
import com.qmusic.qmusic.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.qmusic.qmusic.main_post1_music.Main_post1_Model_music.BASE_URL;

public class Main_post1_Adapter extends RecyclerView.Adapter<Main_post1_Adapter.holder> {
    List<Main_post1_Model_music> models;
    Context context;
    MediaPlayer mediaPlayer;
    String music_url;

    public Main_post1_Adapter(List<Main_post1_Model_music> models, Context context) {
        this.models = models;
        this.context = context;
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
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


        holder.tv_nameMusic.setText(model.getNameMusic());
        holder.tv_nameSinger.setText(model.getNameSinger());
        holder.tv_comment.setText(model.getCommentMusic());
        holder.tv_like.setText(model.getLikeMusic());
        Picasso.with(context).load(BASE_URL + "photo/" + model.getNameIdPhoto()).placeholder(R.drawable.ic_gallery).into(holder.image);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Play_music.class);
                intent.putExtra("model", model);
                context.startActivity(intent);
            }
        });
        holder.btn_play.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                music_url = com.qmusic.qmusic.BASE_URL.getBASE_URL() + "mysite/music_post1/music/" + model.getNameIdMusic();

                holder.btn_stop.setVisibility(View.VISIBLE);
                holder.btn_play.setVisibility(View.GONE);
                try {
                    mediaPlayer.setDataSource(music_url);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        holder.btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holder.btn_stop.setVisibility(View.GONE);
                holder.btn_play.setVisibility(View.VISIBLE);
                mediaPlayer.stop();
                mediaPlayer.reset();

            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    class holder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView tv_nameMusic, tv_nameSinger, tv_like, tv_comment;
        RelativeLayout layout;
        ImageView btn_play, btn_stop;

        public holder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.ui_recycle_main_post1_image);
            tv_nameMusic = itemView.findViewById(R.id.ui_recycle_main_post1_tv_nameMusic);
            tv_nameSinger = itemView.findViewById(R.id.ui_recycle_main_post1_tv_nameSinger);
            tv_like = itemView.findViewById(R.id.ui_recycle_main_post1_tv_like);
            tv_comment = itemView.findViewById(R.id.ui_recycle_main_post1_tv_comment);
            layout = itemView.findViewById(R.id.ui_recycle_main_post1_layout);
            btn_play = itemView.findViewById(R.id.ui_recycle_main_post1_btn_play);
            btn_stop = itemView.findViewById(R.id.ui_recycle_main_post1_btn_stop);
        }

    }

}
