package com.zack.bukucatatan

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class dbHerper (context: Context) :SQLiteOpenHelper(context, DATABASE_NAME,null,DATABASE_VERSION) {
    companion object{
        private const val DATABASE_NAME = "note.db"
        private const val DATABASE_VERSION =1
        private const val TABLE_NAME = "allnote"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_CONTENT = "content"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val tblquery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_TITLE TEXT,
                $COLUMN_CONTENT TEXT
            )
        """
        db?.execSQL(tblquery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS  $TABLE_NAME")
        onCreate(db)
    }


    fun insertNote(note : Note){

        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE,note.title)
            put(COLUMN_CONTENT,note.content)

        }
        db.insert(TABLE_NAME,null,values)
        db.close()
    }

    fun getAllNote(): List<Note>{
        val noteList = mutableListOf<Note>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query,null)

        while (cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
            val content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))

            Log.d("dbHerper","Fecth id:$id,title : $title")
            noteList.add(Note(id,title,content))
        }
        cursor.close()
        db.close()
        return noteList
    }


}