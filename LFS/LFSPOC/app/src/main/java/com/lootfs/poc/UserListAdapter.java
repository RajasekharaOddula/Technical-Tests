package com.lootfs.poc;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserItemViewHolder> {

    private List<String> userNames = new ArrayList<>();

    public void updateUsersList(List<String> userNames) {
        this.userNames = userNames;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserListAdapter.UserItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserItemViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserListAdapter.UserItemViewHolder holder, int position) {
        holder.updateUserNameToItemView(userNames.get(position));
        setItemAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        if (userNames != null)
            return userNames.size();

        return 0;
    }

    /**
     * this Method is responsible to set Animation for Row Items.
     *
     * @param view itemView to apply Animation
     */
    private void setItemAnimation(View view, int position) {
        Animation anim = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);

        anim.setDuration(AppConstants.ANIM_DURATION);
        anim.setStartOffset(AppConstants.ANIM_ITEM_DELAY * position);

        view.startAnimation(anim);
    }

    public static class UserItemViewHolder extends RecyclerView.ViewHolder {

        public final TextView userNameView;

        public UserItemViewHolder(View itemView) {
            super(itemView);

            userNameView = (TextView) itemView.findViewById(R.id.user_name_view);
        }

        public void updateUserNameToItemView(String name) {
            userNameView.setText(name);
        }
    }
}
