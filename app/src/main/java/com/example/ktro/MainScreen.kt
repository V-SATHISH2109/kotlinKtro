//package com.example.ktro
//
//import PostList
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import org.koin.androidx.compose.koinViewModel
//
//@Composable
//fun MainScreen(viewModel: PostViewModel = koinViewModel()) {
//    val posts = viewModel.posts.collectAsState()
//    val isLoading = viewModel.isLoading.collectAsState()
//    val errorMessage = viewModel.errorMessage.collectAsState()
//
//    var title by remember { mutableStateOf("") }
//    var body by remember { mutableStateOf("") }
//
//    Scaffold(
//        topBar = {
////            TopAppBar(
////                title = { Text("Ktor + Jetpack Compose") }
////            )
//        }
//    ) { paddingValues ->
//        Column(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
//            // Input fields to add a new post
//            OutlinedTextField(
//                value = title,
//                onValueChange = { title = it },
//                label = { Text("Title") },
//                modifier = Modifier.fillMaxWidth().padding(16.dp)
//            )
//            OutlinedTextField(
//                value = body,
//                onValueChange = { body = it },
//                label = { Text("Body") },
//                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
//            )
//            Button(
//                onClick = { viewModel.addPost(title, body) },
//                modifier = Modifier.padding(16.dp).align(Alignment.End)
//            ) {
//                Text("Add Post")
//            }
//
//            // Show a loading spinner if data is being fetched
//            if (isLoading.value) {
//                CircularProgressIndicator(modifier = Modifier.fillMaxWidth())
//            } else {
//                // Show any error message if present
//                if (errorMessage.value != null) {
//                    Text(
//                        text = errorMessage.value!!,
//                        color = MaterialTheme.colorScheme.error,
//                        modifier = Modifier.padding(16.dp)
//                    )
//                }
//
//                // Show the list of posts
//                PostList(posts = posts.value)
//            }
//        }
//    }
//}
