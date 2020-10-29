package com.qmusic.qmusic.ui.artist;

import android.os.Parcel;
import android.os.Parcelable;

public class Person_model implements Parcelable {
    public static final String KEY_NAME="name";
    public static final String KEY_FIELD="field";
    public static final String KEY_FLOWER="flower";
    public static final String KEY_LIKE="like";
    public static final String KEY_VIEW="view";
    public static final String KEY_BIO="bio";
    public static final String KEY_IDPHOTO="idPhoto";
    public static final String KEY_ID="id";

    private String name;
    private String field;
    private String flower;
    private String like;
    private String view;
    private String bio;
    private String idPhoto;
    private String id;

    protected Person_model(Parcel in) {
        name = in.readString();
        field = in.readString();
        flower = in.readString();
        like = in.readString();
        view = in.readString();
        bio = in.readString();
        idPhoto = in.readString();
        id=in.readString();
    }
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(field);
        parcel.writeString(flower);
        parcel.writeString(like);
        parcel.writeString(view);
        parcel.writeString(bio);
        parcel.writeString(idPhoto);
        parcel.writeString(id);
    }
    public Person_model() {
    }

    public static final Creator<Person_model> CREATOR = new Creator<Person_model>() {
        @Override
        public Person_model createFromParcel(Parcel in) {
            return new Person_model(in);
        }

        @Override
        public Person_model[] newArray(int size) {
            return new Person_model[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getFlower() {
        return flower;
    }

    public void setFlower(String flower) {
        this.flower = flower;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(String idPhoto) {
        this.idPhoto = idPhoto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }


}
