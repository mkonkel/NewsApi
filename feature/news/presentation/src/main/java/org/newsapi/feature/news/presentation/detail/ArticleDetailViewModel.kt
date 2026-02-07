package org.newsapi.feature.news.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.newsapi.feature.news.presentation.domain.GetArticleByUrlUseCase
import org.newsapi.feature.news.presentation.navigation.Screen
import javax.inject.Inject

@HiltViewModel
class ArticleDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getArticleByUrlUseCase: GetArticleByUrlUseCase,
) : ViewModel() {
    private val articleUrl: String = savedStateHandle.toRoute<Screen.ArticleDetail>().articleUrl

    private val _state = MutableStateFlow<ArticleDetailState>(ArticleDetailState.Loading)
    val state: StateFlow<ArticleDetailState> = _state.asStateFlow()

    init {
        loadArticle()
    }

    fun onScrollChanged(scrollPosition: Int) {
        val currentState = _state.value
        if (currentState is ArticleDetailState.Content) {
            val shouldShowFab = scrollPosition > SCROLL_THRESHOLD
            if (currentState.showFab != shouldShowFab) {
                _state.value = currentState.copy(showFab = shouldShowFab)
            }
        }
    }

    @Suppress("TooGenericExceptionCaught")
    private fun loadArticle() {
        viewModelScope.launch {
            try {
                val article = getArticleByUrlUseCase(articleUrl)
                _state.value =
                    if (article != null) {
                        ArticleDetailState.Content(article)
                    } else {
                        ArticleDetailState.Error("Article not found")
                    }
            } catch (e: Exception) {
                _state.value = ArticleDetailState.Error(e.message ?: "Unknown error")
            }
        }
    }

    private companion object {
        const val SCROLL_THRESHOLD = 100
    }
}
