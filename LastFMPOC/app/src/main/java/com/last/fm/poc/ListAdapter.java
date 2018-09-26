package com.last.fm.poc;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.last.fm.poc.datamodels.SearchData;
import com.last.fm.poc.listeners.OnItemClickListener;
import com.last.fm.poc.listeners.UpdateEventListener;
import com.last.fm.poc.parsing.JSONParser;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ItemViewHolder> implements UpdateEventListener {

    private final OnItemClickListener itemClickListener;

    private List<SearchData> searchList = new ArrayList<>();

    public ListAdapter(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void updateEvent(String data) {
        if (data != null) {
            this.searchList = JSONParser.parseJSON(data, AppConfigurations.getSearchModelType());
            this.notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);

        final ItemViewHolder itemViewHolder = new ItemViewHolder(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(searchList.get(itemViewHolder.getAdapterPosition()));
            }
        });

        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.updateDataToItemView(searchList.get(position));
    }

    @Override
    public int getItemCount() {
        if (searchList != null)
            return searchList.size();

        return 0;
    }

}