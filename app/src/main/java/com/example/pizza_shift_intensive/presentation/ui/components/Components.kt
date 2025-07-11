package com.example.pizza_shift_intensive.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.pizza_shift_intensive.R

@Composable
fun Title(modifier: Modifier = Modifier, text: String = stringResource(R.string.default_title)) {
    Text(
        text = text,
        modifier = modifier.padding(start = 70.dp, top = 50.dp),
        style = TextStyle(
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            fontWeight = FontWeight.SemiBold,
        )
    )
}

@Composable
fun ItemImage(url: String, size: Dp = 96.dp) {
    SubcomposeAsyncImage(
        model = url,
        contentDescription = "",
        loading = {
            CircularProgressIndicator(
                strokeWidth = 2.dp,
                modifier = Modifier.size(24.dp)
            )
        },
        error = {
            CircularProgressIndicator(
                strokeWidth = 2.dp,
                modifier = Modifier.size(24.dp)
            )
        },
        modifier = Modifier
            .size(size = size)
            .padding(end = 16.dp)
    )
}

@Composable
fun FullScreenProgressIndicator(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            strokeWidth = 4.dp,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun ErrorMessage(
    message: String = stringResource(R.string.error_unknown),
    onRetry: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {},
        title = { Text(text = stringResource(R.string.error_dialog_title)) },
        text = { Text(text = message) },
        confirmButton = {
            Button(onClick = onRetry) {
                Text(text = stringResource(R.string.error_retry_button))
            }
        },
        modifier = Modifier,
    )
}