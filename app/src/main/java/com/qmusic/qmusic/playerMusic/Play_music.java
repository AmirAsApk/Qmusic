package com.qmusic.qmusic.playerMusic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.SeekBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;
import com.qmusic.qmusic.R;
import com.qmusic.qmusic.main_post1_music.Main_post1_Model_music;
import com.qmusic.qmusic.playerMusic.comment.Fragment_playMusic_comment;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static com.qmusic.qmusic.main_post1_music.Main_post1_Model_music.BASE_URL;

public class Play_music extends AppCompatActivity implements View.OnClickListener {
    ImageButton btm_playMusic;
    int idMusic;
    MediaPlayer player;
    SeekBar seekBar_time, seekBar_sound;
    AudioManager audioManager;
    TextView tv_timerMax, tv_time_custom;
    ImageButton btm_back;
    Main_post1_Model_music model;
    TabLayout tabLayout;
    ViewPager viewPager;
    List<Fragment> fragmentList;
    List<String> title;
    Fragment_playMusic_download fragment_playMusic_download;
    Fragment_playMusic_txt_music fragment_playMusic_txt_music;
    Fragment_playMusic_comment fragment_playMusic_comment;
    String url_music;
    Context context;
    RequestQueue queue;
    ImageButton btn_like;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        context = getApplicationContext();

        queue = Volley.newRequestQueue(context);
        //notification
        Bundle bundle = getIntent().getExtras();
        //audio manager
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        model = bundle.getParcelable("model");
        //manage multi tab
        title = new ArrayList<>();
        title.add("لینک دانلود");
        title.add("متن ترانه");
        title.add("نظرات");
        url_music = com.qmusic.qmusic.BASE_URL.getBASE_URL() + "mysite/music_post1/music/" + model.getNameIdMusic();
        fragmentList = new ArrayList<>();
        fragment_playMusic_download = new Fragment_playMusic_download();
        fragment_playMusic_txt_music = new Fragment_playMusic_txt_music();
        fragment_playMusic_comment = new Fragment_playMusic_comment();
        Bundle data = new Bundle();
        data.putString("link", url_music);
        fragment_playMusic_download.setArguments(data);

        Bundle txt_music = new Bundle();
        txt_music.putString("txt", model.getTxt_music());

        Bundle comment_id = new Bundle();
        comment_id.putString("id", model.getId());

