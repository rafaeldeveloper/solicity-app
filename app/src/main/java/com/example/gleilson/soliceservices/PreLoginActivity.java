package com.example.gleilson.soliceservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PreLoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_login);

//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);

        btnLogin = (Button) findViewById(R.id.activity_pre_login_action_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PreLoginActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

        btnSignUp = (Button) findViewById(R.id.activity_pre_login_action_signup);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PreLoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("TESTE", "Erro");

        logged();
    }

    private void logged() {
        String token = SharedPreferencesUser.getToken(this);

        if (token != null) {
            Intent intent = new Intent(PreLoginActivity.this, MainActivity.class);
            startActivity(intent);

            finish();
        } else {

        }
    }
}