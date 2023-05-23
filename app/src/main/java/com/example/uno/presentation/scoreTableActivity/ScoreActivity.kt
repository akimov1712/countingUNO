package com.example.uno.presentation.scoreTableActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.uno.R
import com.example.uno.data.AppDatabase
import com.example.uno.data.consts.Names
import com.example.uno.databinding.ActivityScoreBinding
import com.example.uno.domain.entity.Game
import com.example.uno.presentation.addActivity.AddActivity

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

    override fun onResume() {
        super.onResume()
        viewModel.getGame(gameId)
    }

    private fun initViews() {
        binding.tvNumberOfGame.text = "№ $gameId"
        binding.btnCloseActivity.setOnClickListener {
            finish()
        }
        initButtonsAddScore()
    }

    private fun initButtonsAddScore() {
        binding.btnAddScoreTyomik.setOnClickListener {
            val intent = AddActivity.newIntentAddActivity(this, gameId, Names.TYOMIK)
            startActivity(intent)
        }
        binding.btnAddScoreMakson.setOnClickListener {
            val intent = AddActivity.newIntentAddActivity(this, gameId, Names.MAKSON)
            startActivity(intent)
        }
        binding.btnAddScoreArtem.setOnClickListener {
            val intent = AddActivity.newIntentAddActivity(this, gameId, Names.ARTEM)
            startActivity(intent)
        }
        binding.btnAddScoreSamurai.setOnClickListener {
            val intent = AddActivity.newIntentAddActivity(this, gameId, Names.SAMURAI)
            startActivity(intent)
        }
    }

    private fun initViewModel() {
        database = AppDatabase.getInstance(this)
        viewModel = ViewModelProvider(this, ScoreViewModelFactory(database))[ScoreViewModel::class.java]
        observeViewModel()
        viewModel.getGame(gameId)
    }

    private fun observeViewModel() {
        viewModel.shouldCloseActivity.observe(this) {
            finish()
        }
        viewModel.finishGame.observe(this){
            binding.apply {
                btnAddScoreTyomik.setOnClickListener { toastFinishError() }
                btnAddScoreMakson.setOnClickListener { toastFinishError() }
                btnAddScoreArtem.setOnClickListener { toastFinishError() }
                btnAddScoreSamurai.setOnClickListener { toastFinishError() }
            }
        }
        viewModel.gameItem.observe(this){
            gameItem = it
            binding.tvTarget.text = it.targetOfScore.toString()
            scoreAdapter.submitList(it.listColumns)
            binding.rvScore.post {
                if (it.listColumns.size != 0) {
                    binding.rvScore.smoothScrollToPosition(it.listColumns.size - 1)
                }
            }
        }
    }

    private fun parseIntent(){
        val intent = intent
        val id = intent.getIntExtra(KEY_ID, 99)
        gameId = id
    }

    private fun setupRecyclerView(){
        val rvScore = findViewById<RecyclerView>(R.id.rv_score)
        scoreAdapter = ScoreAdapter()
        with(rvScore) {
            adapter = scoreAdapter
        }
    }

    private fun toastFinishError(){
        Toast.makeText(this@ScoreActivity, "Игра окончена", Toast.LENGTH_SHORT).show()
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