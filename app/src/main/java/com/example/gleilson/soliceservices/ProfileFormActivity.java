package com.example.gleilson.soliceservices;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gleilson.soliceservices.dao.ProfileDAO;
import com.example.gleilson.soliceservices.model.Profile;

import java.io.File;

public class ProfileFormActivity extends AppCompatActivity {

    private FormProfileHelper helper;
    public static final int CODIGO_CAMERA = 567;
    private String caminhoFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_form);

        helper = new FormProfileHelper(this);

        Intent intent = getIntent();
        Profile user = (Profile) intent.getSerializableExtra("profile");
        if (user != null) {
            helper.setForm(user);
        }

        Button btnFoto = (Button) findViewById(R.id.profile_btn_foto);
        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                caminhoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                File arquivoFoto = new File(caminhoFoto);
                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(arquivoFoto));
                startActivityForResult(intentCamera, CODIGO_CAMERA);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulario_ok:

                Profile user = helper.getUser();
                Toast.makeText(ProfileFormActivity.this, "Informações salvas com sucesso!", Toast.LENGTH_SHORT).show();

                ProfileDAO dao = new ProfileDAO(this);

                if (user.getId() != null) {
                    dao.update(user);
                } else {
                    dao.insert(user);
                }

                dao.close();

                finish();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CODIGO_CAMERA) {
                helper.loadImage(caminhoFoto);
            }
        }
    }
}