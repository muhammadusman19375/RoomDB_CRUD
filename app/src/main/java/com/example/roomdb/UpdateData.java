package com.example.roomdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateData extends AppCompatActivity {
    private EditText update_first_name,update_last_name;
    private Button update_btn;
    int uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        update_btn = findViewById(R.id.update);
        update_first_name = findViewById(R.id.fname);
        update_last_name = findViewById(R.id.lname);

        uid = Integer.parseInt(getIntent().getStringExtra("uid"));
        update_first_name.setText(getIntent().getStringExtra("Fname"));
        update_last_name.setText(getIntent().getStringExtra("Lname"));

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "room_db").allowMainThreadQueries().build();

                UserDao userDao = db.userDao();
                userDao.updateById(uid,update_first_name.getText().toString(),update_last_name.getText().toString());
                startActivity(new Intent(getApplicationContext(),fetchDataInRecyclerView.class));
                finish();

            }
        });
    }
}