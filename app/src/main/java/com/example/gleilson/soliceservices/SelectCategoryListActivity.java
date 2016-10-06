package com.example.gleilson.soliceservices;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;

public class SelectCategoryListActivity extends ListActivity {

    public static final String EXTRA_CATEGORIA = "categoria";
    public static final String EXTRA_RESULTADO = "result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] categorias = getResources().getStringArray(R.array.categorias);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, categorias));

        String categoria = getIntent().getStringExtra(EXTRA_CATEGORIA);
        if (categoria != null) {
            int position = Arrays.asList(categorias).indexOf(categoria);
            getListView().setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
            getListView().setItemChecked(position, true);
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String result = l.getItemAtPosition(position).toString();
        Intent intent = new Intent();
        intent.putExtra(EXTRA_RESULTADO, result);
        setResult(RESULT_OK, intent);

        finish();
    }
}
