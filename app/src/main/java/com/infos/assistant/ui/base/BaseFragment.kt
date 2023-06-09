package com.infos.assistant.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.infos.assistant.MainActivity

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: Inflate<VB>
) : Fragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun hideToolBar(){
        (activity as MainActivity).hideToolbar()
    }
    fun showToolBar(){
        (activity as MainActivity).showToolbar()
    }

    fun hideBottomBar(){
        (activity as MainActivity).hideNavigationBar()
    }
    fun showBottomBar(){
        (activity as MainActivity).showNavigationBar()
    }
    fun hideTextview(){
        (activity as MainActivity).hideTextview()
    }
    fun showTextview(){
        (activity as MainActivity).showTextview()
    }
    fun changeTextview(amount : Int){
        (activity as MainActivity).changeTextview(amount)
    }
}