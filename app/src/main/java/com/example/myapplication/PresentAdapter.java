package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PresentAdapter extends RecyclerView.Adapter<PresentAdapter.ViewHolder> {


    ArrayList<Present> mList;
    Context context;

    public PresentAdapter(ArrayList<Present> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Present vacancy1 = mList.get(position);
        holder.txtbname.setText("Roll No "+vacancy1.getRollno());
        holder.txtaddress.setText("date "+vacancy1.getDate());
        holder.txttype.setText("Time "+vacancy1.getTime());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {


        TextView txtbname,txtaddress,txttype;
        RelativeLayout relativeLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            txtbname = itemView.findViewById(R.id.nametext);
            txtaddress = itemView.findViewById(R.id.coursetext);
            txttype = itemView.findViewById(R.id.emailtext);
            relativeLayout = itemView.findViewById(R.id.relative);
        }
    }
}
