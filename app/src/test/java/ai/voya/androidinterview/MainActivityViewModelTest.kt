package ai.voya.androidinterview

import ai.voya.androidinterview.main_activity.CharacterListUiState
import ai.voya.androidinterview.main_activity.MainActivityViewModel
import ai.voya.androidinterview.model.CharacterInfo
import ai.voya.androidinterview.model.CharacterResults
import ai.voya.androidinterview.repository.CharacterRepository
import app.cash.turbine.test
import dagger.hilt.android.lifecycle.HiltViewModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

@HiltViewModel
class MainActivityViewModelTest {

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var repository: CharacterRepository
    private val expectedResults = CharacterResults(
        info = CharacterInfo(1, 1, "", ""),
        results = emptyList(),
    )

    @Before
    fun setup() {
        repository = mockk {
            coEvery {
                getCharacters()
            } returns expectedResults
        }
        mainActivityViewModel = MainActivityViewModel(repository)
    }

    @After
    fun cleanUp() {

    }

    @Test
    fun `initial state of ui state is loading`() = runTest {
        mainActivityViewModel.characterListUiState.test {
            val initialValue = awaitItem()
            assert(initialValue == CharacterListUiState.Loading)
        }
    }

    @Test
    fun `ui state is updated with successful result`() = runTest {
        mainActivityViewModel.characterListUiState.test {
            val initialValue = awaitItem()
            val resultValue = awaitItem()


            assert(initialValue == CharacterListUiState.Loading)
            assert(resultValue == CharacterListUiState.Success(expectedResults))
        }
    }
}