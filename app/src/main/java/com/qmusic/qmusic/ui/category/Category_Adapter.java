package com.qmusic.qmusic.ui.category;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qmusic.qmusic.R;
import com.qmusic.qmusic.ui.category.show_category.Show_category_adapter;
import com.qmusic.qmusic.ui.category.show_category.show_category_activity;

import java.util.List;

public class Category_Adapter extends RecyclerView.Adapter<Category_Adapter.holder> {
    List<Category_Model> models;
    Context context;

    public Category_Adapter(List<Category_Model> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ui_recycle_category, parent, false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final holder holder, int position) {
        final Category_Model model = models.get(position);
        holder.tv1.setText(model.getText1());
        holder.tv2.setText(model.getText2());
        holder.tv3.setText(model.getText3());


        holder.tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startIntent(model.getTag1());
            }
        });
        holder.tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startIntent(model.getTag2());
            }
        });
    }

    private void startIntent(String s) {

        Intent intent = new Intent(context, show_category_activity.class);
        intent.putExtra("tag", s);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    class holder extends RecyclerView.ViewHolder {
        TextView tv1, tv2, tv3;

        public holder(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.ui_recycle_category_tv1);
            tv2 = itemView.findViewById(R.id.ui_recycle_category_tv2);
            tv3 = itemView.findViewById(R.id.ui_recycle_category_tv3);
        }
    }
}
