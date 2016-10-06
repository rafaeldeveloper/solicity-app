package com.example.gleilson.soliceservices.converter;

import com.example.gleilson.soliceservices.model.Profile;

import org.json.JSONException;
import org.json.JSONStringer;

public class LoginConverter {

    public String post(String email, String password) {
        JSONStringer json = new JSONStringer();

        try {
            json.object();

            json.key("email").value(email);
            json.key("password").value(password);

            json.endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json.toString();
    }
}
