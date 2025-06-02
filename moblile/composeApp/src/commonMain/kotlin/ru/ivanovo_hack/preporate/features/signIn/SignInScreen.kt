package ru.ivanovo_hack.preporate.features.signIn

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import preporate.composeapp.generated.resources.Res
import preporate.composeapp.generated.resources.app_name_first
import preporate.composeapp.generated.resources.app_name_second
import preporate.composeapp.generated.resources.email
import preporate.composeapp.generated.resources.enter
import preporate.composeapp.generated.resources.logo1
import preporate.composeapp.generated.resources.logo2
import preporate.composeapp.generated.resources.password
import preporate.composeapp.generated.resources.sign_in
import ru.ivanovo_hack.preporate.common.ui.TextInput
import ru.ivanovo_hack.preporate.features.signIn.models.SignInViewAction
import ru.ivanovo_hack.preporate.features.signIn.models.SignInViewEvent
import ru.ivanovo_hack.preporate.navigation.AppScreens
import ru.ivanovo_hack.preporate.theme.AppTheme

@Composable
fun SignInScreen(
    navController: NavController,
    signInViewModel: SignInViewModel = koinViewModel(),
){
    val state = signInViewModel.viewStates().collectAsState()
    val actions = signInViewModel.viewActions().collectAsState(null)

    LaunchedEffect(actions.value){
        when(actions.value){
            is SignInViewAction.goToHomeScreen -> {
                delay(1000L)
                navController.navigate(AppScreens.Home.title){
                    popUpTo(AppScreens.SignIn.title){
                        inclusive = true
                    }
                    launchSingleTop = true
                    restoreState = false
                }
            }
            is SignInViewAction.showErrorEmail -> TODO()
            is SignInViewAction.showErrorPassword -> TODO()
            null -> {}
        }
    }

    LaunchedEffect(Unit){
        signInViewModel.obtainEvent(SignInViewEvent.onLaunchScreen())
    }

    Surface {
        Box{
            SignInView(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surfaceContainerLowest)
                    .fillMaxSize(),
                emailValue = state.value.email,
                passwordValue = state.value.password,
                onEmailChanged = {
                    signInViewModel.obtainEvent(SignInViewEvent.onEmailChanged(it))
                },
                onPasswordChanged = {
                    signInViewModel.obtainEvent(SignInViewEvent.onPsswordChanged(it))
                },
                onSignInClick = {
                    signInViewModel.obtainEvent(SignInViewEvent.signInClcik())
                }
            )
        }
    }
}

@Composable
fun SignInView(
    modifier: Modifier = Modifier,
    emailValue: String,
    passwordValue: String,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onSignInClick: () -> Unit,
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Column(
            modifier = Modifier
                .padding(horizontal = 30.dp, vertical = 10.dp)
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = stringResource(Res.string.app_name_first).uppercase(),
                color = MaterialTheme.colorScheme.secondary,
                style = TextStyle(
                    lineHeight = 48.sp,
                    textAlign = TextAlign.Center,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                ),
            )
            Text(
                modifier = Modifier
                    .offset(y = (-20).dp)
                    .padding(end = 30.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.End,
                text = stringResource(Res.string.app_name_second).lowercase(),
                color = Color(255,186,8),
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 36.sp,
                    lineHeight = 36.sp,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline,
                ),
            )
        }
        Box {
            Image(
                modifier = Modifier
                    .zIndex(2f)
                    .offset(-25.dp, -15.dp)
                    .rotate(-20f)
                    .width(120.dp)
                    .align(Alignment.TopEnd),
                painter = painterResource(Res.drawable.logo1),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )

            Image(
                modifier = Modifier
                    .zIndex(2f)
                    .offset(-15.dp, -50.dp)
                    .rotate(-30f)
                    .width(100.dp),
                painter = painterResource(Res.drawable.logo2),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
            )

            Card(
                modifier = Modifier
                    .zIndex(1f)
                    .padding(horizontal = 30.dp)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors().copy(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                ) {
                    Text(
                        text = stringResource(Res.string.enter),
                        style = TextStyle(
                            textAlign = TextAlign.Center,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                        ),
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                    )


                    Spacer(Modifier.height(50.dp))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        TextInput(
                            modifier = Modifier
                                .fillMaxWidth(),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Email,
                                imeAction = ImeAction.Next
                            ),
                            textInputLabel = stringResource(Res.string.email),
                            onValueChanged = onEmailChanged,
                            value = emailValue
                        )

                        Spacer(Modifier.height(10.dp))

                        TextInput(
                            modifier = Modifier
                                .fillMaxWidth(),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Next
                            ),
                            secureText = true,
                            textInputLabel = stringResource(Res.string.password),
                            onValueChanged = onPasswordChanged,
                            value = passwordValue
                        )
                    }


                    Spacer(Modifier.height(30.dp))

                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = onSignInClick,
                        colors = ButtonDefaults.buttonColors().copy(
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ){
                        Text(
                            text = stringResource(Res.string.sign_in),
                            style = TextStyle(
                                textAlign = TextAlign.Center,
                                fontSize = 32.sp,
                            ),
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SignInView_PreView(){
    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    AppTheme {
        SignInView(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surfaceContainerLowest)
                .fillMaxSize(),
            emailValue = email,
            passwordValue = password,
            onEmailChanged = {
                email += it
            },
            onPasswordChanged = {
                password += it
            },
            onSignInClick = {}
        )
    }
}