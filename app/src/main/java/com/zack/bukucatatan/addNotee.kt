package com.zack.bukucatatan

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.zack.bukucatatan.databinding.ActivityAddNoteeBinding
import com.zack.bukucatatan.databinding.ActivityMainBinding

class addNotee : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteeBinding
    private lateinit var db :dbHerper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = dbHerper(this)


        binding.saveButton.setOnClickListener(){
            val title = binding.txtjudul.text.toString()
            val content = binding.txtcontent.text.toString()
            val note = Note (0,title,content)
            db.insertNote(note)
            finish()
            Toast.makeText(this,"catatan disimpan",Toast.LENGTH_SHORT).show()
        }


    }
}