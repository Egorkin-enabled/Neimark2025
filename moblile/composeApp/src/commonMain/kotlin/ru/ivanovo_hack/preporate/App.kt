package ru.ivanovo_hack.preporate

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import preporate.composeapp.generated.resources.*
import ru.ivanovo_hack.preporate.theme.AppTheme
import ru.ivanovo_hack.preporate.theme.LocalThemeIsDark
import kotlinx.coroutines.isActive
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ru.ivanovo_hack.preporate.features.home.HomeScreen
import ru.ivanovo_hack.preporate.features.signIn.SignInScreen
import ru.ivanovo_hack.preporate.navigation.AppScreens
import ru.seventhrombus.app.navigation.LocalNavHost

@Preview
@Composable
internal fun App(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.route ?: AppScreens.SignIn.title

    CompositionLocalProvider(
        LocalNavHost provides navController,
    ){
        NavHost(
            navController = navController,
            startDestination = currentScreen,
        ){
            composable(route = AppScreens.SignIn.title) {
                SignInScreen(navController = navController)
            }
            composable(route = AppScreens.Home.title) {

            }
        }

    }
}
