package com.example.uno.presentation.scoreTableActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.uno.R
import com.example.uno.data.AppDatabase
import com.example.uno.databinding.ActivityScoreBinding
import com.example.uno.domain.entity.Column
import com.example.uno.domain.entity.Game

class ScoreActivity : AppCompatActivity() {

    private var gameId = UNDEFINED_ID
    private var gameItem: Game? = null

    private lateinit var database: AppDatabase
    private lateinit var viewModel: ScoreViewModel
    private lateinit var binding: ActivityScoreBinding
    private lateinit var scoreAdapter: ScoreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        parseIntent()
        initViewModel()
        setupRecyclerView()
        initViews()
        Log.d("ScoreActivity", "gameItem3: $gameItem")
    }

    private fun initViews(){
        binding.tvNumberOfGame.text = "№ $gameId"
        binding.btnCloseActivity.setOnClickListener{
            finish()
        }
        binding.btnAddScoreTyomik.setOnClickListener {
            val score = arrayListOf(24,74,35,2)
            val column = Column(1,score)
            gameItem?.let {
                viewModel.addColumn(it, column)
            }
        }
    }

    private fun initViewModel() {
        database = AppDatabase.getInstance(this)
        viewModel = ViewModelProvider(this, ScoreViewModelFactory(database))[ScoreViewModel::class.java]
        observeViewModel()
        viewModel.getGame(gameId)
    }

    private fun observeViewModel(){
        viewModel.shouldCloseActivity.observe(this){
            finish()
        }
        viewModel.gameItem.observe(this){
            gameItem = it
            binding.tvTarget.text = it.targetOfScore.toString()
            scoreAdapter.submitList(it.listColumns.reversed())
            binding.rvScore.post {
                binding.rvScore.smoothScrollToPosition(it.listColumns.size - 1)
            }
        }
    }

    private fun parseIntent(){
        val intent = intent
        val id = intent.getIntExtra(KEY_ID, UNDEFINED_ID)
        gameId = id
    }

    private fun setupRecyclerView(){
        val rvScore = findViewById<RecyclerView>(R.id.rv_score)
        scoreAdapter = ScoreAdapter()
        with(rvScore) {
            adapter = scoreAdapter
        }
    }

    companion object{

        const val KEY_ID = "key_id"
        const val UNDEFINED_ID = -1

        fun newIntentScoreActivity(context: Context, id: Int): Intent{
            val intent = Intent(context,ScoreActivity::class.java)
            intent.putExtra(KEY_ID, id)
            return intent
        }
    }
}