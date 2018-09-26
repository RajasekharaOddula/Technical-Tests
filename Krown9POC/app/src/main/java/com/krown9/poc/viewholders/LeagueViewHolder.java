package com.krown9.poc.viewholders;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.krown9.poc.R;
import com.krown9.poc.model.League;

/**
 * This Class Provide a reference to the views for each League item
 */
public class LeagueViewHolder extends RecyclerView.ViewHolder {

    private final TextView mLeagueNameView;
    private final TextView mSportNameView;
    private final ImageView mGameIconView;
    private final TextView mLeagueIDView;
    private final TextView mLeagueAltNameView;

    public LeagueViewHolder(View itemView) {
        super(itemView);

        mLeagueNameView = itemView.findViewById(R.id.league_name_view);
        mSportNameView = itemView.findViewById(R.id.sport_name_view);
        mLeagueIDView = itemView.findViewById(R.id.league_id_view);
        mLeagueAltNameView = itemView.findViewById(R.id.alt_league_name_view);
        mGameIconView = itemView.findViewById(R.id.game_icon);
    }

    public void updateLeagueDataToView(League mLeague) {
        if (mLeague != null && mLeagueNameView != null)
            mLeagueNameView.setText(mLeague.getStrLeague());

        if (mLeague != null && mSportNameView != null)
            mSportNameView.setText(mLeague.getStrSport());

        if (mLeague != null && mLeagueAltNameView != null)
            mLeagueAltNameView.setText(mLeague.getStrLeagueAlternate());

        if (mLeague != null && mLeagueIDView != null)
            mLeagueIDView.setText(mLeague.getIdLeague());

        if (mLeague != null && mGameIconView != null)
            mGameIconView.setImageResource(getIconResID(mLeague.getStrSport()));
    }

    private int getIconResID(String gameName) {
        int iconResID = android.R.drawable.ic_dialog_alert;
        if (gameName == null)
            return iconResID;

        if (gameName.equalsIgnoreCase("Soccer"))
            iconResID = R.drawable.ic_soccer;
        else if (gameName.equalsIgnoreCase("Ice Hockey"))
            iconResID = R.drawable.ic_icehocky;
        else if (gameName.equalsIgnoreCase("Basketball"))
            iconResID = R.drawable.ic_baskerball;
        else if (gameName.equalsIgnoreCase("Motorsport"))
            iconResID = R.drawable.ic_motorsport;
        else if (gameName.equalsIgnoreCase("American Football"))
            iconResID = R.drawable.ic_americanfootball;
        else if (gameName.equalsIgnoreCase("Golf"))
            iconResID = R.drawable.ic_golf;
        else if (gameName.equalsIgnoreCase("Baseball"))
            iconResID = R.drawable.ic_baseball2;
        else if (gameName.equalsIgnoreCase("Rugby"))
            iconResID = R.drawable.ic_rugby;
        else if (gameName.equalsIgnoreCase("Cricket"))
            iconResID = R.drawable.ic_cricket1;
        else if (gameName.equalsIgnoreCase("Fighting"))
            iconResID = R.drawable.ic_fighting;
        else if (gameName.equalsIgnoreCase("Australian football"))
            iconResID = R.drawable.ic_aus_football;
        else if (gameName.equalsIgnoreCase("Cycling"))
            iconResID = R.drawable.ic_cycling1;
        else if (gameName.equalsIgnoreCase("Tennis"))
            iconResID = R.drawable.ic_tennis;

        return iconResID;
    }
}
