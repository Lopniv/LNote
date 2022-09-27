package com.android.lnote.feature.persentation.navigation

sealed class Screen(val route: String)
{
    object AddNote: Screen("add_note")
    object Home: Screen("home")

    fun withArgs(vararg args: String): String
    {
        return buildString()
        {
            append(route)
            args.forEach()
            { arg ->
                append("/$arg")
            }
        }
    }
}