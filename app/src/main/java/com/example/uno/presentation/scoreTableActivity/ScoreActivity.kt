package com.example.uno.presentation.scoreTableActivity

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.uno.R
import com.example.uno.data.AppDatabase
import com.example.uno.data.consts.Names
import com.example.uno.databinding.ActivityScoreBinding
import com.example.uno.databinding.ModalDeleteColumnBinding
import com.example.uno.domain.entity.Column
import com.example.uno.domain.entity.Game
import com.example.uno.presentation.addActivity.AddActivity
import com.example.uno.presentation.toasts.ToastFinishGame

class ScoreActivity : AppCompatActivity() {

    private var gameId = UNDEFINED_ID
    private var gameItem: Game? = null

    private lateinit var modalDeleteColumn: Dialog
    private lateinit var bindingModalDeleteColumn: ModalDeleteColumnBinding

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
        createModalDeleteColumn()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getGame(gameId)
    }

    private fun createModalDeleteColumn(){

        modalDeleteColumn = Dialog(this)
        bindingModalDeleteColumn = ModalDeleteColumnBinding.inflate(layoutInflater)
        modalDeleteColumn.setContentView(bindingModalDeleteColumn.root)

    }

    private fun initListenersModalDeleteColumn(column: Column){
        bindingModalDeleteColumn.btnDelete.setOnClickListener {

        }
        bindingModalDeleteColumn.btnCancel.setOnClickListener {
            modalDeleteColumn.dismiss()
        }
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
            ToastFinishGame.toastFinishGame(this)
            binding.apply {
                btnAddScoreTyomik.setOnClickListener { ToastFinishGame.toastFinishGame(this@ScoreActivity) }
                btnAddScoreMakson.setOnClickListener { ToastFinishGame.toastFinishGame(this@ScoreActivity) }
                btnAddScoreArtem.setOnClickListener { ToastFinishGame.toastFinishGame(this@ScoreActivity) }
                btnAddScoreSamurai.setOnClickListener { ToastFinishGame.toastFinishGame(this@ScoreActivity) }
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
        scoreAdapter.onColumnClickListener = {
            modalDeleteColumn.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            modalDeleteColumn.show()
        }
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