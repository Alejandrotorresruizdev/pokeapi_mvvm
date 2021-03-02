package com.example.pokedex_mvvm.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pokedex_mvvm.ui.fragments.*


class ViewPageAdapter(fa:  FragmentActivity)  : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {

        return when(position){
             0 -> {
                 return DescriptionFragment()
             }
             1 -> {
                 return StatsFragment()

             }
            else -> StatsFragment()
         }
    }
}

