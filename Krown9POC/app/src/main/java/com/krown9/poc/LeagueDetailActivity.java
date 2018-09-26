package com.krown9.poc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.krown9.poc.constants.AppConstants;
import com.krown9.poc.model.LeagueDetail;
import com.krown9.poc.utils.FetchDataTask;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

public class LeagueDetailActivity extends AppCompatActivity {

    private TextView mDetailsView;
    private LeagueDetail mLeagueDetail;
    private ImageView headerImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        String leagueID = null;
        String leagueName = null;
        if (extras != null) {
            leagueID = extras.getString(AppConstants.LEAGUE_ID_KEY);
            leagueName = extras.getString(AppConstants.LEAGUE_NAME_KEY);
        }

        View content = findViewById(R.id.league_detail_layout);
        mDetailsView = content.findViewById(R.id.description_view);

        headerImageView = findViewById(R.id.league_website_icon);

        if (getSupportActionBar() != null) {
            if (leagueName != null)
                getSupportActionBar().setTitle(leagueName);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Leagues website for More Details", Snackbar.LENGTH_LONG)
                        .setAction("Open Website", webLinkListener).show();
            }
        });

        if (leagueID != null)
            new FetchDataTask(this).execute(AppConstants.LEAGUE_DETAILS_BY_ID + leagueID);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void parseJSON(String responseJson) {
        try {
            JSONObject jsonObj = new JSONObject(responseJson);

            // Getting JSON Array node
            JSONArray leagues = jsonObj.getJSONArray("leagues");

            for (int i = 0; i < leagues.length(); i++) {
                mLeagueDetail = new Gson().fromJson(String.valueOf(leagues.get(i)), LeagueDetail.class);
                mDetailsView.setText(mLeagueDetail.getStrDescriptionEN());

                String bannerURL = mLeagueDetail.getStrFanart1();
                if (bannerURL == null && mLeagueDetail.getStrFanart2() != null)
                    bannerURL = mLeagueDetail.getStrFanart2();

                if (bannerURL == null && mLeagueDetail.getStrFanart3() != null)
                    bannerURL = mLeagueDetail.getStrFanart3();

                if (bannerURL == null && mLeagueDetail.getStrFanart4() != null)
                    bannerURL = mLeagueDetail.getStrFanart4();

                if (bannerURL != null)
                    Picasso.with(this).load(bannerURL).into(headerImageView);
            }

        } catch (Exception e) {
            Log.e("Leagues", "Error parsing data" + e.getMessage());
        }
    }

    /**
     * Listener to open Leagues website on FAB Click
     */
    private final View.OnClickListener webLinkListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String url = mLeagueDetail.getStrWebsite();
            if (url == null)
                return;

            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "http://" + url;
            }
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        }
    };
}
