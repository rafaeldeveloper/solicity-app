package com.example.gleilson.soliceservices.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.gleilson.soliceservices.MainActivity;
import com.example.gleilson.soliceservices.Server;
import com.example.gleilson.soliceservices.SharedPreferencesUser;
import com.example.gleilson.soliceservices.Sync;
import com.example.gleilson.soliceservices.WebClient;
import com.example.gleilson.soliceservices.converter.LoginConverter;
import com.example.gleilson.soliceservices.converter.ProfileConverter;
import com.example.gleilson.soliceservices.dao.ProfileDAO;
import com.example.gleilson.soliceservices.model.Profile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginTask extends AsyncTask<Void, Void, JSONObject> {

    private Context context;
    private ProgressDialog dialog;
    private String email;
    private String password;

    public LoginTask(Context context, String email, String password) {
        this.context = context;
        this.email = email;
        this.password = password;
    }

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(context, "Aguarde", "Verficando usuário...", true, true);
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        LoginConverter conversor = new LoginConverter();
        String json = conversor.post(this.email, this.password);

        try {
            WebClient clientLogin = new WebClient(Server.API_URL_LOGIN);
            JSONObject jsonResponse = clientLogin.post(json);

            if (jsonResponse.has("success") && jsonResponse.getBoolean("success")) {
                JSONObject userJson = jsonResponse.getJSONObject("user");

                String token = userJson.getString("authentication_token");
                String url = Server.API_URL_SYNC + "?authentication_token=" + token;

                SharedPreferencesUser.saveProfile(context, token);

                Log.d("URL LOGIN", url);

                WebClient clientSync = new WebClient(url);
                JSONObject jsonResponseSync = clientSync.get();

                Sync.process(jsonResponseSync, context);
            }

            return jsonResponse;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected void onPostExecute(JSONObject jsonResponse) {
        dialog.dismiss();

        try {
            String msg = jsonResponse.has("message") ? jsonResponse.getString("message") : "";
            if (jsonResponse.getBoolean("success")) {
                ProfileDAO dao = new ProfileDAO(context);
                Profile profile = dao.get("");

                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("profile", profile);

                Toast.makeText(context, "Conta criada com sucesso!", Toast.LENGTH_LONG).show();

                context.startActivity(intent);
            } else {
                Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}