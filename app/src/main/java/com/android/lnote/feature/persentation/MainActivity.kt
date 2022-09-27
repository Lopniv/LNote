package com.android.lnote.feature.persentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.android.lnote.feature.persentation.navigation.MainScreenNavigation
import com.android.lnote.feature.persentation.navigation.Screen
import com.android.lnote.ui.theme.LNoteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity()
{
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContent {
            LNoteTheme {
                val homeState = rememberSaveable { (mutableStateOf(false)) }

                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()

                when (navBackStackEntry?.destination?.route)
                {
                    Screen.Home.route ->
                    {
                        homeState.value = true
                    }
                    else ->
                    {
                        homeState.value = false
                    }
                }

                Scaffold(
                    topBar = { TopBar(homeState) },
                    floatingActionButton =
                    {
                        AnimatedVisibilityHome(homeState.value, false)
                        {
                            FloatingActionButton(
                                shape = CircleShape,
                                onClick = { navController.navigate(Screen.AddNote.route) })
                            {
                                Image(Icons.Default.Add, contentDescription = null)
                            }
                        }
                    }
                )
                {
                    MainScreenNavigation(navController = navController, homeState)
                }
            }
        }
    }
}

@Composable
fun AnimatedVisibilityHome(homeState: Boolean, isNegative: Boolean, content: @Composable () -> Unit)
{
    AnimatedVisibility(
        visible = homeState,
        enter = slideInVertically(initialOffsetY = { if (isNegative) (-it) else it }),
        exit = slideOutVertically(targetOffsetY = { if (isNegative) (-it) else it }),
        content =
        {
            content()
        }
    )
}

@Composable
fun TopBar(homeState: MutableState<Boolean>)
{
    AnimatedVisibilityHome(homeState.value, true)
    {
        var isDisplay by remember { mutableStateOf(false) }

        TopAppBar(backgroundColor = Color.Gray, title = { Text("LNote", color = Color.Black) }, actions = {
            IconButton(onClick = { isDisplay = !isDisplay }) {
                Icon(Icons.Default.MoreVert, contentDescription = null)
            }

            DropdownMenu(expanded = isDisplay, onDismissRequest = { isDisplay = false }) {
                DropdownMenuItem(onClick = { })
                {
                    Text(text = "Sort By")
                }
            }
        })
    }
}