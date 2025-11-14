package ai.voya.androidinterview

import ai.voya.androidinterview.main_activity.MainActivityViewModel
import ai.voya.androidinterview.ui.CharacterList
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

sealed class MainNavigationRoutes(val route: String) {
    data object CharacterList: MainNavigationRoutes("character-list")
    data object CharacterDetails: MainNavigationRoutes("character-details")
}

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    ) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = MainNavigationRoutes.CharacterList.route) {
        composable(MainNavigationRoutes.CharacterList.route) {
            val viewModel = hiltViewModel<MainActivityViewModel>()
            val uiState by viewModel.characterListUiState.collectAsStateWithLifecycle()

            CharacterList(
                uiState = uiState,
            )
        }
        composable(MainNavigationRoutes.CharacterDetails.route) {

        }
    }
}