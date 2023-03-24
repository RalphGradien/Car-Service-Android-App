package com.example.carserviceandroidapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter {
    ItemClickListener itemClickListener;

    ArrayList<ImageAndText> aList;
    LayoutInflater inflater;

    public CustomAdapter(Context context, ArrayList<ImageAndText> list,
                         ItemClickListener itemClickListener1){
        // mData = data;
        aList = list;
        itemClickListener = itemClickListener1;
        inflater = LayoutInflater.from(context);
        //this.names = names;
    }

    Integer getItem(int id){
        return aList.get(id).getImageId();
    };

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                      int viewType) {
        View view = inflater.inflate(R.layout.recyclerview_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).imageView.setImageResource(aList.get(position).getImageId());
        ((ViewHolder)holder).textView.setText(aList.get(position).getTxt());
    }

    @Override
    public int getItemCount() {
        return  aList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgDelete);
            textView = itemView.findViewById(R.id.txt);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(itemClickListener!=null)
                        itemClickListener.onItemClick(view,getAdapterPosition());
                }
            });
        }
    }

    public interface ItemClickListener{
        void onItemClick(View view,int position);
    }

}

