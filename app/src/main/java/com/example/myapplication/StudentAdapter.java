package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {


    ArrayList<Student> mList;
    private RecyclerViewClickListener listener;

    public StudentAdapter(ArrayList<Student> mList, RecyclerViewClickListener listener) {
        this.mList = mList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.useritem,parent,false);

        return new StudentAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Student vacancy1 = mList.get(position);
        holder.txtbname.setText("Student Name: "+vacancy1.getName());
        holder.txtaddress.setText("Address: "+vacancy1.getAddress());
        holder.txttype.setText("Class: "+vacancy1.getMyclass());
        holder.txtcate.setText("Roll No: "+vacancy1.getRollno());
        Glide.with(holder.img1.getContext()).load(vacancy1.getImageurl()).into(holder.img1);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView img1;
        TextView txtbname,txtaddress,txttype,txtcate;

        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img1 =itemView.findViewById(R.id.img1);
            txtbname = itemView.findViewById(R.id.nametext);
            txtaddress = itemView.findViewById(R.id.coursetext);
            txttype = itemView.findViewById(R.id.emailtext);
            relativeLayout = itemView.findViewById(R.id.relative);
            txtcate = itemView.findViewById(R.id.address);
            itemView.setOnClickListener(this);



        }


        @Override
        public void onClick(View view) {
            listener.onClick(view,getAdapterPosition());

        }
    }


}
