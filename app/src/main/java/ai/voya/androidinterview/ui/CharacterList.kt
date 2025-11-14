package ai.voya.androidinterview.ui

import ai.voya.androidinterview.main_activity.CharacterListUiState
import ai.voya.androidinterview.model.Character
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun CharacterList(
    modifier: Modifier = Modifier,
    uiState: CharacterListUiState,
    ) {
    Scaffold { innerPadding ->
        when (uiState) {
            CharacterListUiState.Loading -> { CircularProgressIndicator() }
            is CharacterListUiState.Success -> {
                LazyColumn(
                    modifier = modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                ) {
                    items(uiState.characterResults.results) {
                        CharacterCard(character = it)
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterCard(modifier: Modifier = Modifier, character: Character) {
    Row(
        modifier = modifier.fillMaxWidth().wrapContentHeight(),
    ) {
        AsyncImage(
            modifier = Modifier.size(200.dp),
            model = character.image,
            contentDescription = null,
        )
        Spacer(modifier = modifier.size(10.dp))

        Text(
            modifier = modifier.align(Alignment.CenterVertically),
            text = character.name,
        )
    }
}
