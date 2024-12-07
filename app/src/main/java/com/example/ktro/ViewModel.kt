package com.example.ktro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

//class PostViewModel(private val repository: PostRepository) : ViewModel() {
//  //  private val repository = PostRepository()
//
//    private val _posts = MutableStateFlow<List<Post>>(emptyList())
//    val posts: StateFlow<List<Post>> get() = _posts
//
//    private val _isLoading = MutableStateFlow(false)
//    val isLoading: StateFlow<Boolean> get() = _isLoading
//
//
//    private val _errorMessage = MutableStateFlow<String?>(null)
//    val errorMessage: StateFlow<String?> get() = _errorMessage
//
//    init {
//        fetchPosts()
//    }
//
//    private fun fetchPosts() {
//        viewModelScope.launch {
//            _isLoading.value = true
//            try {
//                _posts.value = repository.getPosts()
//            } catch (e: Exception) {
//                // Handle error (e.g., show a Snackbar or Log it)
//            } finally {
//                _isLoading.value = false
//            }
//        }
//    }
//
//
//    fun createPost(post: Post) {
//        viewModelScope.launch {
//            try {
//                val newPost = repository.addPost(post)
//                _posts.value = _posts.value + newPost // Append the new post to the list
//            } catch (e: Exception) {
//                _errorMessage.value = "Failed to create post: ${e.message}"
//            }
//        }
//    }
//
//
//
//}



class PostViewModel(private val repository: PostRepository) : ViewModel() {

    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> get() = _posts

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    init {
        fetchPosts()
    }

    // Add this function to get the next ID
    fun getNextId(posts: List<Post>): Int {
        return if (posts.isEmpty()) 1 else posts.maxOf { it.id } + 1
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _posts.value = repository.getPosts()
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Update the createPost method to use the new ID
    fun createPost(post: Post) {
        viewModelScope.launch {
            try {
                val newId = getNextId(_posts.value)
                val newPost = post.copy(id = newId) // Assign new ID
                val createdPost = repository.addPost(newPost)
                _posts.value = _posts.value + createdPost
            } catch (e: Exception) {
                _errorMessage.value = "Failed to create post: ${e.message}"
            }
        }
    }

    // Add the deletePost method to delete posts
    fun deletePost(id: Int) {
        viewModelScope.launch {
            try {
                repository.deletePost(id)
                _posts.value = _posts.value.filter { it.id != id }
            } catch (e: Exception) {
                _errorMessage.value = "Failed to delete post: ${e.message}"
            }
        }
    }
}
