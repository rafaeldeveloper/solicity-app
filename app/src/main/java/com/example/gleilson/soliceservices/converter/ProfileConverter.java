package com.example.gleilson.soliceservices.converter;

import com.example.gleilson.soliceservices.model.Profile;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

public class ProfileConverter {

    public String post(Profile profile) {
        JSONStringer json = new JSONStringer();

        try {
            json.object();

            json.key("email").value(profile.getEmail());
            json.key("firstname").value(profile.getFirstName());
            json.key("lastname").value(profile.getLastName());
            json.key("phone").value(profile.getPhone());
            json.key("site").value(profile.getSite());
            json.key("password").value(profile.getPassword());

            json.endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json.toString();
    }
}
