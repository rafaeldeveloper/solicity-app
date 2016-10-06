package com.example.gleilson.soliceservices;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gleilson.soliceservices.adapter.CategoriesAdapter;
import com.example.gleilson.soliceservices.adapter.UsersAdapter;
import com.example.gleilson.soliceservices.model.Category;
import com.example.gleilson.soliceservices.model.User;

import java.util.ArrayList;
import java.util.List;

public class ListProfessionalsActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_category);

        final List<User> users;
        UsersAdapter adapter;

        users = new ArrayList<User>();

        String url = "http://lorempixel.com/200/200";

        users.add(new User("Ferreira", "Gleilson", "fgleilsonf@gmail.com", url));
        users.add(new User("Gleilson", "Ferreira", "fgleilsonf@gmail.com", url));
        users.add(new User("Gleilson", "Ferreira", "fgleilsonf@gmail.com", url));
        users.add(new User("Gleilson", "Ferreira", "fgleilsonf@gmail.com", url));
        users.add(new User("Gleilson", "Ferreira", "fgleilsonf@gmail.com", url));
        users.add(new User("Gleilson", "Ferreira", "fgleilsonf@gmail.com", url));
        users.add(new User("Gleilson", "Ferreira", "fgleilsonf@gmail.com", url));

        adapter = new UsersAdapter(this, users);

        listView = (ListView) findViewById(R.id.list);

        final int PADDING = 0;

        TextView txtHeader = new TextView(this);
        txtHeader.setBackgroundColor(Color.GRAY);
        txtHeader.setTextColor(Color.WHITE);
        txtHeader.setText(R.string.text_header_professionals);
        txtHeader.setPadding(16, PADDING, 0, PADDING);

        listView.addHeaderView(txtHeader);

        TextView txtFooter = new TextView(this);
        txtFooter.setText(getResources().getQuantityString(R.plurals.text_footer_professionals, adapter.getCount(), adapter.getCount()));
        txtFooter.setBackgroundColor(Color.LTGRAY);
        txtFooter.setGravity(Gravity.RIGHT);
        txtFooter.setPadding(0, PADDING, 16, PADDING);

        listView.addFooterView(txtFooter);

        listView.setAdapter(adapter);

        registerForContextMenu(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                User user = (User) adapterView.getItemAtPosition(i);

                if (user != null) {
                    Toast.makeText(ListProfessionalsActivity.this, user.getFirstName() + " " + user.getLastName(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final User user = (User) listView.getItemAtPosition(info.position);

        MenuItem itemMapa = menu.add("Visualizar no mapa");
        Intent intentMapa = new Intent(Intent.ACTION_VIEW);
        itemMapa.setIntent(intentMapa);
        intentMapa.setData(Uri.parse("geo:0,0?q=" + "Rua capitão antônio aguiar, 55"));

        MenuItem itemSite = menu.add("Visitar site");
        Intent intentSite = new Intent(Intent.ACTION_VIEW);

        String site = user.getUrlImage();
        if (!site.startsWith("http://")) {
            site = "http://" + site;
        }

        intentSite.setData(Uri.parse(site));
        itemSite.setIntent(intentSite);

        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(ListProfessionalsActivity.this, "Deletar o user " + user.getFirstName(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