        fragment_playMusic_comment.setArguments(comment_id);
        fragment_playMusic_txt_music.setArguments(txt_music);
        fragmentList.add(fragment_playMusic_download);
        fragmentList.add(fragment_playMusic_txt_music);
        fragmentList.add(fragment_playMusic_comment);
        btn_like = findViewById(R.id.play_music_ib_like);
        final int current_like = Integer.parseInt(model.getLikeMusic());
        btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getTag().equals("1")) {
                    view.setTag("2");
                    btn_like.setImageResource(R.drawable.ic_like1);
                    serverLikeMusic("sum");
                    manageLikeMusic(current_like + 1 + "");
                } else if (view.getTag().equals("2")) {
                    btn_like.setImageResource(R.drawable.ic_like2);
                    view.setTag("1");
                    serverLikeMusic("sub");
                    manageLikeMusic((current_like - 1) + "");
                }
            }
        });

        viewPager = findViewById(R.id.play_music_viewPager);
        tabLayout = findViewById(R.id.play_music_tab_layout);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        viewPager.setAdapter(new SlidAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);


        ImageView im_photo_MusicBand = findViewById(R.id.play_music_im_photo_MusicBand);
        Picasso.with(getApplicationContext()).load(BASE_URL + "photo/" + model.getNameIdPhoto()).placeholder(R.drawable.ic_gallery).into(im_photo_MusicBand);

        TextView tv_data = findViewById(R.id.play_music_tv_calender);
        //tv_data.setText(data);
        TextView tv_name_musicBand = findViewById(R.id.play_music_name_musicBand);
        tv_name_musicBand.setText(model.getNameSinger());
        manageLikeMusic(model.getLikeMusic());
        TextView tv_bazdid = findViewById(R.id.play_music_tv_bazdid);
        tv_bazdid.setText(model.getViewMusic());
        tv_timerMax = findViewById(R.id.play_music_tv_time_col);
        tv_time_custom = findViewById(R.id.play_music_tv_time_mande);
        btm_playMusic = findViewById(R.id.playMusic_ib_playMusic);
        btm_playMusic.setOnClickListener(this);
        btm_back = findViewById(R.id.play_music_btn_back);
        btm_back.setOnClickListener(this);
        ImageButton nextMusic = findViewById(R.id.play_music_ib_nextMusic);
        ImageButton backMusic = findViewById(R.id.play_music_ib_backMusic);
        nextMusic.setOnClickListener(this);
        backMusic.setOnClickListener(this);
        seekBar_sound = findViewById(R.id.play_music_seekBar_sond);
        seekBar_sound.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekBar_sound.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
            }
        }, 0, 100);
        seekBar_sound.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //seekBar time
        seekBar_time = findViewById(R.id.play_music_seekBar_time);
        seekBar_time.setEnabled(false);
        if (player == null) {
            managePlayer();

        }
        seekBar_time.setMax(player.getDuration());
        seekBar_time.setProgress(0);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekBar_time.setProgress(player.getCurrentPosition());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        int ms = player.getCurrentPosition();
                        int s = ms / 1000;
                        int min = s / 60;
                        tv_time_custom.setText(min + ":" + s);
                    }
                });
            }
        }, 0, 100);
        seekBar_time.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (player != null && fromUser)
                    player.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void serverLikeMusic(final String status) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, com.qmusic.qmusic.BASE_URL.getBASE_URL() + "mysite/music_post1/databse_qmusic/like_music.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, null) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                int like = Integer.parseInt(model.getLikeMusic());
                if (status.equals("sum")) {
                    like = like + 1;
                } else if (status.equals("sub")) {
                    like = like - 1;
                }
                params.put("id", model.getId());
                params.put("$current_like", like + "");
                return params;
            }
        };
        queue.add(stringRequest);
    }

    private void manageLikeMusic(String like) {
        TextView tv_like = findViewById(R.id.ui_post_vi_tv_like);
        tv_like.setText(like);
    }

    private void managePlayer() {
        try {
            player = new MediaPlayer();
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource(url_music);
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == btm_playMusic.getId()) {
            seekBar_time.setEnabled(true);
            manageMusic();

        }
        if (v.getId() == R.id.play_music_btn_back) {
            finishMusic();
        }
        if (v.getId() == R.id.play_music_ib_nextMusic) {
            nextToMusic();
        }
        if (v.getId() == R.id.play_music_ib_backMusic) {
            backToMusic();
        }
    }

    private void finishMusic() {
        player.reset();
        finish();
    }

    public void backToMusic() {
        if (player.getCurrentPosition() - 10000 > 0) {
            player.seekTo(player.getCurrentPosition() - 10000);
        }
    }

    private void nextToMusic() {
        if (player.getCurrentPosition() + 10000 < player.getDuration()) {
            player.seekTo(player.getCurrentPosition() + 10000);
        }
    }

    private void manageMusic() {

        if (player == null) managePlayer();
        if (player.isPlaying()) {
            player.pause();
            btm_playMusic.setImageResource(R.drawable.ic_play);
        } else {
            player.start();
            btm_playMusic.setImageResource(R.drawable.ic_pause);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    int ms = player.getDuration();
                    int s = ms / 1000;
                    int min = s / 60;
                    tv_timerMax.setText(min + ":" + s);
                }
            });
        }

    }

    @Override
    public void onBackPressed() {
        player.reset();
        finish();
    }

    public class SlidAdapter extends FragmentPagerAdapter {
        public SlidAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return title.get(position);
        }
    }

}