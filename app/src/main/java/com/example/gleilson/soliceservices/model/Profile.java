package com.example.gleilson.soliceservices.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by gleilson on 18/09/16.
 */
public class Profile extends User implements Serializable {

    private String password;
    private String token;

    public Profile() {}

    public Profile(JSONObject userJson) throws JSONException {
        this.setId(userJson.getLong("id"));
        this.setEmail(userJson.getString("email"));
        this.setLastName(userJson.getString("last_name"));
        this.setFirstName(userJson.getString("first_name"));
        this.setToken(userJson.getString("authentication_token"));
        this.setCpf(userJson.getString("authentication_token"));
        this.setPhone(userJson.getString("phone"));
        this.setSite(userJson.getString("site"));
        this.setUrlImage(userJson.getString("url_image"));
        this.setCreatedAt(userJson.getString("created_at"));
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
