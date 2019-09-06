package com.example.boris.testrestapi.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.boris.testrestapi.MainActivity;
import com.example.boris.testrestapi.R;
import com.example.boris.testrestapi.models.Item;
import com.squareup.picasso.Picasso;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyHolder> {

    Item messages;
    MainActivity mainActivity;

    public MyRecyclerAdapter(Item messages, MainActivity mainActivity) {
        this.messages = messages;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Picasso.with(mainActivity.getApplicationContext()).load(messages.getData().get(position).getAvatar_image().getUrl_medium()).into(holder.imageView);
        holder.name.setText(messages.getData().get(position).getName());
    }

    @Override
    public int getItemCount() {
        return messages.getData().size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.recyclerImage);
            name = itemView.findViewById(R.id.textView6);
        }
    }

}
