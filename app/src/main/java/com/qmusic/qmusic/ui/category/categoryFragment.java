package com.qmusic.qmusic.ui.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.qmusic.qmusic.R;

import java.util.ArrayList;
import java.util.List;

public class categoryFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_category, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.fragment_category_recycle);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        List<Category_Model> models = new ArrayList<>();
        models.add(new Category_Model("موزیک", "موزیک ایرانی", "موزیک خارجی", "music_iran", "music_foreign"));
        models.add(new Category_Model("البوم", "البوم ایرانی", "البوم خارجی", "albom_iran", "albom_foreign"));
        models.add(new Category_Model("ویدیو", "ویدیو ایرانی", "ویدیو خارجی", "video_iran", "video_foreign"));
        models.add(new Category_Model("انیمیشن", "انیمیشن ایرانی", "انیمیشن خارجی", "animation_iran", "animation_foreign"));
        models.add(new Category_Model("مستند", "مستند ایرانی", "مستند خارجی", "mostanad_iran", "mostanad_foreign"));

        Category_Adapter adapter = new Category_Adapter(models, getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        return root;
    }
}
