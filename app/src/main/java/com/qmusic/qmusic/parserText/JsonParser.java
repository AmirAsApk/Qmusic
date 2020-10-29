package com.qmusic.qmusic.parserText;

import com.android.volley.toolbox.JsonObjectRequest;
import com.qmusic.qmusic.main_post1_music.Main_post1_Model_music;
import com.qmusic.qmusic.playerMusic.comment.Comment_model;
import com.qmusic.qmusic.settings.setting_user;
import com.qmusic.qmusic.ui.artist.Person_model;
import com.qmusic.qmusic.ui.artist.model_flw_user;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    public static List<Main_post1_Model_music> parsMain_post_model_music(String s) {
        List<Main_post1_Model_music> models = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(s);
            for (int i = 0; i < array.length(); i++) {

                JSONObject object = array.getJSONObject(i);

                Main_post1_Model_music model = new Main_post1_Model_music();

                model.setNameMusic(object.getString(Main_post1_Model_music.KEY_NAME_MUSIC));

                model.setNameSinger(object.getString(Main_post1_Model_music.KEY_NAME_SINGER));

                model.setNameIdMusic(object.getString(Main_post1_Model_music.KEY_ID_MUSIC));

                model.setNameIdPhoto(object.getString(Main_post1_Model_music.KEY_ID_PHOTO));

                model.setLikeMusic(object.getString(Main_post1_Model_music.KEY_LIKE_MUSIC));

                model.setCommentMusic(object.getString(Main_post1_Model_music.KEY_COMMENT_MUSIC));

                model.setViewMusic(object.getString(Main_post1_Model_music.KEY_VIEW_MUSIC));

                model.setId(object.getString(Main_post1_Model_music.KEY_ID));

                model.setTxt_music(object.getString(Main_post1_Model_music.KEY_TXT_MUSIC));
                model.setCategory(object.getString(Main_post1_Model_music.KEY_CATEGORY));

                models.add(model);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return models;
    }

    public static List<Person_model> pars_person_model(String s) {
        List<Person_model> models = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(s);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                Person_model model = new Person_model();
                model.setName(object.getString(Person_model.KEY_NAME));
                model.setField(object.getString(Person_model.KEY_FIELD));
                model.setBio(object.getString(Person_model.KEY_BIO));
                model.setFlower(object.getString(Person_model.KEY_FLOWER));
                model.setLike(object.getString(Person_model.KEY_LIKE));
                model.setView(object.getString(Person_model.KEY_VIEW));
                model.setIdPhoto(object.getString(Person_model.KEY_IDPHOTO));
                model.setId(object.getString(Person_model.KEY_ID));
                models.add(model);
//                {"name":"arashap","field":"music","flower":"1","like":"0","view":"0","bio":"is two brother is one arash is two ap ","idPhoto":"image_arash_ap.jpg"}
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return models;
    }

    public static List<Comment_model> pars_comment(String s) {
        List<Comment_model> models = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(s);
            for (int i = 0; i < array.length(); i++) {
                Comment_model model = new Comment_model();

                JSONObject object = array.getJSONObject(i);
                model.setId(object.getString("id"));
                model.setTxt(object.getString("comment"));
                model.setName(object.getString("name"));
                model.setEmail(object.getString("email"));

                models.add(model);
            }
            //[{"id":"1","comment":"salaaam","name":"amir","email":"amir@gmail.com "},{"id":"1","comment":"","name":"amiradsls","email":"sala@gmail.com"}]
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return models;
    }

    public static List<setting_user> pars_setting_user(String s){
        List<setting_user>models=new ArrayList<>();
        try {
            JSONArray array=new JSONArray(s);
      for (int i=0;i<array.length();i++){
          JSONObject object=array.getJSONObject(i);
          setting_user model=new setting_user();
          model.setName(object.getString("name"));
          model.setEmail(object.getString("email"));
          model.setPass(object.getString("password"));
          model.setNumberPhone(object.getString("numberPhone"));
      models.add(model);
      }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return models;
    }

    public static List<model_flw_user>par_flw_user(String s){
        List<model_flw_user>models=new ArrayList<>();
        try {
            JSONArray array=new JSONArray(s);
       for (int i=0;i<array.length();i++){
           model_flw_user model=new model_flw_user();
           JSONObject object=array.getJSONObject(i);

           model.setId_artist(object.getString("id_artist"));
           model.setId_user(object.getString("id_user"));

           models.add(model);
       }
        } catch (JSONException e) {
            e.printStackTrace();
        }
return models;
    }
}
