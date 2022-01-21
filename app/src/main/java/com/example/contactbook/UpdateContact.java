package com.example.contactbook;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputEditText;


public class UpdateContact extends AppCompatActivity {

    TextInputEditText update_PersonName, update_editPhone;
    TextInputEditText update_editEmail;
    TextInputEditText update_date_birth;
    Button update_button, delete_button;
    String id, name, phone_number, dob, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);
        update_PersonName = findViewById(R.id.update_PersonName);
        update_editPhone = findViewById(R.id.update_editPhone);
        update_editEmail = findViewById(R.id.update_editEmail);
        update_date_birth = findViewById(R.id.update_date_birth);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);
        get_Intentdata();

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseclass db = new databaseclass(UpdateContact.this);
                name = update_PersonName.getText().toString().trim();
                phone_number = update_editPhone.getText().toString().trim();
                email = update_editEmail.getText().toString().trim();
                dob = update_date_birth.getText().toString().trim();
                db.update_data(id, name, phone_number, email, dob);

            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm_dialog(); }
        });
    }
    void get_Intentdata(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("phone_number")
                 && getIntent().hasExtra("email") && getIntent().hasExtra("dob")){
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            phone_number = getIntent().getStringExtra("phone_number");
            email = getIntent().getStringExtra("email");
            dob = getIntent().getStringExtra("dob");



            update_PersonName.setText(name);
            update_editPhone.setText(phone_number);
            update_editEmail.setText(email);
            update_date_birth.setText(dob);}
        else{
            Toast.makeText(this, "No Data available", Toast.LENGTH_SHORT).show();
        }
    }
    void confirm_dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ??");
        builder.setMessage("Are You sure you want to delete "+ name +"?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databaseclass db = new databaseclass(UpdateContact.this);
                db.delete_data(id);
                finish();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}