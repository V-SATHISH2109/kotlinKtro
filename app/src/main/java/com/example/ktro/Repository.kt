package com.example.ktro

class PostRepository(private val apiClient: ApiClient) {


    suspend fun getPosts(): List<Post> {
        return apiClient.fetchPosts()
    }

    suspend fun addPost(post: Post): Post {
        return apiClient.createPost(post)
    }

    suspend fun deletePost(id: Int) {
        apiClient.deletePost(id)
    }
}
