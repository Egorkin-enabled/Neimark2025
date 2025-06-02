package ru.ivanovo_hack.preporate.features.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Recommend
import androidx.compose.material.icons.filled.Topic
import androidx.compose.material.icons.outlined.Analytics
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material.icons.outlined.Message
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Recommend
import androidx.compose.material.icons.outlined.Topic
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import preporate.composeapp.generated.resources.Res
import preporate.composeapp.generated.resources.about_teacher
import preporate.composeapp.generated.resources.analitics
import preporate.composeapp.generated.resources.awards
import preporate.composeapp.generated.resources.chat
import preporate.composeapp.generated.resources.courses
import preporate.composeapp.generated.resources.grade
import preporate.composeapp.generated.resources.rating
import preporate.composeapp.generated.resources.recomendations
import ru.ivanovo_hack.preporate.common.userApi.domain.models.UserRole
import ru.ivanovo_hack.preporate.features.aboutTeaccherScreen.AboutTeacherScreen
import ru.ivanovo_hack.preporate.features.analytics.AnalyticsScreen
import ru.ivanovo_hack.preporate.features.awards.AwardsScreen
import ru.ivanovo_hack.preporate.features.chat.ChatScreen
import ru.ivanovo_hack.preporate.features.disciplines.DisciplinesScreen
import ru.ivanovo_hack.preporate.features.recommendationsScreen.RecommendationsScreen
import ru.ivanovo_hack.preporate.features.teacherSwiper.TeacherSwiperScreen
import ru.ivanovo_hack.preporate.navigation.AppScreens

data class NavigationItem(
    val title: StringResource,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

val studentsNavItems = listOf(
    NavigationItem(
        title = Res.string.courses,
        route = AppScreens.Disciplins.title,
        selectedIcon = Icons.Filled.Book,
        unselectedIcon = Icons.Outlined.Book
    ),
    NavigationItem(
        title = Res.string.awards,
        route = AppScreens.Disciplins.title,
        selectedIcon = Icons.Filled.Circle,
        unselectedIcon = Icons.Outlined.Circle
    ),
    NavigationItem(
        title = Res.string.chat,
        route = AppScreens.Chat.title,
        selectedIcon = Icons.Filled.Message,
        unselectedIcon = Icons.Outlined.Message
    ),
    NavigationItem(
        title = Res.string.grade,
        route = AppScreens.TeacherSlider.title,
        selectedIcon = Icons.Filled.Topic,
        unselectedIcon = Icons.Outlined.Topic
    ),
)

val teacherNavItems = listOf(
    NavigationItem(
        title = Res.string.analitics,
        route = AppScreens.TeacherAnalitics.title,
        selectedIcon = Icons.Filled.Analytics,
        unselectedIcon = Icons.Outlined.Analytics
    ),
    NavigationItem(
        title = Res.string.recomendations,
        route = AppScreens.Recommendations.title,
        selectedIcon = Icons.Filled.Recommend,
        unselectedIcon = Icons.Outlined.Recommend
    ),
    NavigationItem(
        title = Res.string.about_teacher,
        route = AppScreens.AboutTeatcher.title,
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person
    )
)

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel
){

    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    val mainNavController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.onPrimary
            ) {
                val navBackStackEntry by mainNavController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                when (homeViewModel.viewStates().value.user?.userRole) {
                    UserRole.Teather -> teacherNavItems.forEachIndexed { index, navigationItem ->
                        NavigationBarItem(
                            selected = currentDestination?.hierarchy?.any { it.route == navigationItem.route } == true,
                            onClick = {
                                selectedItemIndex = index
                                mainNavController.navigate(navigationItem.route) {
                                    popUpTo(mainNavController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    restoreState = true
                                }
                            },
                            label = {
                                Text(
                                    text = stringResource(navigationItem.title)
                                )
                            },
                            icon = {
                                Icon(
                                    imageVector = if (currentDestination?.hierarchy?.any { it.route == navigationItem.route } == true) {
                                        navigationItem.selectedIcon
                                    } else {
                                        navigationItem.unselectedIcon
                                    },
                                    contentDescription = "",
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = MaterialTheme.colorScheme.primary,
                            ),
                        )
                    }

                    UserRole.Student -> studentsNavItems.forEachIndexed { index, navigationItem ->
                        NavigationBarItem(
                            selected = currentDestination?.hierarchy?.any { it.route == navigationItem.route } == true,
                            onClick = {
                                selectedItemIndex = index
                                mainNavController.navigate(navigationItem.route) {
                                    popUpTo(mainNavController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    restoreState = true
                                }
                            },
                            label = {
                                Text(
                                    text = stringResource(navigationItem.title)
                                )
                            },
                            icon = {
                                Icon(
                                    imageVector = if (currentDestination?.hierarchy?.any { it.route == navigationItem.route } == true) {
                                        navigationItem.selectedIcon
                                    } else {
                                        navigationItem.unselectedIcon
                                    },
                                    contentDescription = "",
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = MaterialTheme.colorScheme.primary,
                            ),
                        )
                    }

                    null -> {}
                }
            }
        }
    ) { pv ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(pv)
        ) {
            NavHost(navController = mainNavController, startDestination = AppScreens.Disciplins.title){
                composable(AppScreens.Disciplins.title){
                    DisciplinesScreen()
                }
                composable(AppScreens.AboutTeatcher.title) {
                    AboutTeacherScreen()
                }
                composable(AppScreens.TeacherSlider.title) {
                    TeacherSwiperScreen()
                }
                composable(AppScreens.Recommendations.title) {
                    RecommendationsScreen()
                }
                composable(AppScreens.TeacherAnalitics.title) {
                    AnalyticsScreen()
                }
                composable(AppScreens.Chat.title) {
                    ChatScreen()
                }
                composable(AppScreens.StudentAwards.title) {
                    AwardsScreen()
                }
            }
        }
    }
}