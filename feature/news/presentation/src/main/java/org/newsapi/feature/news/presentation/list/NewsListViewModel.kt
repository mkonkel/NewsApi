package org.newsapi.feature.news.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.newsapi.feature.news.presentation.domain.GetArticlesUseCase
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val getArticles: GetArticlesUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow<NewsListState>(NewsListState.Loading)
    val state: StateFlow<NewsListState> = _state.asStateFlow()

    init {
        loadArticles()
    }

    fun refresh() {
        loadArticles(forceRefresh = true)
    }

    @Suppress("TooGenericExceptionCaught")
    private fun loadArticles(forceRefresh: Boolean = false) {
        viewModelScope.launch {
            val currentState = _state.value
            if (currentState is NewsListState.Content && forceRefresh) {
                _state.value = currentState.copy(isRefreshing = true)
            } else {
                _state.value = NewsListState.Loading
            }

            try {
                val articles = getArticles(forceRefresh)
                _state.value =
                    if (articles.isEmpty()) {
                        NewsListState.Empty
                    } else {
                        NewsListState.Content(articles)
                    }
            } catch (e: Exception) {
                _state.value = NewsListState.Error("Something went wrong.")
            }
        }
    }
}
