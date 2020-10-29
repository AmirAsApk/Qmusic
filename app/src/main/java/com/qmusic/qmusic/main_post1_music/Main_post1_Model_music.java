package com.qmusic.qmusic.main_post1_music;

import android.os.Parcel;
import android.os.Parcelable;

import com.qmusic.qmusic.BASE_URL;

public class Main_post1_Model_music implements Parcelable {
    public static String BASE_URL = com.qmusic.qmusic.BASE_URL.getBASE_URL() + "/mysite/music_post1/";
    private String nameMusic;
    private String nameSinger;
    private String nameIdPhoto;
    private String nameIdMusic;
    private String likeMusic;
    private String viewMusic;
    private String commentMusic;
    private String id;
    private String txt_music;
    private String category;

    public static final String KEY_NAME_MUSIC = "nameMusic";
    public static final String KEY_NAME_SINGER = "nameSinger";
    public static final String KEY_LIKE_MUSIC = "likeMusic";
    public static final String KEY_COMMENT_MUSIC = "comment";
    public static final String KEY_ID_PHOTO = "nameIdphoto";
    public static final String KEY_ID_MUSIC = "nameIdMusic";
    public static final String KEY_VIEW_MUSIC = "viewMusic";
    public static final String KEY_ID = "idMusic";
    public static final String KEY_TXT_MUSIC = "txtMusic";
    public static final String KEY_CATEGORY = "category";

    public Main_post1_Model_music(Parcel in) {
        nameMusic = in.readString();
        nameSinger = in.readString();
        nameIdPhoto = in.readString();
        nameIdMusic = in.readString();
        likeMusic = in.readString();
        viewMusic = in.readString();
        commentMusic = in.readString();
        txt_music = in.readString();
        category=in.readString();
        id = in.readString();
    }

    public static final Creator<Main_post1_Model_music> CREATOR = new Creator<Main_post1_Model_music>() {
        @Override
        public Main_post1_Model_music createFromParcel(Parcel in) {
            return new Main_post1_Model_music(in);
        }

        @Override
        public Main_post1_Model_music[] newArray(int size) {
            return new Main_post1_Model_music[size];
        }
    };

    public Main_post1_Model_music() {

    }

    public void setNameMusic(String nameMusic) {
        this.nameMusic = nameMusic;
    }

    public void setNameSinger(String nameSinger) {
        this.nameSinger = nameSinger;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNameIdPhoto(String nameIdPhoto) {
        this.nameIdPhoto = nameIdPhoto;
    }

    public void setNameIdMusic(String nameIdMusic) {
        this.nameIdMusic = nameIdMusic;
    }

    public void setLikeMusic(String likeMusic) {
        this.likeMusic = likeMusic;
    }

    public void setViewMusic(String viewMusic) {
        this.viewMusic = viewMusic;
    }

    public void setCommentMusic(String commentMusic) {
        this.commentMusic = commentMusic;
    }

    public String getCommentMusic() {
        return commentMusic;
    }

    public String getNameMusic() {
        return nameMusic;
    }

    public String getNameSinger() {
        return nameSinger;
    }

    public String getNameIdPhoto() {
        return nameIdPhoto;
    }

    public String getNameIdMusic() {
        return nameIdMusic;
    }

    public String getLikeMusic() {
        return likeMusic;
    }

    public String getViewMusic() {
        return viewMusic;
    }

    public String getTxt_music() {
        return txt_music;
    }

    public void setTxt_music(String txt_music) {
        this.txt_music = txt_music;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nameMusic);
        parcel.writeString(nameSinger);
        parcel.writeString(nameIdPhoto);
        parcel.writeString(nameIdMusic);
        parcel.writeString(likeMusic);
        parcel.writeString(viewMusic);
        parcel.writeString(commentMusic);
        parcel.writeString(txt_music);
        parcel.writeString(category);
        parcel.writeString(id);
    }

    public static final Creator<Main_post1_Model_music> Creator = new Creator<Main_post1_Model_music>() {
        @Override
        public Main_post1_Model_music createFromParcel(Parcel parcel) {
            return new Main_post1_Model_music(parcel);
        }

        @Override
        public Main_post1_Model_music[] newArray(int i) {
            return new Main_post1_Model_music[i];
        }
    };
}