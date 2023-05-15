package com.example.uno.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.uno.R
import com.example.uno.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var userAdapter: UserAdapter
    private lateinit var gameAdapter: GameAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()


    }

    private fun setupRecyclerView() {
        setupUserAdapter()
        setupGameAdapter()
    }

    private fun setupUserAdapter() {
        val rvUsers = findViewById<RecyclerView>(R.id.rv_users)
        userAdapter = UserAdapter()
        with(rvUsers) {
            adapter = userAdapter
        }
    }

    private fun setupGameAdapter() {
        val rvGames = findViewById<RecyclerView>(R.id.rv_games)
        gameAdapter = GameAdapter()
        with(rvGames) {
            adapter = gameAdapter
            recycledViewPool.setMaxRecycledViews(
                GameAdapter.VIEW_TYPE_ACTIVE,
                GameAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                GameAdapter.VIEW_TYPE_DISACTIVE,
                GameAdapter.MAX_POOL_SIZE
            )
        }
    }

}