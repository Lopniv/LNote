package com.android.lnote.feature.persentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.android.lnote.feature.persentation.home.HomeScreen
import com.android.lnote.feature.persentation.note.AddNoteScreen

@Composable
fun MainScreenNavigation(
    navController: NavHostController,
    homeState: MutableState<Boolean>)
{
    NavHost(navController, startDestination = Screen.Home.route)
    {

        //home
        composable(Screen.Home.route)
        {
            LaunchedEffect(Unit)
            {
                homeState.value = true
            }
            HomeScreen(navController)
        }

        //add note
        composable(
            route = Screen.AddNote.route +
                    "?noteId={noteId}",
            arguments = listOf(
                navArgument(
                    name = "noteId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        )
        {
            LaunchedEffect(Unit)
            {
                homeState.value = false
            }
            AddNoteScreen(navController)
        }
    }
}