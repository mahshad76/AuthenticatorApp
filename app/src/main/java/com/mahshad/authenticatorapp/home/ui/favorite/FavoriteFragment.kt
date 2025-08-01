package com.mahshad.authenticatorapp.home.ui.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mahshad.authenticatorapp.databinding.FragmentFavoriteBinding
import com.mahshad.authenticatorapp.home.data.favorite.model.FavoriteArticle
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class FavoriteFragment : DaggerFragment(), FavoriteContract.View {

    private lateinit var _binding: FragmentFavoriteBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var myContext: Context

    @Inject
    lateinit var presenter: FavoriteContract.Presenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        myContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater)
        recyclerView = _binding.favoriteRecyclerView
        recyclerView.layoutManager = GridLayoutManager(myContext, 2)
        return _binding.root
    }

    override fun onDetach() {
        presenter.detachView()
        super.onDetach()
    }

    override fun onDestroyView() {
        presenter.destroyView()
        super.onDestroyView()
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun hideLoading() {
        TODO("Not yet implemented")
    }

    override fun showFavoriteArticles(favoriteArticle: List<FavoriteArticle>) {
        TODO("Not yet implemented")
    }
}