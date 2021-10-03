package com.bogdan.score.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bogdan.score.R
import com.bogdan.score.databinding.FragmentMainBinding
import com.bogdan.score.ui.viewmodel.MainFragmentViewModel
import com.bogdan.score.ui.viewmodel.MainViewModelFactory


class MainFragment : Fragment() {

    private lateinit var fragmentViewModel: MainFragmentViewModel
    private lateinit var progressBar: ProgressBar

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setupViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentMainBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main,
            container,
            false
        )
        val view = binding.root

        binding.lifecycleOwner = this
        setupObservers(binding, view)
        return view
    }

    private fun setupObservers(binding: FragmentMainBinding, view: View) {
        progressBar = view.findViewById(R.id.progress_bar)

        fragmentViewModel.maxUserScore.observe(requireActivity(), { maxUserScore ->
            progressBar.max = maxUserScore.toInt()
            binding.maxUserScore.text =
                String.format(view.context.getString(R.string.max_user_score), maxUserScore)
        })

        fragmentViewModel.userScore.observe(requireActivity(), { userScore ->
            progressBar.progress = userScore.toInt()
            binding.currentUserScore.text = userScore
        })

        fragmentViewModel.errorLiveData.observe(requireActivity(), { throwable ->
            binding.errorText.text = String.format(view.context.getString(R.string.error_message),
                throwable.getError().toString(),
                throwable.getDeveloperMessage()
            )
        })
    }

    override fun onStart() {
        super.onStart()
        fragmentViewModel.getUpdatedView()
    }

    private fun setupViewModel() {
        val factory = MainViewModelFactory()
        fragmentViewModel = ViewModelProvider(this, factory).get(MainFragmentViewModel::class.java)
    }

    companion object {
        const val FRAGMENT_TAG = "MAIN_FRAGMENT_TAG"
    }
}