package com.example.roomdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.hardware.lights.LightState;
import android.os.Bundle;

import java.util.List;

public class fetchDataInRecyclerView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_data_in_recycler_view);

        getRoomData();

    }
    public void getRoomData(){
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,"room_db").allowMainThreadQueries().build();

        RecyclerView rcview = findViewById(R.id.recyclerView);
        rcview.setLayoutManager(new LinearLayoutManager(this));

        UserDao userDao = db.userDao();
        List<User> users = userDao.getallusers();

        myadapter adapter = new myadapter(users);
        rcview.setAdapter(adapter);

    }
}