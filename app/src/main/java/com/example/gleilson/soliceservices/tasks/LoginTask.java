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
import com.example.gleilson.soliceservices.WebClient;
import com.example.gleilson.soliceservices.converter.LoginConverter;
import com.example.gleilson.soliceservices.converter.ProfileConverter;
import com.example.gleilson.soliceservices.dao.ProfileDAO;
import com.example.gleilson.soliceservices.model.Profile;

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
            WebClient client = new WebClient(Server.API_URL_LOGIN);
            return client.post(json);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(JSONObject jsonResponse) {
        dialog.dismiss();

        try {
            if (jsonResponse.getString("authentication_token") != null || jsonResponse.getBoolean("success")) {
                Toast.makeText(context, "Conta criada com sucesso!", Toast.LENGTH_LONG).show();

                String token = jsonResponse.getString("authentication_token");

                Log.d("LOGON", token);

                SharedPreferencesUser.saveProfile(context, token);

//                ProfileDAO dao = new ProfileDAO(this.context);
//                dao.insert(profile);
//
//                Intent intent = new Intent(context, MainActivity.class);
//                intent.putExtra("profile", profile);

//                context.startActivity(intent);
            } else {
                Toast.makeText(context, "Falha ao criar a conta!", Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}