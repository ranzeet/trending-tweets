package com.example.twitterclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.MyViewHolder> {

    ArrayList<String> namesList;
    Context context;

    public TweetAdapter(Context context, ArrayList<String> namesList) {
        this.namesList = namesList;
        this.context = context;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mNamesTxt.setText(namesList.get(position));
//        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return namesList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mNamesTxt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mNamesTxt = itemView.findViewById(R.id.data_names_txt);
        }
    }

}
