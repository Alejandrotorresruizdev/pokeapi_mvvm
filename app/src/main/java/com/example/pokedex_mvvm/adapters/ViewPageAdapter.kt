package com.example.pokedex_mvvm.adapters

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pokedex_mvvm.ui.fragments.*


class ViewPageAdapter(fa:  FragmentActivity)  : FragmentStateAdapter(fa) {


    companion object{
        private const val ARG_OBJECT = "object"
    }

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {

        return when(position){
             0 -> {
                 val fragment = DescriptionFragment()
                 fragment.arguments = Bundle().apply {
                     // Our object is just an integer :-P
                     putInt(ARG_OBJECT, position + 1)
                 }
                 return fragment
             }
             1 -> {
                 val fragment = StatsFragment()
                 fragment.arguments = Bundle().apply {
                     // Our object is just an integer :-P
                     putInt(ARG_OBJECT, position + 1)
                 }
                 return fragment

             }
            else -> StatsFragment()
         }
        /* val fragment = DemoObjectFragment()
         fragment.arguments = Bundle().apply {
             // Our object is just an integer :-P
             putInt(ARG_OBJECT, position + 1)
         }
         return fragment*/
    }
}

