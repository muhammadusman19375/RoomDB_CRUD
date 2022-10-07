package com.example.roomdb;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertRecord (User users);

    @Query("SELECT EXISTS (SELECT * FROM User WHERE uid = :userId)")
    Boolean exist(int userId);

    @Query("SELECT * FROM user")
    List<User> getallusers();

    @Query("DELETE FROM User WHERE uid = :id")
    void deleteById(int id);

    @Query("UPDATE User SET first_name = :update_first_name,last_name = :update_last_name WHERE uid = :userId")
    void updateById(int userId, String update_first_name, String update_last_name);


}
