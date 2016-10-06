package com.example.gleilson.soliceservices;

import android.app.ProgressDialog;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gleilson.soliceservices.model.Profile;
import com.example.gleilson.soliceservices.tasks.CreateAccountTask;

import com.*;

public class SignUpActivity extends AppCompatActivity {

    private EditText edtFirstName;
    private EditText edtLastName;
    private EditText edtEmail;
    private EditText edtPhone;
    private EditText edtPassword;
    private Button btnCreateAccount;
    private ProgressDialog diolog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtFirstName = (EditText) findViewById(R.id.activity_sign_up_edt_first_name);
        edtLastName = (EditText) findViewById(R.id.activity_sign_up_edt_last_name);
        edtEmail = (EditText) findViewById(R.id.activity_sign_up_edt_email);
        edtPhone = (EditText) findViewById(R.id.activity_sign_up_edt_phone);
        edtPassword = (EditText) findViewById(R.id.activity_sign_up_edt_password);

        btnCreateAccount = (Button) findViewById(R.id.activity_sign_up_btn_create_account);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean valid = validForm();

                if (valid) {
                    Profile profile = new Profile();
                    profile.setFirstName(edtFirstName.getText().toString());
                    profile.setLastName(edtLastName.getText().toString());
                    profile.setEmail(edtEmail.getText().toString());
                    profile.setPhone(edtPhone.getText().toString());
                    profile.setPassword(edtPassword.getText().toString());

                    new CreateAccountTask(SignUpActivity.this, profile).execute();
                }
            }
        });
    }

    private boolean validForm() {
        boolean valid = true;

        if (edtFirstName.getText().toString().matches("")) {
            edtFirstName.setError(getString(R.string.msg_error_field_required));
            valid = false;
        }

        if (edtLastName.getText().toString().matches("")) {
            edtLastName.setError(getString(R.string.msg_error_field_required));
            valid = false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(edtEmail.getText()).matches()) {
            edtEmail.setError(getString(R.string.msg_error_email_invalid));
            valid = false;
        }

        return valid;
    }
}
