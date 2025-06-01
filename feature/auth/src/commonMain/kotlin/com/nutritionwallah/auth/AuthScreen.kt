package com.nutritionwallah.auth

import ContentWithMessageBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DividerDefaults.color
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mmk.kmpauth.firebase.google.GoogleButtonUiContainerFirebase
import com.nutritionwallah.auth.components.GoogleButton
import com.nutritionwallah.shared.Alpha
import com.nutritionwallah.shared.BebasNeueFont
import com.nutritionwallah.shared.FontSize
import com.nutritionwallah.shared.Surface
import com.nutritionwallah.shared.SurfaceBrand
import com.nutritionwallah.shared.SurfaceError
import com.nutritionwallah.shared.TextPrimary
import com.nutritionwallah.shared.TextSecondary
import com.nutritionwallah.shared.TextWhite
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel
import rememberMessageBarState

@Composable
fun AuthScreen(
    navigateToHomeGraph: ()->Unit
){
    val scope = rememberCoroutineScope()
    val viewModel: AuthViewModel = koinViewModel()
    var messageBarState = rememberMessageBarState()
    var loading by remember { mutableStateOf(false) }
    Scaffold {padding->
        ContentWithMessageBar(
            contentBackgroundColor = Surface,
            modifier = Modifier.padding(
                top = padding.calculateTopPadding(),
                bottom = padding.calculateBottomPadding()
            ),
            messageBarState = messageBarState,
            errorMaxLines = 2,
            errorContainerColor = SurfaceError,
            errorContentColor = TextWhite,
            successContainerColor = SurfaceBrand,
            successContentColor = TextPrimary
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "NutriSport",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontFamily = BebasNeueFont(),
                        fontSize = FontSize.EXTRA_LARGE,
                        color = TextSecondary
                    )
                    Text(
                        text = "Sign in to continue",
                        modifier = Modifier.fillMaxWidth().alpha(Alpha.HALF),
                        textAlign = TextAlign.Center,
                        fontSize = FontSize.EXTRA_REGULAR,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                }
                GoogleButtonUiContainerFirebase(
                    linkAccount = false,
                    onResult = {result->
                        result.onSuccess {user->
                            viewModel.createCustomer(
                                user = user,
                                onSuccess = {
                                    scope.launch {
                                        messageBarState.addSuccess("Successfully signed in")
                                        delay(2000)
                                        navigateToHomeGraph()
                                    }
                                },
                                onError = {
                                    messageBarState.addError(it)
                                }
                            )
                            loading = false
                        }.onFailure {error->
                            if(error.message?.contains("A network error") == true){
                                messageBarState.addError("Internet connection unavailable.")
                            }else if(error.message?.contains("Idtoken is null")==true){
                                messageBarState.addError("Sign in cancelled")
                            }else{
                                messageBarState.addError(error.message ?: "Unknown")
                            }
                            loading = false
                        }
                    }
                ){
                    GoogleButton(
                        loading = loading,
                        onClicked = {
                            loading = true
                            this@GoogleButtonUiContainerFirebase.onClick()
                        }
                    )
                }
            }
        }
    }
}