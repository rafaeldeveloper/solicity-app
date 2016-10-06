package com.example.gleilson.soliceservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SearchServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_CATEGORY = 1;
    private static final String STATE_CATEGORY = "category";

    private Button btnSelectCategory;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_service);

        btnSelectCategory = (Button) findViewById(R.id.btnSelectCategory);
        btnSelectCategory.setOnClickListener(this);

        if (savedInstanceState != null) {
            category = savedInstanceState.getString(STATE_CATEGORY);
            btnSelectCategory.setText(category);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_CATEGORY, category);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(SearchServiceActivity.this, SelectCategoryListActivity.class);
        intent.putExtra(SelectCategoryListActivity.EXTRA_CATEGORIA, category);

        startActivityForResult(intent, REQUEST_CATEGORY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUEST_CATEGORY) {
            category = data.getStringExtra(SelectCategoryListActivity.EXTRA_RESULTADO);
            if (category != null) {
                btnSelectCategory.setText(category);
            }
        }
    }
}
