package com.example.gleilson.soliceservices;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.provider.SearchRecentSuggestions;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gleilson.soliceservices.adapter.CategoriesAdapter;
import com.example.gleilson.soliceservices.dao.CategoryDAO;
import com.example.gleilson.soliceservices.model.Category;
import com.example.gleilson.soliceservices.provider.SearchableProvider;
import com.example.gleilson.soliceservices.tasks.ListCategoriesTask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.gleilson.soliceservices.R.id.toolbar;

public class ListCategoryActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private List<Category> categories;
    private List<Category> mListAux;
    private CategoriesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_category);

        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar .setTitle("Lista de Categorias");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView listView;

        CategoryDAO dao = new CategoryDAO(this);

        categories = dao.list();
        mListAux = new ArrayList<Category>(categories);

        adapter = new CategoriesAdapter(this, mListAux);

        listView = (ListView) findViewById(R.id.list);

        TextView txtFooter = new TextView(this);
        txtFooter.setText(getResources().getQuantityString(R.plurals.texto_rodape, adapter.getCount(), adapter.getCount()));
        txtFooter.setBackgroundColor(Color.LTGRAY);
        txtFooter.setGravity(Gravity.RIGHT);

        listView.addFooterView(txtFooter);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Category category = (Category) adapterView.getItemAtPosition(i);

                if (category != null) {
                    Intent intent = new Intent(ListCategoryActivity.this, ListProfessionalsActivity.class);

                    intent.putExtra("category", category);

                    startActivity(intent);

                    Toast.makeText(ListCategoryActivity.this, category.getName() + " " + category.getQty(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        hendleSearch(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent( intent );
        hendleSearch( intent );
    }

    public void hendleSearch( Intent intent ) {
        if( Intent.ACTION_SEARCH.equalsIgnoreCase( intent.getAction() ) ){
            String q = intent.getStringExtra( SearchManager.QUERY );

            toolbar.setTitle(q);
            filterCars( q );

            SearchRecentSuggestions searchRecentSuggestions = new SearchRecentSuggestions(this,
                    SearchableProvider.AUTHORITY,
                    SearchableProvider.MODE);
            searchRecentSuggestions.saveRecentQuery( q, null );
        }
    }

    public void filterCars( String q ){
        mListAux.clear();

        for (int i = 0, tamI = categories.size(); i < tamI; i++) {

            String a = categories.get(i).getName().toLowerCase();
            Log.d("categories", a);

            if (categories.get(i).getName().toLowerCase().startsWith(q.toLowerCase())) {
                mListAux.add( categories.get(i) );
            }
        }

//        mRecyclerView.setVisibility( mListAux.isEmpty() ? View.GONE : View.VISIBLE);
//        if( mListAux.isEmpty() ){
//            TextView tv = new TextView( this );
//            tv.setText( "Nenhum carro encontrado." );
//            tv.setTextColor( getResources().getColor( R.color.colorPrimarytext ) );
//            tv.setId( 1 );
//            tv.setLayoutParams( new FrameLayout.LayoutParams( FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT )  );
//            tv.setGravity(Gravity.CENTER);
//
//            clContainer.addView( tv );
//        }
//        else if( clContainer.findViewById(1) != null ) {
//            clContainer.removeView( clContainer.findViewById(1) );
//        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView;
        MenuItem item = menu.findItem(R.id.action_searchable_activity);

        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ){
            searchView = (SearchView) item.getActionView();
        } else {
            searchView = (SearchView) MenuItemCompat.getActionView( item );
        }

        searchView.setSearchableInfo( searchManager.getSearchableInfo( getComponentName() ) );
        searchView.setQueryHint( getResources().getString(R.string.search_hint) );

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
