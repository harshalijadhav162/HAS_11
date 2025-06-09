package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;

import java.util.HashMap;
import java.util.Map;

public class UpdateAdapter extends FirebaseRecyclerAdapter<Menu,UpdateAdapter.ViewHolder> {


    private Context context;

    public UpdateAdapter(@NonNull FirebaseRecyclerOptions<Menu> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull Menu model) {

        holder.txtbname.setText("Hostel Name:- "+model.getName());
        holder.txtaddress.setText("Menu One:- "+model.getMenuone());
        holder.txttype.setText("Menu Two:- "+model.getMenutwo());
        holder.txtarea.setText("Menu Three:- "+model.getMenuthree());
        holder.txtcontact.setText("Number:- "+model.getNumber());
        holder.txtmenu.setText("Address:- "+model.getAddress());

        Glide.with(holder.img1.getContext()).load(model.getImageurl()).into(holder.img1);

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogPlus dialogPlus = DialogPlus.newDialog(context)
                        .setGravity(Gravity.CENTER)
                        .setMargin(50, 0, 50, 0)

                        .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.content))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();

                View view1 = (LinearLayout) dialogPlus.getHolderView();
                final EditText edname = view1.findViewById(R.id.edname);
                final EditText ednumber = view1.findViewById(R.id.ednumber);
                EditText edaddress = view1.findViewById(R.id.edaddress);
                EditText edone = view1.findViewById(R.id.edone);
                EditText edtwo = view1.findViewById(R.id.edtwo);
                EditText edthree = view1.findViewById(R.id.edthree);




                edname.setText(model.getName());
                ednumber.setText(model.getNumber());
                edaddress.setText(model.getAddress());
                edone.setText(model.getMenuone());
                edtwo.setText(model.getMenutwo());
                edthree.setText(model.getMenuthree());




                Button btn = view1.findViewById(R.id.btnupdate);

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Map<String,Object> map = new HashMap<>();
                        map.put("name",edname.getText().toString());
                        map.put("number",ednumber.getText().toString());
                        map.put("address",edaddress.getText().toString());
                        map.put("menuone",edone.getText().toString());
                        map.put("menutwo",edtwo.getText().toString());
                        map.put("menuthree",edthree.getText().toString());



                        FirebaseDatabase.getInstance().getReference().child("menu").child(getRef(position).getKey())
                                .updateChildren(map)

                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        dialogPlus.dismiss();
                                    }
                                });


                    }
                });
                dialogPlus.show();
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference().child("menu").child(getRef(position).getKey())
                        .setValue(null)

                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
            }
        });



    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.updatemenu,parent,false);

        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img1;
        TextView txtbname,txtaddress,txttype,txtarea,txtcontact,txtmenu;
        ImageView update,delete;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img1 =itemView.findViewById(R.id.img1);
            txtbname = itemView.findViewById(R.id.nametext);
            txtaddress = itemView.findViewById(R.id.coursetext);
            txttype = itemView.findViewById(R.id.emailtext);
            txtarea = itemView.findViewById(R.id.edarea);
            txtcontact = itemView.findViewById(R.id.edcontactno);
            txtmenu = itemView.findViewById(R.id.edmenu);


            update = itemView.findViewById(R.id.update);
            delete = itemView.findViewById(R.id.delete);
        }
    }

}
