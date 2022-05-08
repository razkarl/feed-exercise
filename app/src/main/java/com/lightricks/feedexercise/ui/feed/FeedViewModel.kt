package com.lightricks.feedexercise.ui.feed

import android.content.Context
import androidx.lifecycle.*
import com.lightricks.feedexercise.data.FeedItem
import com.lightricks.feedexercise.data.FeedRepository
import com.lightricks.feedexercise.util.Event
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

/**
 * This view model manages the data for [FeedFragment].
 */
open class FeedViewModel(
    private val feedRepository: FeedRepository) : ViewModel() {
    private val stateInternal: MutableLiveData<State> = MutableLiveData(DEFAULT_STATE)
    private val networkErrorEvent: MutableLiveData<Event<String>> = MutableLiveData()

    fun getIsLoading(): LiveData<Boolean> =
        Transformations.map(stateInternal) { state -> state.isLoading }

    fun getIsEmpty(): LiveData<Boolean> =
        Transformations.map(stateInternal) { state -> state.feedItems.isNullOrEmpty() }

    fun getFeedItems(): LiveData<List<FeedItem>> =
        Transformations.map(stateInternal) { state -> state.feedItems ?: emptyList() }

    fun getNetworkErrorEvent(): LiveData<Event<String>> = networkErrorEvent

    init {
        collectFeedItems()
        refresh()
    }

    private fun collectFeedItems() {
        viewModelScope.launch {
            feedRepository.feedItemsFlow().collect { feedItems ->
                updateState { copy(feedItems = feedItems) }
            }
        }
    }

    fun refresh() {
        if (getState().isLoading) {
            return
        }
        updateFeedItems()
    }

    private fun updateFeedItems() {
        viewModelScope.launch {
            updateState { copy(isLoading = true) }
            try {
                feedRepository.refreshFeedItems()
            } catch (exception: Exception){
                networkErrorEvent.value = Event("Failed to refresh")
            } finally {
                updateState { copy(isLoading = false) }
            }
        }
    }

    private fun updateState(transform: State.() -> State) {
        stateInternal.value = transform(getState())
    }

    private fun getState(): State {
        return stateInternal.value!!
    }

    data class State(
        val feedItems: List<FeedItem>?,
        val isLoading: Boolean)

    companion object {
        private val DEFAULT_STATE = State(
            feedItems = null,
            isLoading = false)
    }
}

/**
 * This class creates instances of [FeedViewModel].
 * It's not necessary to use this factory at this stage. But if we will need to inject
 * dependencies into [FeedViewModel] in the future, then this is the place to do it.
 */
class FeedViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (!modelClass.isAssignableFrom(FeedViewModel::class.java)) {
            throw IllegalArgumentException("factory used with a wrong class")
        }
        @Suppress("UNCHECKED_CAST")
        return FeedViewModel(FeedRepository.instance) as T
    }
}
