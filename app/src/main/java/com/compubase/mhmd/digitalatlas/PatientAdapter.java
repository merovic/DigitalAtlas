package com.compubase.mhmd.digitalatlas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.ViewHolder> {
    ArrayList<Patient> ourPatients;
    Context context;
    TinyDB tinyDB;

    public PatientAdapter(Context context, ArrayList<Patient> list) {

        ourPatients = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView userimg;
        TextView name, id, email;
        Button update;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            id = itemView.findViewById(R.id.userid);
            email = itemView.findViewById(R.id.useremail);
            userimg = itemView.findViewById(R.id.userimg);
            update = itemView.findViewById(R.id.update);
        }


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patientlists, parent
                ,false);

        context = parent.getContext();
        tinyDB = new TinyDB(context);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.name.setText(ourPatients.get(position).getName());
        Glide.with(context)
                .load(ourPatients.get(position).getImgUrl())
                .into(holder.userimg);
        holder.id.setText(ourPatients.get(position).getId());
        holder.email.setText(ourPatients.get(position).getUseremail());
        if(tinyDB.getString("type").equals("admin"))
        {
            holder.update.setVisibility(View.VISIBLE);
            holder.update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,Addfinging1ByAdmin.class);
                    intent.putExtra("userID",ourPatients.get(position).getId());
                    intent.putExtra("userApproval",ourPatients.get(position).getIsApproved());
                    context.startActivity(intent);
                }
            });
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailsActivity.class);
                intent.putExtra("userIMG",ourPatients.get(position).getImgUrl());
                intent.putExtra("userName",ourPatients.get(position).getName());
                intent.putExtra("userEmail",ourPatients.get(position).getUseremail());
                intent.putExtra("userPhone",ourPatients.get(position).getPhone());
                intent.putExtra("userSystem",ourPatients.get(position).getSystem());
                intent.putExtra("userFinding",ourPatients.get(position).getFinding());
                intent.putExtra("userAbnormality",ourPatients.get(position).getAbnormality());
                intent.putExtra("userMention",ourPatients.get(position).getMention());
                intent.putExtra("userDate",ourPatients.get(position).getDate());
                intent.putExtra("userScenario",ourPatients.get(position).getScenario());
                intent.putExtra("userMessage",ourPatients.get(position).getMessage());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return ourPatients.size();
    }

}
