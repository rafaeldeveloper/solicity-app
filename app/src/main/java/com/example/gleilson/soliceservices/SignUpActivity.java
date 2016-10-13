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

import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

public class SignUpActivity extends AppCompatActivity implements Validator.ValidationListener {

    @NotEmpty
    private EditText edtFirstName;

    private EditText edtLastName;

    @Email
    private EditText edtEmail;

    @NotEmpty
    private EditText edtPhone;

    @NotEmpty
    private EditText edtPassword;
    private Button btnCreateAccount;
    private ProgressDialog diolog;
    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        validator = new Validator(this);
        validator.setValidationListener(this);


        edtFirstName = (EditText) findViewById(R.id.activity_sign_up_edt_first_name);
        edtLastName = (EditText) findViewById(R.id.activity_sign_up_edt_last_name);
        edtEmail = (EditText) findViewById(R.id.activity_sign_up_edt_email);
        edtPhone = (EditText) findViewById(R.id.activity_sign_up_edt_phone);
        edtPassword = (EditText) findViewById(R.id.activity_sign_up_edt_password);

        btnCreateAccount = (Button) findViewById(R.id.activity_sign_up_btn_create_account);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validator.validate();

                boolean valid = true; //

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

    @Override
    public void onValidationSucceeded() {
        Toast.makeText(this, "Yay! we got it right!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
