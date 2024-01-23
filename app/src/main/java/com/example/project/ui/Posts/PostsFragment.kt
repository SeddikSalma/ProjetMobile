package com.example.project.ui.Posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.project.R
import com.example.project.databinding.FragmentPostsBinding
import com.example.project.ui.components.CreatePostComponent
import com.example.project.ui.components.post.PostComponent

class PostsFragment : Fragment() {

    private var _binding: FragmentPostsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val postsViewModel =
            ViewModelProvider(this).get(PostsViewModel::class.java)

        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        root.apply {
            findViewById<ComposeView>(R.id.posts_compose_view).setContent {
                PostsPage()
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

@Preview()
@Composable()
fun PostsPage(){
    val posts = listOf("a", "b", "c")
    return LazyColumn {
        item {
            CreatePostComponent()
        }
        items(posts.size){ index ->
            PostComponent(posts[index])
        }
    }
}
