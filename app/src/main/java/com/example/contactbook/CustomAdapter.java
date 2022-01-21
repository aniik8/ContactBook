package com.example.contactbook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    Activity activity;
    private ArrayList contact_id, contact_name, contact_phone, contact_email, contact_dob;
    int position;
    CustomAdapter(Activity activity ,Context context, ArrayList contact_id, ArrayList contact_name, ArrayList contact_phone,
                  ArrayList contact_email, ArrayList contact_dob){
        this.activity= activity;
        this.context = context;
        this.contact_id = contact_id;
        this.contact_name = contact_name;
        this.contact_phone = contact_phone;
        this.contact_dob = contact_dob;
        this.contact_email = contact_email;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.contact_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        this.position = position;
        holder.textView.setText(String.valueOf(contact_id.get(position)));
        holder.name.setText(String.valueOf(contact_name.get(position)));
        holder.phone_number.setText(String.valueOf(contact_phone.get(position)));
        holder.dob.setText(String.valueOf(contact_dob.get(position)));
        holder.email.setText(String.valueOf(contact_email.get(position)));
        holder.mainlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateContact.class);
                intent.putExtra("id", String.valueOf(contact_id.get(position)));
                intent.putExtra("name", String.valueOf(contact_name.get(position)));
                intent.putExtra("phone_number", String.valueOf(contact_phone.get(position)));
                intent.putExtra("dob", String.valueOf(contact_dob.get(position)));
                intent.putExtra("email", String.valueOf(contact_email.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contact_id.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView, name, phone_number, dob, email;
        LinearLayout mainlayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            name = itemView.findViewById(R.id.name);
            phone_number = itemView.findViewById(R.id.phone_number);
            dob = itemView.findViewById(R.id.dob);
            email = itemView.findViewById(R.id.email);
            mainlayout = itemView.findViewById(R.id.mainlayout);

        }
    }
}
