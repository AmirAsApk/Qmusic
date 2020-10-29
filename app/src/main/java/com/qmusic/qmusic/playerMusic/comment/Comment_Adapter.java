package com.qmusic.qmusic.playerMusic.comment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qmusic.qmusic.R;

import java.util.List;

public class Comment_Adapter extends RecyclerView.Adapter<Comment_Adapter.holder> {
    List<Comment_model> models;

    public Comment_Adapter(List<Comment_model> models) {
        this.models = models;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ui_recycle_player_comment, parent, false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        Comment_model model = models.get(position);
        holder.tv_name.setText(model.getName());
        holder.tv_txt.setText(model.getTxt());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    class holder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_txt;

        public holder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.ui_recycle_player_comment_tv_name);
            tv_txt = itemView.findViewById(R.id.ui_recycle_player_comment_tv_txt);
        }
    }
}
