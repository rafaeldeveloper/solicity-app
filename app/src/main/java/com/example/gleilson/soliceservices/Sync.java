package com.example.gleilson.soliceservices;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.gleilson.soliceservices.dao.CategoryDAO;
import com.example.gleilson.soliceservices.dao.ProfessionalDAO;
import com.example.gleilson.soliceservices.dao.ProfileDAO;
import com.example.gleilson.soliceservices.model.Category;
import com.example.gleilson.soliceservices.model.Professional;
import com.example.gleilson.soliceservices.model.Profile;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by gleilson on 22/10/16.
 */

public class Sync extends FirebaseInstanceIdService {

    static String[] resources = {"profile", "categories", "professionals"};

    public static boolean process(JSONObject jsonResources, Context context) {

        try {
            for (String resource: resources) {
                switch (resource) {
                    case "profile": {
                        JSONObject jsonProfile = jsonResources.getJSONObject(resource);
                        saveProfile(jsonProfile, context);

                        break;
                    }
                    case "categories": {
                        JSONArray jsonCategories = jsonResources.getJSONArray(resource);
                        saveCategories(jsonCategories, context);

                        break;
                    }
                    case "professionals": {
                        JSONArray jsonProfessionals = jsonResources.getJSONArray(resource);
                        saveProfessionals(jsonProfessionals, context);

                        break;
                    }
                }
            }

            return true;
        } catch (JSONException e) {
            e.printStackTrace();

            return false;
        }
    }

    public static void saveProfile(JSONObject jsonProfile, Context context) {
        try {
            Profile profile = new Profile(jsonProfile);

            ProfileDAO dao = new ProfileDAO(context);
            dao.insert(profile);

            SharedPreferencesUser.saveProfile(context, profile.getToken());

            dao.close();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void saveCategories(JSONArray jsonArray, Context context) {
        try {
            for(int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonObject = null;

                jsonObject = jsonArray.getJSONObject(i);

                Category category = new Category();
                category.setName(jsonObject.getString("name"));

                int qty = 0;
                if (jsonObject.has("amount_users")) {
                    qty = jsonObject.getInt("amount_users");
                }

                category.setQty(qty);
                category.setUrlImage(jsonObject.getString("url_image"));

                CategoryDAO dao = new CategoryDAO(context);
                dao.insert(category);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void saveProfessionals(JSONArray jsonArray, Context context) {
        try {
            for(int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonObject = null;

                jsonObject = jsonArray.getJSONObject(i);

                Professional professional = new Professional(jsonObject);
                ProfessionalDAO dao = new ProfessionalDAO(context);
                dao.insert(professional);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
//        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
//        Log.d("AAAAAAAAAAAAA", "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
//        sendRegistrationToServer(refreshedToken);
    }

}
