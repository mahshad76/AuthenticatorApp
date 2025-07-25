package com.mahshad.authenticatorapp.home.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.widget.textChanges
import com.mahshad.authenticatorapp.databinding.FragmentHomeBinding
import com.mahshad.authenticatorapp.home.data.home.model.repository.Article
import dagger.android.support.AndroidSupportInjection
import io.reactivex.Observable
import javax.inject.Inject

class HomeFragment : Fragment(), HomeContract.View {

    private lateinit var _binding: FragmentHomeBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var searchBar: EditText
    private lateinit var searchBarObservable: Observable<CharSequence>
    private lateinit var myContext: Context

    @Inject
    lateinit var presenter: HomeContract.Presenter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        myContext = context
        presenter.attachView(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater)
        recyclerView = _binding.articleRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(myContext)
        progressBar = _binding.loadingProgressBar
        searchBar = _binding.searchEditText
        searchBarObservable = searchBar.textChanges()
        val root: View = _binding.root
        presenter.getArticles(searchBarObservable)
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

    override fun showArticles(articles: List<Article>) {
        recyclerView.adapter = ArticleAdapter(articles)
    }

    override fun showErrorMessage(error: String) {
        TODO("Not yet implemented")
    }

    override fun showSuccessMessage() {
        TODO("Not yet implemented")
    }
}