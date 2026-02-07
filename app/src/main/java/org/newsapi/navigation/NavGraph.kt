package org.newsapi.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.newsapi.feature.news.presentation.detail.ArticleDetailScreen
import org.newsapi.feature.news.presentation.list.NewsListScreen
import org.newsapi.feature.news.presentation.navigation.Screen

@Composable
fun NewsNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.NewsList::class,
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
