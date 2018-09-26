package com.krown9.poc.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

import com.krown9.poc.R;
import com.krown9.poc.constants.AppConstants;
import com.krown9.poc.listeners.OnItemClickListener;
import com.krown9.poc.model.League;
import com.krown9.poc.viewholders.LeagueViewHolder;

import java.util.ArrayList;

public class LeaguesListAdapter extends RecyclerView.Adapter<LeagueViewHolder> {

    private final OnItemClickListener itemClickListener;
    private ArrayList<League> leagueList = new ArrayList<>();

    public ArrayList<League> getLeagueList() {
        return leagueList;
    }

    public void setLeagueList(ArrayList<League> leagueList) {
        this.leagueList = leagueList;
    }

    public LeaguesListAdapter(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public LeagueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RelativeLayout itemView;

        switch (viewType) {
            case 0:
                itemView = (RelativeLayout) LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_view, parent, false);
                break;
            case 1:
                itemView = (RelativeLayout) LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_view_1, parent, false);
                break;

            case 2:
                itemView = (RelativeLayout) LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_view_2, parent, false);
                break;

            default:
                itemView = (RelativeLayout) LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_view, parent, false);

        }

        final LeagueViewHolder leagueViewHolder = new LeagueViewHolder(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(leagueViewHolder.getAdapterPosition());
            }
        });

        return leagueViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LeagueViewHolder holder, int position) {
        holder.updateLeagueDataToView(leagueList.get(position));
        setItemAnimation(holder.itemView);
    }

    /**
     * this Method is responsible to set Animation for Row Items.
     *
     * @param view
     *          itemView to apply Animation
     */
    private void setItemAnimation(View view) {
        Animation anim;
       // if(itemType%2 == 0) {
            anim = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF, 1.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f);
       /* } else {
            anim = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF, -1.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f);
        }*/

        anim.setDuration(AppConstants.ANIM_DURATION);
        anim.setStartOffset(AppConstants.ANIM_ITEM_DELAY);

        view.startAnimation(anim);
    }

    @Override
    public int getItemCount() {
        return leagueList.size();
    }

    @Override
    public int getItemViewType(int position) {
        League league = leagueList.get(position);
        if(league != null) {
            String sportName = league.getStrSport();
            if(sportName != null && !sportName.isEmpty())
                return getRowType(sportName);
        }
        return super.getItemViewType(position);
    }

    private int getRowType(String leagueSportName) {
        int rowType = 0;
        if (leagueSportName == null)
            return rowType;

        if (leagueSportName.equalsIgnoreCase("Soccer"))
            rowType = 1;
        else if (leagueSportName.equalsIgnoreCase("Ice Hockey"))
            rowType = 2;
        else if (leagueSportName.equalsIgnoreCase("Basketball"))
            rowType = 1;
        else if (leagueSportName.equalsIgnoreCase("Motorsport"))
            rowType = 0;
        else if (leagueSportName.equalsIgnoreCase("American Football"))
            rowType = 1;
        else if (leagueSportName.equalsIgnoreCase("Golf"))
            rowType = 2;
        else if (leagueSportName.equalsIgnoreCase("Baseball"))
            rowType = 2;
        else if (leagueSportName.equalsIgnoreCase("Rugby"))
            rowType = 1;
        else if (leagueSportName.equalsIgnoreCase("Cricket"))
            rowType = 2;
        else if (leagueSportName.equalsIgnoreCase("Fighting"))
            rowType = 0;
        else if (leagueSportName.equalsIgnoreCase("Australian football"))
            rowType = 1;
        else if (leagueSportName.equalsIgnoreCase("Cycling"))
            rowType = 0;
        else if (leagueSportName.equalsIgnoreCase("Tennis"))
            rowType = 2;

        return rowType;
    }
}
