package com.example.pokedex_mvvm.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pokedex_mvvm.ui.fragments.DemoObjectFragment
import com.example.pokedex_mvvm.ui.fragments.DetailFragment
import com.example.pokedex_mvvm.ui.fragments.ListFragment


class ViewPageAdapter(fa:  FragmentActivity)  : FragmentStateAdapter(fa) {

    companion object{
        private const val ARG_OBJECT = "object"
    }

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {

        /* return when(position){
             0 -> {ListFragment()}
             1 -> {DetailFragment()}
            else -> ListFragment()
         }*/
       val fragment = DemoObjectFragment()
        fragment.arguments = Bundle().apply {
            // Our object is just an integer :-P
            putInt(ARG_OBJECT, position + 1)
        }
        return fragment
    }
}

