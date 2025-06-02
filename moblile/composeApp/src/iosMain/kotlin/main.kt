import androidx.compose.ui.window.ComposeUIViewController
import ru.ivanovo_hack.preporate.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
