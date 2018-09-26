package com.last.fm.poc;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.last.fm.poc.datamodels.SearchData;
import com.last.fm.poc.utils.DataModelUtils;
import com.squareup.picasso.Picasso;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    public final TextView nameView;
    public final ImageView imageView;

    public ItemViewHolder(View itemLayoutView) {
        super(itemLayoutView);

        imageView = itemLayoutView.findViewById(R.id.img_view);
        nameView = itemLayoutView.findViewById(R.id.name_view);
    }

    public void updateDataToItemView(SearchData searchData) {
        nameView.setText(searchData.getName());
        Picasso.with(imageView.getContext()).load(DataModelUtils.getImageURL(searchData, imageView.getContext())).into(imageView);
    }


}

