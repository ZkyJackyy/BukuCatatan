package com.zack.bukucatatan

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.zack.bukucatatan.adapter.adapterNote
import com.zack.bukucatatan.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var db: dbHerper
    private lateinit var noteAdapter : adapterNote

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = dbHerper(this)
        noteAdapter = adapterNote(db.getAllNote(),this)

        binding.rvnote.layoutManager = LinearLayoutManager(this)
        binding.rvnote.adapter = noteAdapter

        binding.addbutton.setOnClickListener(){
            val intent = Intent(this,addNotee::class.java)
            startActivity(intent)
        }


    }
    override fun onResume(){
        super.onResume()
        val notes = db.getAllNote()
        Log.d("MainActivity","Notes count ${notes.size}")
        noteAdapter.refreshData(notes)
    }

}