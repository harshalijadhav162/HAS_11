package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
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

public class MenuAdapte extends RecyclerView.Adapter<MenuAdapte.ViewHolder> {


    ArrayList<Menu> mList;
    private MenuAdapte.RecyclerViewClickListener listener;

    public MenuAdapte(ArrayList<Menu> mList, MenuAdapte.RecyclerViewClickListener listener) {
        this.mList = mList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menulist,parent,false);

        return new MenuAdapte.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Menu vacancy1 = mList.get(position);
        holder.txtbname.setText("Mess Name: "+vacancy1.getName());
        holder.txtaddress.setText("Address: "+vacancy1.getAddress());
        holder.txttype.setText("Menu One: "+vacancy1.getMenuone());
        holder.txtcate.setText("Menu Two: "+vacancy1.getMenutwo());
        holder.txtmenu.setText("Menu three: "+vacancy1.getMenuthree());
        holder.txtnumber.setText("Contact Number: "+vacancy1.getNumber());
        Glide.with(holder.img1.getContext()).load(vacancy1.getImageurl()).into(holder.img1);
        holder.imgcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+91"+vacancy1.number));
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView img1,imgcall;
        TextView txtbname,txtaddress,txttype,txtcate,txtnumber,txtmenu;

        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img1 =itemView.findViewById(R.id.img1);
            txtbname = itemView.findViewById(R.id.nametext);
            txtaddress = itemView.findViewById(R.id.coursetext);
            txttype = itemView.findViewById(R.id.emailtext);
            relativeLayout = itemView.findViewById(R.id.relative);
            txtcate = itemView.findViewById(R.id.address);
            txtnumber = itemView.findViewById(R.id.number);
            imgcall = itemView.findViewById(R.id.call);
            txtmenu = itemView.findViewById(R.id.menuthree);




        }



    }

}
