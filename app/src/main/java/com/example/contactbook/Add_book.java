package com.example.contactbook;

import android.app.Person;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputEditText;

public class Add_book extends AppCompatActivity {
    TextInputEditText PersonName;
    TextInputEditText editPhone;
    TextInputEditText editEmail;
    TextInputEditText date_birth;
    Button buttonadd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        PersonName = findViewById(R.id.PersonName);
        editPhone = findViewById(R.id.editPhone);
        editEmail = findViewById(R.id.editEmail);
        date_birth = findViewById(R.id.date_birth);
        buttonadd = findViewById(R.id.buttonadd);
        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseclass myDB = new databaseclass(Add_book.this);
                myDB.add_contact(PersonName.getText().toString().trim(),
                        (editPhone.getText().toString()), date_birth.getText().toString().trim(),
                        editEmail.getText().toString().trim());

            }
        });
    }
}