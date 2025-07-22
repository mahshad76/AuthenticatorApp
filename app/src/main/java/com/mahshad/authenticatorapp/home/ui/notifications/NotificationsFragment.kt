package com.mahshad.authenticatorapp.home.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mahshad.authenticatorapp.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private lateinit var _binding: FragmentNotificationsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNotificationsBinding.inflate(inflater)
        val root: View = _binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}