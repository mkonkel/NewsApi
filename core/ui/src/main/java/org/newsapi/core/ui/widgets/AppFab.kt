package org.newsapi.core.ui.widgets

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.newsapi.core.ui.R
import org.newsapi.core.ui.theme.NewsApiTheme

@Composable
fun AppFab(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Default.Share,
    iconContentDescription: String? = stringResource(id = R.string.share_content_description),
    containerColor: Color = MaterialTheme.colorScheme.secondary,
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        containerColor = containerColor,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = iconContentDescription,
        )
    }
}

@Preview
@Composable
private fun AppFabPreview() {
    NewsApiTheme {
        AppFab(onClick = {})
    }
}
