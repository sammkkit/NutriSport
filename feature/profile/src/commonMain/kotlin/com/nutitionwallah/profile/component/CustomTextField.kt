package com.nutitionwallah.profile.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.nutritionwallah.shared.Alpha
import com.nutritionwallah.shared.BorderError
import com.nutritionwallah.shared.BorderIdle
import com.nutritionwallah.shared.FontSize
import com.nutritionwallah.shared.SurfaceBrand
import com.nutritionwallah.shared.SurfaceError
import com.nutritionwallah.shared.SurfaceLighter
import com.nutritionwallah.shared.TextPrimary

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String? = null,
    enabled: Boolean = true,
    error: Boolean = false,
    expanded: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text
    )

) {
    val borderColor by animateColorAsState(
        targetValue = if (error == true) BorderError else BorderIdle
    )
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, borderColor, RoundedCornerShape(6.dp)),
        enabled = enabled,
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            if (placeholder != null) {
                Text(
                    text = placeholder,
                    modifier = Modifier.alpha(Alpha.DISABLED),
                    fontSize = FontSize.REGULAR
                )
            } else null
        },
        keyboardOptions = keyboardOptions,
        singleLine = !expanded,
        shape = RoundedCornerShape(6.dp),
        colors = TextFieldDefaults.colors(
            focusedTextColor = TextPrimary,
            unfocusedTextColor = TextPrimary.copy(Alpha.DISABLED),
            focusedContainerColor = SurfaceLighter
        )
    )
}