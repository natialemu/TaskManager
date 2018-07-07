package com.natialemu.taskmanager.View;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.natialemu.taskmanager.Domain.Item;
import com.natialemu.taskmanager.R;

import java.util.ArrayList;
import java.util.List;

public class TaskViewAdapter extends RecyclerView.Adapter<TaskViewAdapter.ViewHolder>{

    private List<Item> items;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    //Constructor for TaskViewAdapter goes here
    public TaskViewAdapter(List<Item> itemList) {
        items = new ArrayList<>();

        for(Item i:itemList){
            items.add(i);
        }
    }

    public void addItem(Item item){
        items.add(item);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TaskViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
//        // create a new view
      TextView v = (TextView) LayoutInflater.from(parent.getContext())
               .inflate(R.layout.fragment_card, parent, false);
//        ...
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(items.get(position).getNotes());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
