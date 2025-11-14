package ai.voya.androidinterview.main_activity

import ai.voya.androidinterview.model.CharacterResults
import ai.voya.androidinterview.repository.CharacterRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed class CharacterListUiState {
    data object Loading: CharacterListUiState()
    data class Success(val characterResults: CharacterResults): CharacterListUiState()
}

interface CharacterListViewModel {
    fun loadCharacters()
}

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val rickPository: CharacterRepository,
) : ViewModel(), CharacterListViewModel {

    private val _characterListUiState = MutableStateFlow<CharacterListUiState>(CharacterListUiState.Loading)
    val characterListUiState = _characterListUiState.asStateFlow()

    init {
        loadCharacters()
    }

    override fun loadCharacters() {
        viewModelScope.launch {
            _characterListUiState.emit(CharacterListUiState.Loading)
            val results = rickPository.getCharacters()

            _characterListUiState.emit(CharacterListUiState.Success(results))
        }
    }
}
