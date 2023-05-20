package com.example.uno.presentation.mainActivity

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.uno.R
import com.example.uno.data.AppDatabase
import com.example.uno.data.consts.Id
import com.example.uno.databinding.ActivityMainBinding
import com.example.uno.domain.entity.Game
import com.example.uno.presentation.scoreTableActivity.ScoreActivity
import com.example.uno.presentation.scoreTableActivity.ScoreAdapter


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var etTarget: EditText
    private lateinit var btnDone: Button
    private lateinit var listGames: List<Game>

    private lateinit var modalAddGame: Dialog
    private lateinit var database: AppDatabase
    private lateinit var viewModel: MainViewModel
    private lateinit var userAdapter: UserAdapter
    private lateinit var gameAdapter: GameAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initModalCreateGame()
        setupRecyclerView()
        initViewModel()

    }

    private fun initModalCreateGame() {
        modalAddGame = Dialog(this);
        modalAddGame.setContentView(R.layout.modal_create_game);
        etTarget = modalAddGame.findViewById(R.id.et_target)
        btnDone = modalAddGame.findViewById(R.id.btn_done)
        onClickOpenModalAddUser()
    }

    private fun onClickOpenModalAddUser() {
        binding.btnCreateTable.setOnClickListener {
            modalAddGame.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            modalAddGame.show()
            listenerCreateGame()
        }
    }

    private fun listenerCreateGame() {
        btnDone.setOnClickListener {
            val target = etTarget.text.toString()
            etTarget.text = null
            viewModel.addCreateGame(target)
        }
    }

    private fun initViewModel() {
        database = AppDatabase.getInstance(this)
        viewModel = ViewModelProvider(this, MainViewModelFactory(database))[MainViewModel::class.java]
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.getUsersList.observe(this) {
            userAdapter.submitList(it)
            Log.d("sasas", "Участники: $it")
        }
        viewModel.getGamesList.observe(this) {
            listGames = it
            gameAdapter.submitList(it)
            binding.tvCountGames.text = it.size.toString()
            Log.d("sasas", "Установлен список с играми")
        }
        viewModel.shouldCloseModal.observe(this) {
            modalAddGame.dismiss();
        }
    }

    private fun setupRecyclerView() {
        setupUserAdapter()
        setupGameAdapter()
        setupClickListener()
    }

    private fun openGame(id: Int){
        val intent = ScoreActivity.newIntentScoreActivity(this,id)
        startActivity(intent)
    }

    private fun setupClickListener() {
        gameAdapter.onGameClickListener = {
            openGame(it.id)
        }
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