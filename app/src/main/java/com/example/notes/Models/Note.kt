package com.example.notes.Models

@Entity(tableName = "notes")
data class Notes (
    @ColumnInfo(name = "title") var title:String,
    @ColumnInfo(name = "notes") var notes:String,
    @ColumnInfo(name = "date") var date:String,
    @ColumnInfo(name = "color") var color:Int,
    @ColumnInfo(name = "pinned") var pinned:Boolean
) {
    @PrimaryKey(autoGenerate = true) var id = 0
}