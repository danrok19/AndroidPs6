package dataBase.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "book")
public class Book {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String author;

    public int getId(){
        return this.id;
    }
    void setId(int _id){
        this.id = _id;
    }
    public String getTitle(){
        return this.title;
    }
    void setTitle(String _title){
        this.title = _title;
    }
    public String getAuthor(){
        return this.author;
    }
    void setAuthor(String _author){
        this.author = _author;
    }
}