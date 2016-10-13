package com.example.gleilson.soliceservices.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.gleilson.soliceservices.MainActivity;
import com.example.gleilson.soliceservices.Server;
import com.example.gleilson.soliceservices.SharedPreferencesUser;
import com.example.gleilson.soliceservices.WebClient;
import com.example.gleilson.soliceservices.converter.LoginConverter;
import com.example.gleilson.soliceservices.dao.ProfileDAO;
import com.example.gleilson.soliceservices.model.Profile;

import org.json.JSONException;
import org.json.JSONObject;

public class ListCategoriesTask extends AsyncTask<Void, Void, JSONObject> {

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
    protected JSONObject doInBackground(Void... params) {
        try {
            WebClient client = new WebClient(Server.API_URL_CATEGORIES);

            return client.get();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(JSONObject jsonResponse) {
        dialog.dismiss();

        try {
            boolean success = jsonResponse.getBoolean("success");

            if (success) {
                JSONObject userJson = jsonResponse.getJSONObject("user");

                Profile profile = new Profile(userJson);

                SharedPreferencesUser.saveProfile(context, profile.getToken());

                ProfileDAO dao = new ProfileDAO(this.context);
                dao.insert(profile);

                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("profile", profile);

                context.startActivity(intent);
            } else {
                JSONObject userJson = jsonResponse.getJSONObject("user");
                Toast.makeText(context, "Falha ao criar a conta!", Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}