package com.mahshad.authenticatorapp.home.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.mahshad.authenticatorapp.databinding.FragmentHomeBinding
import com.mahshad.authenticatorapp.home.data.home.model.remote.RootDTO

class HomeFragment : Fragment(), HomeContract.View {

    private lateinit var _binding: FragmentHomeBinding
    private lateinit var textView: TextView

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater)
        textView = _binding.textHome
        val root: View = _binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun hideLoading() {
        TODO("Not yet implemented")
    }

    override fun showArticles(articles: List<RootDTO?>?) {
        TODO("Not yet implemented")
    }

    override fun showErrorMessage(error: String) {
        TODO("Not yet implemented")
    }

    override fun showSuccessMessage() {
        TODO("Not yet implemented")
    }
}