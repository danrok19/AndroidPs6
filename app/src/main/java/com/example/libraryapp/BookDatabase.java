package com.example.libraryapp;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.libraryapp.BookDao;
import com.example.libraryapp.Book;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Book.class}, version = 1, exportSchema = false)
public abstract class BookDatabase extends RoomDatabase {

    private static BookDatabase databaseInstance;
    public static final ExecutorService databaseWriteExecutor = Executors.newSingleThreadExecutor();

    public abstract BookDao bookDao();

    public static BookDatabase getDatabase(final Context context) {
        if(databaseInstance == null) {
            databaseInstance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            BookDatabase.class,
                            "book_db"
                    )
                    .addCallback(roomDatabaseCallback)
                    .build();
        }
        return databaseInstance;
    }

    private static final RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                BookDao dao = databaseInstance.bookDao();
                Book[] books = new Book[] {
                        new Book("Clean code", "Robert C. Martin"),
                        new Book("Zwiadowcy Ruiny Gorlanu", "John Flanagan"),
                        new Book("Wiedźmin Ostatnie Życzenie", "Andrzej Sapkowski"),
                        new Book("Wiedźmin Miecz Przeznaczenia", "Andrzej Sapkowski"),
                        new Book("Krew Elfów", "Andrzej Sapkowski"),
                        new Book("Czas Pogardy", "Andrzej Sapkowski"),
                        new Book("Chrzest Ognia", "Andrzej Sapkowski"),
                        new Book("Wieża Jaskółki", "Andrzej Sapkowski"),
                        new Book("Wiedźmin Pani Jeziora", "Andrzej Sapkowski"),
                        new Book("Sezon Burz", "Andrzej Sapkowski"),
                        new Book("Camp Pozzi. GROM w Iraku", "Naval"),
                        new Book("Ostatnich gryzą psy", "Naval"),
                        new Book("Przetrwać Belize", "Naval"),

                };

                for (Book book:
                        books) {
                    dao.insert(book);
                }
            });
        }
    };
}