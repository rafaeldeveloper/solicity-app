package com.example.gleilson.soliceservices;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.gleilson.soliceservices.model.Profile;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by gleilson on 15/09/16.
 */
public class FormProfileHelper {
    private final EditText edtFirstName;
    private final EditText edtLastName;
    private final EditText edtEmail;
    private final CircleImageView foto;
    private final EditText edtSite;

    private Profile user;

    public FormProfileHelper(ProfileFormActivity activity)  {
        edtFirstName = (EditText) activity.findViewById(R.id.profile_firstname);
        edtLastName = (EditText) activity.findViewById(R.id.profile_lastname);
        edtEmail = (EditText) activity.findViewById(R.id.profile_email);
        edtSite = (EditText) activity.findViewById(R.id.profile_site);
        foto = (CircleImageView) activity.findViewById(R.id.profile_foto);

        user = new Profile();
    }

    public Profile getUser() {
        user.setSite(edtSite.getText().toString());
        user.setEmail(edtEmail.getText().toString());
        user.setFirstName(edtFirstName.getText().toString());
        user.setLastName(edtLastName.getText().toString());
        user.setUrlImage((String) foto.getTag());

        return this.user;
    }

    public void setForm(Profile user) {
        edtFirstName.setText(user.getFirstName());
        edtLastName.setText(user.getLastName());
        edtEmail.setText(user.getEmail());
        edtSite.setText(user.getSite());
        loadImage(user.getUrlImage());

        this.user = user;
    }

    public void loadImage(String pathImage) {
        if (pathImage != null) {

            Log.d("pathImage", pathImage);

//            Bitmap bitmap = BitmapFactory.decodeFile(pathImage);
//            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 300, 300, true);

//            foto.setImageBitmap(bitmapReduzido);
            foto.setTag(pathImage);
        }
    }
}
