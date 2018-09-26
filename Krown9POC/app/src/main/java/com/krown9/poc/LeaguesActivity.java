package com.krown9.poc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.krown9.poc.adapters.LeaguesListAdapter;
import com.krown9.poc.constants.AppConstants;
import com.krown9.poc.listeners.OnItemClickListener;
import com.krown9.poc.model.League;
import com.krown9.poc.utils.FetchDataTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class LeaguesActivity extends AppCompatActivity {

    private LeaguesListAdapter mLeaguesListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leagues);

        RecyclerView leaguesListView = findViewById(R.id.leagues_recycler_view);

        /*
         * defining and set suitable LayoutManager to RecyclerView
         */
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        leaguesListView.setLayoutManager(layoutManager);
        leaguesListView.addItemDecoration(new DividerItemDecoration(leaguesListView.getContext(), DividerItemDecoration.VERTICAL));

        /*
         * defining and attaching the Adapter to RecyclerView
         */
        mLeaguesListAdapter = new LeaguesListAdapter(leagueItemClickListener);
        leaguesListView.setAdapter(mLeaguesListAdapter);

        /*
         * defining the ItemTouch Callbacks to handle touch events like swipe, move and attach it to RecyclerView
         */
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(leagueItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(leaguesListView);

        new FetchDataTask(this).execute(AppConstants.ALL_LEAGUES);
    }

    private void updateDataSet(ArrayList<League> leaguesList) {
        mLeaguesListAdapter.setLeagueList(leaguesList);
        mLeaguesListAdapter.notifyDataSetChanged();
    }

    public void parseJSON(String responseJson) {
        try {
            JSONObject jsonObj = new JSONObject(responseJson);

            // Getting JSON Array node
            JSONArray leagues = jsonObj.getJSONArray("leagues");

            ArrayList<League> leaguesList = new ArrayList<>();

            for (int index = 0; index < leagues.length(); index++) {
                League mLeague = new League();
                JSONObject jsonLeague = leagues.getJSONObject(index);
                mLeague.setIdLeague(jsonLeague.getString("idLeague"));
                mLeague.setStrLeague(jsonLeague.getString("strLeague"));
                mLeague.setStrSport(jsonLeague.getString("strSport"));
                mLeague.setStrLeagueAlternate(jsonLeague.getString("strLeagueAlternate"));

                leaguesList.add(mLeague);
            }

            updateDataSet(leaguesList);

        } catch (Exception e) {
            Log.e("Leagues", "Error parsing data" + e.getMessage());

        }
    }

    private final ItemTouchHelper.Callback leagueItemTouchCallback = new ItemTouchHelper.Callback() {

        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return (makeFlag(ItemTouchHelper.ACTION_STATE_DRAG, ItemTouchHelper.DOWN | ItemTouchHelper.UP)
                    | makeFlag(ItemTouchHelper.ACTION_STATE_SWIPE, ItemTouchHelper.START | ItemTouchHelper.END));
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder dragged, RecyclerView.ViewHolder target) {
            Collections.swap(mLeaguesListAdapter.getLeagueList(), dragged.getAdapterPosition(), target.getAdapterPosition());

            mLeaguesListAdapter.notifyItemMoved(dragged.getAdapterPosition(), target.getAdapterPosition());
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            mLeaguesListAdapter.getLeagueList().remove(viewHolder.getAdapterPosition());

            mLeaguesListAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
        }
    };

    private final OnItemClickListener leagueItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            League league = mLeaguesListAdapter.getLeagueList().get(position);

            if(league != null) {
                Intent intent = new Intent(LeaguesActivity.this, LeagueDetailActivity.class);

                intent.putExtra(AppConstants.LEAGUE_ID_KEY, league.getIdLeague());
                intent.putExtra(AppConstants.LEAGUE_NAME_KEY, league.getStrLeague());

                LeaguesActivity.this.startActivity(intent);
            }
        }
    };


}
