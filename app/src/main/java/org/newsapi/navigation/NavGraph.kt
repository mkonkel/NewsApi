package org.newsapi.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.newsapi.feature.news.presentation.detail.ArticleDetailScreen
import org.newsapi.feature.news.presentation.list.NewsListScreen
import org.newsapi.feature.news.presentation.navigation.Screen

@Composable
fun NewsNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.NewsList::class,
        modifier = modifier,
    ) {
        composable<Screen.NewsList> {
            NewsListScreen(
                onArticleClick = { articleUrl ->
                    navController.navigate(Screen.ArticleDetail(articleUrl))
                },
            )
        }

        composable<Screen.ArticleDetail> {
            ArticleDetailScreen(
                onBackClick = {
                    navController.popBackStack()
                },
            )
        }
    }
}
