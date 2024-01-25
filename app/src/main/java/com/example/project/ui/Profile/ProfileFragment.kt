package com.example.project.ui.Profile

import android.app.AlertDialog
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
import com.example.project.utils.showErrorDialog

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

        viewModel = ViewModelProvider(
            this,
            ProfileFragmentViewModelFactory()
        )[ProfileFragmentViewModel::class.java]

        viewModel.getCurrentUser()

        viewModel.currentUser.observe(viewLifecycleOwner) {
            when(it) {
                is CurrentUserState.Idle -> {

                }
                is CurrentUserState.Loading -> {

                }
                is CurrentUserState.Error -> {
                    showErrorDialog(context, it.error)
                }
                is CurrentUserState.Success -> {
                    val username = binding.root.findViewById<TextView>(R.id.user_name)
                    val id = binding.root.findViewById<TextView>(R.id.user_id)

                    username.text = it.user.fullName;
                    id.text = it.user.id;
                }
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}