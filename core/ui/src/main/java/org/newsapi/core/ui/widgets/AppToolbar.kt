package org.newsapi.core.ui.widgets

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.newsapi.core.ui.R
import org.newsapi.core.ui.theme.NewsApiTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolbar(
    title: String,
    showBackButton: Boolean = false,
    onBackClick: () -> Unit = {},
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ChevronLeft,
                        contentDescription = stringResource(id = R.string.back_button_content_description),
                    )
                }
            }
        },
        colors = topAppBarColors(containerColor = backgroundColor),
    )
}

@Preview(showBackground = true)
@Composable
private fun AppToolbarPreview() {
    NewsApiTheme {
        AppToolbar(title = "News")
    }
}

@Preview(showBackground = true)
@Composable
private fun AppToolbarWithBackPreview() {
    NewsApiTheme {
        AppToolbar(
            title = "Article",
            showBackButton = true,
        )
    }
}
