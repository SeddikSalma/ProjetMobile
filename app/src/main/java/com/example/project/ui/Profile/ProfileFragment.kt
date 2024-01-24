package com.example.project.ui.Profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.project.R
import com.example.project.databinding.FragmentProfileBinding
import com.example.project.ui.Posts.PostsFragmentViewModel
import com.example.project.ui.Posts.PostsFragmentViewModelFactory

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel: ProfileFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.root.findViewById<TextView>(R.id.user_name)

        viewModel = ViewModelProvider(
            this,
            ProfileFragmentViewModelFactory()
        )[ProfileFragmentViewModel::class.java]



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}