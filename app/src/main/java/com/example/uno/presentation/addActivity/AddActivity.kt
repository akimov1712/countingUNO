package com.example.uno.presentation.addActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.uno.data.AppDatabase
import com.example.uno.data.consts.Id
import com.example.uno.data.consts.Names
import com.example.uno.databinding.ActivityAddBinding
import com.example.uno.domain.entity.Column
import com.example.uno.domain.entity.Game
import com.example.uno.presentation.scoreTableActivity.ScoreActivity

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding

    private var userName = UNDEFINED_USER
    private var gameId = ScoreActivity.UNDEFINED_ID

    private var gameItem: Game? = null

    private lateinit var database: AppDatabase
    private lateinit var viewModel: AddViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        parseIntent()
        initViewModel()
        initViewListeners()
    }

    private fun initAddScoreListener() {
        binding.tvCountScore.setOnClickListener {
            binding.tvCountScore.text = "0"
        }
        binding.btnAddOne.apply {
            setOnClickListener {
                var countScore = binding.tvCountScore.text.toString().toInt()
                var countAddScore = binding.tvCountAddOne.text.toString().toInt()
                countScore += 1
                countAddScore += 1
                binding.tvCountScore.text = countScore.toString()
                binding.tvCountAddOne.text = countAddScore.toString()
            }
            setOnLongClickListener {
                var countScore = binding.tvCountScore.text.toString().toInt()
                var countAddScore = binding.tvCountAddOne.text.toString().toInt()
                if (countScore > 0 && countAddScore != 0) {
                    countScore -= 1
                    countAddScore -= 1
                    binding.tvCountScore.text = countScore.toString()
                    binding.tvCountAddOne.text = countAddScore.toString()
                }
                true
            }
        }

        binding.btnAddTwo.apply {
            setOnClickListener {
                var countScore = binding.tvCountScore.text.toString().toInt()
                var countAddScore = binding.tvCountAddTwo.text.toString().toInt()
                countScore += 2
                countAddScore += 1
                binding.tvCountScore.text = countScore.toString()
                binding.tvCountAddTwo.text = countAddScore.toString()
            }
            setOnLongClickListener {
                var countScore = binding.tvCountScore.text.toString().toInt()
                var countAddScore = binding.tvCountAddTwo.text.toString().toInt()
                if (countScore > 1&& countAddScore != 0) {
                    countScore -= 2
                    countAddScore -= 1
                    binding.tvCountScore.text = countScore.toString()
                    binding.tvCountAddTwo.text = countAddScore.toString()
                }
                true
            }
        }

        binding.btnAddThree.apply {
            setOnClickListener {
                var countScore = binding.tvCountScore.text.toString().toInt()
                var countAddScore = binding.tvCountAddThree.text.toString().toInt()
                countScore += 3
                countAddScore += 1
                binding.tvCountScore.text = countScore.toString()
                binding.tvCountAddThree.text = countAddScore.toString()
            }
            setOnLongClickListener {
                var countScore = binding.tvCountScore.text.toString().toInt()
                var countAddScore = binding.tvCountAddThree.text.toString().toInt()
                if (countScore > 2&& countAddScore != 0) {
                    countScore -= 3
                    countAddScore -= 1
                    binding.tvCountScore.text = countScore.toString()
                    binding.tvCountAddThree.text = countAddScore.toString()
                }
                true
            }
        }

        binding.btnAddFour.apply {
            setOnClickListener {
                var countScore = binding.tvCountScore.text.toString().toInt()
                var countAddScore = binding.tvCountAddFour.text.toString().toInt()
                countScore += 4
                countAddScore += 1
                binding.tvCountScore.text = countScore.toString()
                binding.tvCountAddFour.text = countAddScore.toString()
            }
            setOnLongClickListener {
                var countScore = binding.tvCountScore.text.toString().toInt()
                var countAddScore = binding.tvCountAddFour.text.toString().toInt()
                if (countScore > 3&& countAddScore != 0) {
                    countScore -= 4
                    countAddScore -= 1
                    binding.tvCountScore.text = countScore.toString()
                    binding.tvCountAddFour.text = countAddScore.toString()
                }
                true
            }
        }

        binding.btnAddFive.apply {
            setOnClickListener {
                var countScore = binding.tvCountScore.text.toString().toInt()
                var countAddScore = binding.tvCountAddFive.text.toString().toInt()
                countScore += 5
                countAddScore += 1
                binding.tvCountScore.text = countScore.toString()
                binding.tvCountAddFive.text = countAddScore.toString()
            }
            setOnLongClickListener {
                var countScore = binding.tvCountScore.text.toString().toInt()
                var countAddScore = binding.tvCountAddFive.text.toString().toInt()
                if (countScore > 4&& countAddScore != 0) {
                    countScore -= 5
                    countAddScore -= 1
                    binding.tvCountScore.text = countScore.toString()
                    binding.tvCountAddFive.text = countAddScore.toString()
                }
                true
            }
        }

        binding.btnAddSix.apply {
            setOnClickListener {
                var countScore = binding.tvCountScore.text.toString().toInt()
                var countAddScore = binding.tvCountAddSix.text.toString().toInt()
                countScore += 6
                countAddScore += 1
                binding.tvCountScore.text = countScore.toString()
                binding.tvCountAddSix.text = countAddScore.toString()
            }
            setOnLongClickListener {
                var countScore = binding.tvCountScore.text.toString().toInt()
                var countAddScore = binding.tvCountAddSix.text.toString().toInt()
                if (countScore > 5&& countAddScore != 0) {
                    countScore -= 6
                    countAddScore -= 1
                    binding.tvCountScore.text = countScore.toString()
                    binding.tvCountAddSix.text = countAddScore.toString()
                }
                true
            }
        }

            binding.btnAddSeven.apply {
                setOnClickListener {
                    var countScore = binding.tvCountScore.text.toString().toInt()
                    var countAddScore = binding.tvCountAddSeven.text.toString().toInt()
                    countScore += 7
                    countAddScore += 1
                    binding.tvCountScore.text = countScore.toString()
                    binding.tvCountAddSeven.text = countAddScore.toString()
                }
                setOnLongClickListener {
                    var countScore = binding.tvCountScore.text.toString().toInt()
                    var countAddScore = binding.tvCountAddSeven.text.toString().toInt()
                    if (countScore > 6&& countAddScore != 0) {
                        countScore -= 7
                        countAddScore -= 1
                        binding.tvCountScore.text = countScore.toString()
                        binding.tvCountAddSeven.text = countAddScore.toString()
                    }
                    true
                }
            }

            binding.btnAddEight.apply {
                setOnClickListener {
                    var countScore = binding.tvCountScore.text.toString().toInt()
                    var countAddScore = binding.tvCountAddEight.text.toString().toInt()
                    countScore += 9
                    countAddScore += 1
                    binding.tvCountScore.text = countScore.toString()
                    binding.tvCountAddEight.text = countAddScore.toString()
                }
                setOnLongClickListener {
                    var countScore = binding.tvCountScore.text.toString().toInt()
                    var countAddScore = binding.tvCountAddEight.text.toString().toInt()
                    if (countScore > 7 && countAddScore != 0) {
                        countScore -= 8
                        countAddScore -= 1
                        binding.tvCountScore.text = countScore.toString()
                        binding.tvCountAddEight.text = countAddScore.toString()
                    }
                    true
                }
            }

            binding.btnAddNine.apply {
                setOnClickListener {
                    var countScore = binding.tvCountScore.text.toString().toInt()
                    var countAddScore = binding.tvCountAddNine.text.toString().toInt()
                    countScore += 9
                    countAddScore += 1
                    binding.tvCountScore.text = countScore.toString()
                    binding.tvCountAddNine.text = countAddScore.toString()
                }
                setOnLongClickListener {
                    var countScore = binding.tvCountScore.text.toString().toInt()
                    var countAddScore = binding.tvCountAddNine.text.toString().toInt()
                    if (countScore > 8&& countAddScore != 0) {
                        countScore -= 9
                        countAddScore -= 1
                        binding.tvCountScore.text = countScore.toString()
                        binding.tvCountAddNine.text = countAddScore.toString()
                    }
                    true
                }
            }

            binding.btnAddSuper.apply {
                setOnClickListener {
                    var countScore = binding.tvCountScore.text.toString().toInt()
                    var countAddScore = binding.tvCountAddSuper.text.toString().toInt()
                    countScore += 20
                    countAddScore += 1
                    binding.tvCountScore.text = countScore.toString()
                    binding.tvCountAddSuper.text = countAddScore.toString()
                }
                setOnLongClickListener {
                    var countScore = binding.tvCountScore.text.toString().toInt()
                    var countAddScore = binding.tvCountAddSuper.text.toString().toInt()
                    if (countScore > 19&& countAddScore != 0) {
                        countScore -= 20
                        countAddScore -= 1
                        binding.tvCountScore.text = countScore.toString()
                        binding.tvCountAddSuper.text = countAddScore.toString()
                    }
                    true
                }
            }
        }

        private fun parseInputScore(score: String?): String {
            return score?.trim() ?: ""
        }

        private fun validateData(score: String, countScore: String): Boolean {
            if (score.isBlank()) {
                if (countScore.toInt() <= 0) {
                    Toast.makeText(
                        this@AddActivity,
                        "Проверьте правильность ввода",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                } else {
                    return true
                }
            } else {
                if (score.toInt() <= 0) {
                    Toast.makeText(
                        this@AddActivity,
                        "Проверьте правильность ввода",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
                return true
            }
            return false
        }

        private fun finishScore(score: String, countScore: String): Int {
            if (score.isBlank()) {
                if (countScore.toInt() <= 0) {
                    Toast.makeText(
                        this@AddActivity,
                        "Проверьте правильность ввода",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                } else {
                    return countScore.toInt()
                }
            } else {
                if (score.toInt() <= 0) {
                    Toast.makeText(
                        this@AddActivity,
                        "Проверьте правильность ввода",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
                return score.toInt()
            }
            return 0
        }

        private fun initViewListeners() {
            binding.btnClose.setOnClickListener {
                finish()
            }
            binding.btnDone.setOnClickListener {
                val score = parseInputScore(binding.etCountScore.text.toString())
                val countScore = binding.tvCountScore.text.toString()
                val validateData = validateData(score, countScore)
                if (validateData) {
                    val finishScore = finishScore(score, countScore)
                    addScore(finishScore)
                    finish()
                }
            }
            initAddScoreListener()
        }

        private fun userAddScore(score: Int, listScores: MutableList<Int>): List<Int> {
            when (userName) {
                Names.TYOMIK -> {
                    val newList = listScores.toMutableList()
                    val newValue = newList[Id.TYOMIK]
                    viewModel.setupScoreOfRecord(Id.TYOMIK,score)
                    newList[Id.TYOMIK] = score + newValue
                    return newList
                }
                Names.MAKSON -> {
                    val newList = listScores.toMutableList()
                    val newValue = newList[Id.MAKSON]
                    viewModel.setupScoreOfRecord(Id.MAKSON,score)
                    newList[Id.MAKSON] = score + newValue
                    return newList
                }
                Names.ARTEM -> {
                    val newList = listScores.toMutableList()
                    val newValue = newList[Id.ARTEM]
                    viewModel.setupScoreOfRecord(Id.ARTEM,score)
                    newList[Id.ARTEM] = score + newValue
                    return newList
                }
                Names.SAMURAI -> {
                    val newList = listScores.toMutableList()
                    val newValue = newList[Id.SAMURAI]
                    viewModel.setupScoreOfRecord(Id.SAMURAI,score)
                    newList[Id.SAMURAI] = score + newValue
                    return newList
                }
                else -> throw RuntimeException("Был передан не допустимый userName: $userName")
            }
        }

        private fun addScore(score: Int) {
            gameItem?.let {
                val column = Column(0)
                if (gameItem?.listColumns?.size == 0) {
                    val listScores = column.scoreList.toMutableList()
                    val newListScores = userAddScore(score, listScores)
                    val newColumn = Column(1, newListScores)
                    viewModel.addColumn(gameItem!!, newColumn)
                } else {
                    val listColumn = gameItem?.listColumns?.get(gameItem?.listColumns!!.size - 1)
                    val listScores = listColumn?.scoreList!!.toMutableList()
                    val newListScores = userAddScore(score, listScores)
                    val newId = listColumn.id.plus(1)
                    val newColumn = newId.let {
                        Column(it, newListScores)
                    }
                    viewModel.addColumn(gameItem!!, newColumn)
                }
            }
        }

        private fun initViewModel() {
            database = AppDatabase.getInstance(this)
            viewModel =
                ViewModelProvider(this, AddViewModelFactory(database))[AddViewModel::class.java]
            observeViewModel()
            viewModel.getGame(gameId)
        }


        private fun observeViewModel() {
            viewModel.gameItem.observe(this) {
                gameItem = it
            }
            viewModel.usersList.observe(this){
                for (item in it){
                    Log.d("users", item.toString())
                }
            }
        }

        private fun parseIntent() {
            if (!intent.hasExtra(GAME_ID_EXTRA)) {
                throw RuntimeException("Не был передан GameId")
            }
            val id = intent.getIntExtra(GAME_ID_EXTRA, ScoreActivity.UNDEFINED_ID)
            if (id == ScoreActivity.UNDEFINED_ID) {
                throw RuntimeException("Был передан не допустимый gameId: $id")
            }
            gameId = id
            if (!intent.hasExtra(USER_EXTRA)) {
                throw RuntimeException("Не был передан User")
            }
            val user = intent.getStringExtra(USER_EXTRA)
            if (user != Names.TYOMIK && user != Names.MAKSON && user != Names.ARTEM && user != Names.SAMURAI) {
                throw RuntimeException("Был передан не допустимый user: $user")
            }
            userName = user
        }

        companion object {

            private const val GAME_ID_EXTRA = "game_extra"
            private const val USER_EXTRA = "user_extra"
            const val UNDEFINED_USER = ""

            fun newIntentAddActivity(context: Context, gameId: Int, user: String): Intent {
                val intent = Intent(context, AddActivity::class.java)
                intent.putExtra(GAME_ID_EXTRA, gameId)
                intent.putExtra(USER_EXTRA, user)
                return intent
            }

        }
    }