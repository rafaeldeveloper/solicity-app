package com.example.gleilson.soliceservices;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gleilson.soliceservices.adapter.CategoriesAdapter;
import com.example.gleilson.soliceservices.model.Category;

import java.util.ArrayList;
import java.util.List;

public class ListCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_category);

        final List<Category> categories;
        CategoriesAdapter adapter;

        ListView listView;

        categories = new ArrayList<Category>();

        String url = "http://lorempixel.com/400/200/sports";

        categories.add(new Category("Eletricista", url + "/1/", 10));
        categories.add(new Category("Encanador", url + "/2/", 12));
        categories.add(new Category("Professor", url + "/3/", 14));
        categories.add(new Category("Transportador", url + "/4/", 16));

        adapter = new CategoriesAdapter(this, categories);

        listView = (ListView) findViewById(R.id.list);

        final int PADDING = 0;

        TextView txtHeader = new TextView(this);
        txtHeader.setBackgroundColor(Color.GRAY);
        txtHeader.setTextColor(Color.WHITE);
        txtHeader.setText(R.string.texto_cabecalho);
        txtHeader.setPadding(16, PADDING, 0, PADDING);

        listView.addHeaderView(txtHeader);

        TextView txtFooter = new TextView(this);
        txtFooter.setText(getResources().getQuantityString(R.plurals.texto_rodape, adapter.getCount(), adapter.getCount()));
        txtFooter.setBackgroundColor(Color.LTGRAY);
        txtFooter.setGravity(Gravity.RIGHT);
        txtFooter.setPadding(0, PADDING, 16, PADDING);

        listView.addFooterView(txtFooter);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Category category = (Category) adapterView.getItemAtPosition(i);

                if (category != null) {
                    Intent intent = new Intent(ListCategoryActivity.this, ListProfessionalsActivity.class);
                    startActivity(intent);

                    Toast.makeText(ListCategoryActivity.this, category.getName() + " " + category.getQty(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
