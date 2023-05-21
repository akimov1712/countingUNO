
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uno.data.AppDatabase
import com.example.uno.data.UnoRepositoryImpl
import com.example.uno.domain.entity.Game
import com.example.uno.domain.useCases.AddGameUseCase
import com.example.uno.domain.useCases.GetAllGamesUseCase
import com.example.uno.domain.useCases.GetAllUsersUseCase
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel(database: AppDatabase) : ViewModel() {

    private val repository = UnoRepositoryImpl(database)

    private val getAllGamesUseCase = GetAllGamesUseCase(repository)
    private val getAllUsersUseCase = GetAllUsersUseCase(repository)
    private val addGameUseCase = AddGameUseCase(repository)

    private val _shouldCloseModal = MutableLiveData<Unit>()
    val shouldCloseModal: LiveData<Unit>
        get() = _shouldCloseModal

    val gamesList = getAllGamesUseCase.invoke()

    val usersList = getAllUsersUseCase.invoke()

    fun addCreateGame(inputTarget: String?) {
        viewModelScope.launch {
            val target = parseTarget(inputTarget)
            val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
            val currentTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
            val game = Game(target, currentDate, currentTime)
            addGameUseCase.invoke(game)
            finishModal()
        }
    }

    private fun parseTarget(inputTarget: String?): Int {
        return try {
            inputTarget?.trim()?.toInt() ?: DEFAULT_TARGET
        } catch (ex: Exception) {
            DEFAULT_TARGET
        }
    }

    private fun finishModal() {
        _shouldCloseModal.value = Unit
    }

    companion object{
        private const val DEFAULT_TARGET = 500
    }
}
