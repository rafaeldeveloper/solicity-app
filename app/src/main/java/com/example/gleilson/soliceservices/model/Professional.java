package com.example.gleilson.soliceservices.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by gleilson on 18/09/16.
 */
public class Professional extends User implements Serializable {

    private int categories[];

    public Professional() {}

    public Professional(JSONObject userJson) throws JSONException {
        this.setId(userJson.getLong("id"));
        this.setEmail(userJson.getString("email"));
        this.setLastName(userJson.getString("last_name"));
        this.setFirstName(userJson.getString("first_name"));
        this.setPhone(userJson.getString("phone"));
        this.setSite(userJson.getString("site"));
        this.setUrlImage(userJson.getString("url_image"));
    }

    public int[] getCategories() {
        return categories;
    }

    public void setCategories(int[] categories) {
        this.categories = categories;
    }
}
