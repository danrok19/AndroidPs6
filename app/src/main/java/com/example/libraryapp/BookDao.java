package com.example.libraryapp;

import androidx.lifecycle.LiveData;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.Update;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import java.util.List;

@Dao
public interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Book book);

    @Update
    void update(Book book);

    @Delete
    void delete(Book book);

    @Query("DELETE FROM book")
    void deleteAll();

    @Query("SELECT * FROM book ORDER BY title")
    LiveData<List<Book>> findAll();

    @Query("SELECT * FROM book WHERE title LIKE :title")
    List<Book> findBookWithTitle(String title);
}
