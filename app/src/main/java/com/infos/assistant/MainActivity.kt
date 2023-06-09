package com.infos.assistant

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var toolbar: Toolbar
    private lateinit var totalMoney : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        totalMoney = findViewById(R.id.total_money_tv)
        toolbar = findViewById(R.id.mToolbar)
        setSupportActionBar(toolbar)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        navController = Navigation.findNavController(this,R.id.navHostFragment)
        NavigationUI.setupWithNavController(bottomNavigationView,navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,null)
    }

    fun hideToolbar(){
        toolbar.visibility = View.GONE
    }

    fun showToolbar(){
        toolbar.visibility = View.VISIBLE
    }
    fun showNavigationBar() {
        bottomNavigationView.visibility = View.VISIBLE
    }

    fun hideNavigationBar() {
        bottomNavigationView.visibility = View.GONE
    }
    fun hideTextview(){
        totalMoney.visibility = View.GONE
    }
    fun showTextview(){
        totalMoney.visibility = View.VISIBLE
    }
    fun changeTextview(amount:Int){
        totalMoney.text = "Total money: ${amount}"
    }
}