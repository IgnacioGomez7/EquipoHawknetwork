package com.example.equipohawknetwork.theme

import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val Light = lightColorScheme(
    primary = Color(0xFF2563EB),
    secondary = Color(0xFF10B981),
    tertiary = Color(0xFFF59E0B)
)
private val Dark = darkColorScheme(
    primary = Color(0xFF93C5FD),
    secondary = Color(0xFF6EE7B7),
    tertiary = Color(0xFFFBBF24)
)

@Composable
fun AppTheme(
    darkTheme: Boolean = false,
    dynamic: Boolean = true,
    content: @Composable () -> Unit
) {
    val ctx = LocalContext.current
    val colors =
        if (dynamic && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (darkTheme) dynamicDarkColorScheme(ctx) else dynamicLightColorScheme(ctx)
        } else {
            if (darkTheme) Dark else Light
        }
    MaterialTheme(colorScheme = colors, content = content)
}
