package org.newsapi.feature.news.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.newsapi.feature.news.presentation.domain.GetArticleByUrlUseCase
import javax.inject.Inject

@HiltViewModel
class ArticleDetailViewModel
    @Inject
    constructor(
        savedStateHandle: SavedStateHandle,
        private val getArticleByUrlUseCase: GetArticleByUrlUseCase,
    ) : ViewModel() {
        private val articleUrl: String = checkNotNull(savedStateHandle["articleUrl"])

        private val _state = MutableStateFlow<ArticleDetailState>(ArticleDetailState.Loading)
        val state: StateFlow<ArticleDetailState> = _state.asStateFlow()

        init {
            loadArticle()
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
    }
