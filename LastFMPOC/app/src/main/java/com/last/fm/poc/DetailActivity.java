package com.last.fm.poc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.last.fm.poc.datamodels.SearchData;
import com.last.fm.poc.utils.DataModelUtils;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    SearchData itemObj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Open Website for More details.", Snackbar.LENGTH_LONG)
                        .setAction("Open Website", webLinkListener).show();

            }
        });

        Intent intent = getIntent();
        itemObj = intent.getParcelableExtra(AppConstants.ITEM_PARCEL_KEY);
        if (getSupportActionBar() != null) {
            if(itemObj != null && itemObj.getName() != null && !itemObj.getName().isEmpty())
                getSupportActionBar().setTitle(itemObj.getName());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if (itemObj != null) {
            TextView mDetailsView = findViewById(R.id.details_tv);
            ImageView imageView = findViewById(R.id.Image_view);

            Picasso.with(this).load(DataModelUtils.getImageURL(itemObj, this)).into(imageView);
            mDetailsView.setText(Html.fromHtml(itemObj.toString()));
        }

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


    /**
     * Listener to open Leagues website on FAB Click
     */
    private final View.OnClickListener webLinkListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(itemObj != null) {
                String url = itemObj.getUrl();
                if (url == null)
                    return;

                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "http://" + url;
                }
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        }
    };
}
