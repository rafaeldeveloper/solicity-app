package com.example.gleilson.soliceservices;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.SingleLineTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gleilson.soliceservices.dao.ProfileDAO;
import com.example.gleilson.soliceservices.tasks.CreateAccountTask;
import com.example.gleilson.soliceservices.tasks.LoginTask;

public class SignInActivity extends AppCompatActivity {

    private TextView btnForgotPassword;
    private Button btnLogin;
    private EditText edtEmail;
    private EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ProfileDAO dao = new ProfileDAO(this);
        dao.get("asdasdas");

        btnForgotPassword = (TextView) findViewById(R.id.activity_sign_in_action_forgot_password);
        btnLogin = (Button) findViewById(R.id.activity_sign_in_action_login);

        edtEmail = (EditText) findViewById(R.id.activity_sign_in_edt_email);
        edtPassword = (EditText) findViewById(R.id.activity_sign_in_edt_password);

        btnForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = edtPassword.getText().toString();
                String email = edtEmail.getText().toString();

                new LoginTask(SignInActivity.this, email, password).execute();
            }
        });

//        SharedPreferences sp = getSharedPreferences("Profile", 0);
//        SharedPreferences.Editor Ed = sp.edit();
//        Ed.putString("email", Value);
//        Ed.putString("password", Value);
//        Ed.commit();
    }
}