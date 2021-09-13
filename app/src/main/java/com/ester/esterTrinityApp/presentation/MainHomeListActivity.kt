package com.ester.esterTrinityApp.presentation

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ester.esterTrinityApp.R
import com.ester.esterTrinityApp.data.model.Photo
import com.ester.esterTrinityApp.util.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.activity_main_home.*
import org.koin.android.ext.android.inject

class MainHomeListActivity : AppCompatActivity() {

    private val homeAdapter: HomeAdapter by inject()
    private val mainHomeViewModel: HomeViewModel by inject()
    lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_home)

        mainHomeViewModel.getData()

        mainHomeViewModel.listPhotos.observe(this, Observer {
            if (it != null) updateListOfPhotos(it)
        })
        mainHomeViewModel.errorRoom.observe(this, Observer {
            Toast.makeText(this, "Erro ao acessar dados", Toast.LENGTH_LONG).show()
        })
        loadRecyclerView()

        preferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        val userEmail = intent.getStringExtra("USER_EMAIL")

        userEmailIdentification.text = userEmail

        if (userEmailIdentification.text.isEmpty()) {
            val userEmailShared = preferences.getString("USER_EMAIL", "")
            userEmailIdentification.text = userEmailShared
        }

        materialButtonSair.setOnClickListener {
            val editor: SharedPreferences.Editor = preferences.edit()
            editor.clear()
            editor.apply()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }



    private fun loadRecyclerView() {

        val linearLayoutHome = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mainHomeRecyclerView.apply {
            adapter = homeAdapter
            setHasFixedSize(true)
            this.layoutManager = linearLayoutHome
        }

        mainHomeRecyclerView.addOnScrollListener(object :
            EndlessRecyclerViewScrollListener(linearLayoutHome) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                mainHomeViewModel.getData()
            }
        })
    }

    private fun updateListOfPhotos(photoList: List<Photo>) {
        homeAdapter.updateData(photoList)
    }
}