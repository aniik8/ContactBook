package com.example.contactbook;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recview;
    FloatingActionButton floatingbtn;
    databaseclass db;
    ArrayList<String> contact_id, contact_name, contact_phone, contact_dob ,contact_email;
    ImageView empty_img;
    TextView empty_text;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recview = findViewById(R.id.recview);
        floatingbtn = findViewById(R.id.floatingbtn);
        empty_img = findViewById(R.id.empty_img);
        empty_text = findViewById(R.id.empty_txt);
        floatingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(MainActivity.this, Add_book.class);
                startActivity(newIntent);
            }
        });
        db = new databaseclass(MainActivity.this);
        contact_id = new ArrayList<>();
        contact_name = new ArrayList<>();
        contact_phone = new ArrayList<>();
        contact_dob = new ArrayList<>();
        contact_email = new ArrayList<>();
        display_data();
        customAdapter = new CustomAdapter(MainActivity.this, this ,contact_id, contact_name, contact_phone,
                contact_dob ,contact_email );
        recview.setAdapter(customAdapter);
        recview.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            recreate();
        }
    }

    void display_data(){
        Cursor cursor = db.read_data();
        if(cursor.getCount() ==0){
            empty_img.setVisibility(View.VISIBLE);
            empty_text.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                contact_id.add(cursor.getString(0));
                contact_name.add(cursor.getString(1));
                contact_phone.add(cursor.getString(2));
                contact_dob.add(cursor.getString(3));
                contact_email.add(cursor.getString(4));
            }
            empty_img.setVisibility(View.GONE);
            empty_text.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.delete_all){
            confirm_dialog();

        }
        return super.onOptionsItemSelected(item);
    }
    void confirm_dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All??");
        builder.setMessage("Are You sure you want to delete ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databaseclass db = new databaseclass(MainActivity.this);

                db.delete_all_data();
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
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