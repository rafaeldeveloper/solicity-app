package com.example.gleilson.soliceservices;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gleilson.soliceservices.model.Category;
import com.example.gleilson.soliceservices.model.Professional;
import com.example.gleilson.soliceservices.model.Profile;
import com.squareup.picasso.Picasso;

public class ProfessionalActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button btnRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professional);

        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar .setTitle("Profissional");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        final Professional professional = (Professional) intent.getSerializableExtra("professional");
        Category category = (Category) intent.getSerializableExtra("category");

        TextView txtPhone = (TextView) findViewById(R.id.activity_profile_phone);

        txtPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (ActivityCompat.checkSelfPermission(ProfessionalActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions();
//                } else {
                    Intent intentLigar = new Intent(Intent.ACTION_CALL);
                    intentLigar.setData(Uri.parse("tel:" + professional.getPhone()));

                    startActivity(intentLigar);
//                }
            }
        });

        btnRequest = (Button) findViewById(R.id.activity_professional_request);
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gerarNotificacao();
            }
        });

        TextView txtCategory = (TextView) findViewById(R.id.activity_profile_category);
        txtCategory.setText("Categoria: " + category.getName());

        ImageView image = (ImageView) findViewById(R.id.activity_professional_image);

        Picasso.with(this)
            .load(professional.getUrlImage())
            .placeholder(R.drawable.perfil)
            .into(image);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void gerarNotificacao(){

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        PendingIntent p = PendingIntent.getActivity(this, 0, new Intent(this, ListCategoryActivity.class), 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setTicker("Ticker Texto");
        builder.setContentTitle("Enviamos a solicitação para o profissional.");

        //builder.setContentText("Descrição");

        builder.setSmallIcon(R.drawable.ic_camera);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.perfil));
        builder.setContentIntent(p);

        NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
        String [] descs = new String[]{ "Aguarde" };
        for(int i = 0; i < descs.length; i++){
            style.addLine(descs[i]);
        }
        builder.setStyle(style);

        Notification n = builder.build();
        n.vibrate = new long[]{150, 300, 150, 600};
        n.flags = Notification.FLAG_AUTO_CANCEL;
        nm.notify(R.drawable.ic_camera, n);

        try {
            Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone toque = RingtoneManager.getRingtone(this, som);
            toque.play();
        }
        catch(Exception e){}
    }

//    @Override
//    public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantTesults) {
//        super.onRequestPermissionResult(requestCode, permissions, grantResults);
//
//        if (requestCode == 123) {
//            // faz a ligacao
//        }
//    }
}
