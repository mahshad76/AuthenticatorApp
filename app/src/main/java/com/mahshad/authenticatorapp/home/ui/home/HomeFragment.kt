package com.mahshad.authenticatorapp.home.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.mahshad.authenticatorapp.databinding.FragmentHomeBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class HomeFragment : Fragment(), HomeContract.View {

    private lateinit var _binding: FragmentHomeBinding
    private lateinit var textView: TextView
    private lateinit var progressBar: ProgressBar

    @Inject
    lateinit var presenter: HomeContract.Presenter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        presenter.attachView(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater)
        //textView = _binding.textHome
        progressBar = _binding.loadingProgressBar
        val root: View = _binding.root
        presenter.getArticles()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun showLoading() {
        progressBar.isVisible = true
    }

    override fun hideLoading() {
        progressBar.isVisible = false
    }

    override fun showArticles(articles: String) {
        Log.d("TAG", "showArticles: successful")
        //textView.text = articles
    }

    override fun showErrorMessage(error: String) {
        TODO("Not yet implemented")
    }

    override fun showSuccessMessage() {
        TODO("Not yet implemented")
    }
}