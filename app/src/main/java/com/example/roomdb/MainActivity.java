package com.example.roomdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText first_name, last_name, userId;
    TextView show_data;
    Button btn_add,btn_fetch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);
        btn_add = findViewById(R.id.btn_add);
        userId = findViewById(R.id.userId);
        btn_fetch = findViewById(R.id.btn_fetch);
        show_data = findViewById(R.id.tv);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //.........................Database object creation..........................
                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "room_db").allowMainThreadQueries().build();

                //.........................Interface object creation.........................
                UserDao userDao = db.userDao();
                Boolean check = userDao.exist(Integer.parseInt(userId.getText().toString()));
                if(check == false){
                    userDao.insertRecord(new User(Integer.parseInt(userId.getText().toString()), first_name.getText().toString(), last_name.getText().toString()));
                    userId.setText("");
                    first_name.setText("");
                    last_name.setText("");
                    Toast.makeText(MainActivity.this, "Inserted successfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    userId.setError("this id already exist in DB");
                    first_name.setText("");
                    last_name.setText("");
                    Toast.makeText(MainActivity.this, "Record already exists", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btn_fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),fetchDataInRecyclerView.class));

//                ...........................Simple fetch data of all users from RoomDatabase.........................
//                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
//                        AppDatabase.class, "room_db").allowMainThreadQueries().build();
//                UserDao userDao = db.userDao();
//
//                List<User> users = userDao.getallusers();
//
//                String str = "";
//                for(User user : users){
//                    str = str+"\t "+user.getUid()+" "+user.getFirstName()+" "+user.getLastName()+"\n\n";
//                    show_data.setText(str);
//                }
            }
        });
    }

    }

