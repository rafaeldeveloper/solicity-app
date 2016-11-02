package com.example.gleilson.soliceservices.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gleilson.soliceservices.MainActivity;
import com.example.gleilson.soliceservices.Server;
import com.example.gleilson.soliceservices.SharedPreferencesUser;
import com.example.gleilson.soliceservices.WebClient;
import com.example.gleilson.soliceservices.adapter.CategoriesAdapter;
import com.example.gleilson.soliceservices.converter.LoginConverter;
import com.example.gleilson.soliceservices.dao.CategoryDAO;
import com.example.gleilson.soliceservices.dao.ProfileDAO;
import com.example.gleilson.soliceservices.model.Category;
import com.example.gleilson.soliceservices.model.Profile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListCategoriesTask extends AsyncTask<Void, Void, JSONArray> {

    private Context context;
    private ProgressDialog dialog;

    public ListCategoriesTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(context, "Aguarde", "Carregando categorias...", true, true);
    }

    @Override
    protected JSONArray doInBackground(Void... params) {
//        try {
//
//            String token = SharedPreferencesUser.getToken(this.context);
//
//            String url = Server.API_URL_CATEGORIES + "?authentication_token=" + token;
//            WebClient client = new WebClient(url);
//
////            return client.get();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        return null;
    }

    @Override
    protected void onPostExecute(JSONArray jsonResponse) {
        dialog.dismiss();

        try {
            for(int i = 0; i < jsonResponse.length(); ++i) {
                JSONObject jsonObject = jsonResponse.getJSONObject(i);

                Category category = new Category();
                category.setName(jsonObject.getString("name"));
                category.setQty(jsonObject.getInt("amount_users"));
                category.setUrlImage(jsonObject.getString("url_image"));

                CategoryDAO dao = new CategoryDAO(context);
                dao.insert(category);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}