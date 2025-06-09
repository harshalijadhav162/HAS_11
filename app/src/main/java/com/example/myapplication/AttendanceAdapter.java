package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AttendanceAdapter  extends RecyclerView.Adapter<AttendanceAdapter.ViewHolder>{

    ArrayList<Present> mList;


    private static RecyclerViewClickListener listener ;

    public AttendanceAdapter(ArrayList<Present> mList, RecyclerViewClickListener listener) {
        this.mList = mList;
        this.listener = listener;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ouritem,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        Present vacancy1 = mList.get(position);


        holder.name.setText("Student Name - "+vacancy1.getName());
        holder.address.setText("Number- "+vacancy1.getNumber());
        holder.number.setText("Roll No-"+vacancy1.getRollno());
        holder.tslot.setText("Date-"+vacancy1.getDate());
        holder.fslot.setText("Time -"+vacancy1.getTime());



    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView name, address, number, tslot, fslot;
        ImageView imageurl,img,msg;
//        Button btninsert;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.nametext);
            address = itemView.findViewById(R.id.coursetext);
            number = itemView.findViewById(R.id.emailtext);
            tslot = itemView.findViewById(R.id.text);
            fslot = itemView.findViewById(R.id.text1);


            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            listener.onClick(view,getAdapterPosition());
        }




    }
}
