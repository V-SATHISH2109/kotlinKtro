
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.ktro.PostViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ktro.Post
import org.koin.androidx.compose.koinViewModel


//@Composable
//fun MainScreen(viewModel: PostViewModel = koinViewModel()) {
//    val posts = viewModel.posts.collectAsState()
//    val isLoading = viewModel.isLoading.collectAsState()
//    val errorMessage = viewModel.errorMessage.collectAsState()
//
//    Scaffold(
//        topBar = {
//          //  TopAppBar(title = { Text("Posts") })
//        }
//    ) { paddingValues ->
//        Column(modifier = Modifier.padding(paddingValues)) {
//            if (isLoading.value) {
//                CircularProgressIndicator(modifier = Modifier.fillMaxWidth())
//            } else {
//                PostForm(onSubmit = { post ->
//                    viewModel.createPost(post)
//                })
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                if (errorMessage.value != null) {
//                    Text(
//                        text = errorMessage.value!!,
//                        color = MaterialTheme.colorScheme.error,
//                        modifier = Modifier.padding(16.dp)
//                    )
//                }
//                PostList(posts = posts.value)
//            }
//        }
//    }
//}
//
@Composable
fun PostForm(onSubmit: (Post) -> Unit) {
    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        BasicTextField(
            value = title,
            onValueChange = { title = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.Yellow)
                .padding(16.dp),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
        )

        BasicTextField(
            value = body,
            onValueChange = { body = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.Blue)
                .padding(16.dp),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    onSubmit(
                        Post(
                            userId = 1,
                            id = 0, // ID will be ignored by JSONPlaceholder
                            title = title,
                            body = body
                        )
                    )
                    title = ""
                    body = ""
                }

            )
        )

        Button(
            onClick = {
                onSubmit(
                    Post(
                        userId = 1,
                        id = 0, // ID will be ignored by JSONPlaceholder
                        title = title,
                        body = body
                    )
                )
                title = ""
                body = ""
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Add Post")
        }
    }
}

//@Composable
//fun PostList(posts: List<Post>) {
//    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
//        items(posts) { post ->
//            PostItem(post = post)
//            Spacer(modifier = Modifier.height(8.dp)) // Add space between items
//        }
//    }
//}

//@Composable
//fun PostItem(post: Post) {
//    Card(
//        modifier = Modifier.fillMaxWidth(),
//        //  elevation = 4.dp
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Text(
//                text = post.title,
//                style = MaterialTheme.typography.titleMedium
//            )
//            Spacer(modifier = Modifier.height(4.dp))
//            Text(
//                text = post.body,
//                style = MaterialTheme.typography.bodyMedium
//            )
//        }
//    }
//}
//






@Composable
fun MainScreen(viewModel: PostViewModel = koinViewModel()) {
    val posts = viewModel.posts.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()
    val errorMessage = viewModel.errorMessage.collectAsState()

    Scaffold(
        topBar = {
            // Add a TopAppBar here if needed
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            if (isLoading.value) {
                CircularProgressIndicator(modifier = Modifier.fillMaxWidth())
            } else {
                PostForm(onSubmit = { post ->
                    viewModel.createPost(post)
                })

                Spacer(modifier = Modifier.height(16.dp))

                if (errorMessage.value != null) {
                    Text(
                        text = errorMessage.value!!,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                PostList(posts = posts.value, onDelete = { id ->
                    viewModel.deletePost(id)
                })
            }
        }
    }
}

@Composable
fun PostList(posts: List<Post>, onDelete: (Int) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(posts) { post ->
            PostItem(post = post, onDelete = onDelete)
            Spacer(modifier = Modifier.height(8.dp)) // Add space between items
        }
    }
}





@Composable
fun PostItem(post: Post, onDelete: (Int) -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = post.title,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = post.body,
                style = MaterialTheme.typography.bodyMedium
            )

            // Delete button
            Button(onClick = { onDelete(post.id) }, modifier = Modifier.padding(top = 16.dp)) {
                Text("Delete Post")
            }
        }
    }
}























































//package com.example.ktro
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.viewmodel.compose.viewModel
//
//
//
//@Composable
//fun PostListScreen(viewModel: PostViewModel = viewModel()) {
//
//    val posts by viewModel.posts.collectAsState()
//    val isLoading by viewModel.isLoading.collectAsState()
//
//
//    Scaffold(
//        topBar = {
////            TopAppBar(
////                title = { Text("Posts") }
////            )
//        }
//    ) { padding ->
//        Box(modifier = Modifier.padding(padding)) {
//            if (isLoading) {
//                CircularProgressIndicator(modifier = Modifier.fillMaxSize())
//            } else {
//                LazyColumn(modifier = Modifier.fillMaxSize()) {
//                    items(posts) { post ->
//                        PostItem(post)
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun PostItem(post: Post) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp),
//        elevation = CardDefaults.cardElevation(4.dp)
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Text(text = post.title, style = MaterialTheme.typography.titleLarge)
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(text = post.body, style = MaterialTheme.typography.bodyMedium)
//        }
//    }
//}
