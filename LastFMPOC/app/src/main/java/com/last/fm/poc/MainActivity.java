package com.last.fm.poc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.last.fm.poc.datamodels.SearchData;
import com.last.fm.poc.listeners.OnItemClickListener;
import com.last.fm.poc.network.FetchSearchDataTask;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final OnItemClickListener ItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(SearchData itemObj) {
            if (itemObj != null) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);

                intent.putExtra(AppConstants.ITEM_PARCEL_KEY, itemObj);

                MainActivity.this.startActivity(intent);
            }
        }
    };

    ListAdapter adapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        RecyclerView mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        /*
         * defining and attaching the Adapter to RecyclerView
         */
        adapter = new ListAdapter(ItemClickListener);
        mRecyclerView.setAdapter(adapter);

        performSearch("a");
    }

    private void performSearch(String searchString) {
        new FetchSearchDataTask(adapter).execute(searchString);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        searchView = (SearchView) menu.findItem(R.id.mi_search).getActionView();
        searchView.setFocusable(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


            @Override
            public boolean onQueryTextSubmit(String s) {
                performSearch(s);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.search_by_album:
                AppConfigurations.setSearchType(AppConfigurations.ALBUM);
                break;
            case R.id.search_by_artist:
                AppConfigurations.setSearchType(AppConfigurations.ARTIST);
                break;
            case R.id.search_by_track:
                AppConfigurations.setSearchType(AppConfigurations.TRACK);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        String searchtext = searchView.getQuery().toString();
        if (searchtext == null || searchtext.isEmpty())
            searchtext = "a";
        performSearch(searchtext);
        return true;
    }
}
