package ai.voya.androidinterview.main_activity

import ai.voya.androidinterview.repository.CharacterRepository
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val rickPository: CharacterRepository,
) : ViewModel()