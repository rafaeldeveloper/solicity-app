package com.example.gleilson.soliceservices;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.gleilson.soliceservices.dao.ProfileDAO;
import com.example.gleilson.soliceservices.model.Profile;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    private TextView txtSite;
    private TextView txtPhone;
    private TextView txtName;
    private TextView txtEmail;
    private Profile profile;
    private CircleImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_profile, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulario_ok:

                Intent intent = new Intent(ProfileActivity.this, ProfileFormActivity.class);
                intent.putExtra("profile", this.profile);

                startActivity(intent);

                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadProfile();
    }

    private void loadProfile() {
        ProfileDAO dao = new ProfileDAO(this);

        String token = SharedPreferencesUser.getToken(this);
        profile = dao.get(token);

        txtEmail = (TextView) findViewById(R.id.activity_profile_email);
        txtName = (TextView) findViewById(R.id.activity_profile_name);
        txtPhone = (TextView) findViewById(R.id.activity_profile_phone);
        txtSite = (TextView) findViewById(R.id.activity_profile_site);
        image = (CircleImageView) findViewById(R.id.activity_profile_image);

        txtEmail.setText(profile.getEmail());
        txtName.setText(profile.getFirstName() + " " + profile.getLastName());
        txtPhone.setText(profile.getPhone() != null ? profile.getPhone() : "Nenhum telefone cadastrado");
        txtSite.setText(profile.getSite() != null ? profile.getSite() : "Nenhum site cadastrado");

        String urlImage = profile.getUrlImage();
        if (urlImage != null) {
//            Bitmap bitmap = BitmapFactory.decodeFile(urlImage);
//            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
//
//            image.setImageBitmap(bitmapReduzido);
//            image.setTag(urlImage);
        }
    }

}
