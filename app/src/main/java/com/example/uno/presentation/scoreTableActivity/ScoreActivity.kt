package com.example.uno.presentation.scoreTableActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.uno.R
import com.example.uno.data.AppDatabase
import com.example.uno.databinding.ActivityScoreBinding
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
    }

    private fun initViews(){
        binding.tvNumberOfGame.text = "№ $gameId"
        binding.btnCloseActivity.setOnClickListener{
            finish()
        }
    }

    private fun initViewModel() {
        database = AppDatabase.getInstance(this)
        viewModel = ViewModelProvider(this, ScoreViewModelFactory(database))[ScoreViewModel::class.java]
        observeViewModel()
        viewModel.getGame(gameId)
        gameItem?.let {
            viewModel.getGameColumn(it)
        }
    }

    private fun observeViewModel(){
        viewModel.shouldCloseActivity.observe(this){
            finish()
        }
        viewModel.gameColumn.observe(this){
            scoreAdapter.submitList(it)
        }
        viewModel.gameItem.observe(this){
            gameItem = it
            binding.tvTarget.text = it.targetOfScore.toString()
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