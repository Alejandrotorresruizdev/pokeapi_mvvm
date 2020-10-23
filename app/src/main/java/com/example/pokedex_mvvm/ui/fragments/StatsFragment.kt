package com.example.pokedex_mvvm.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pokedex_mvvm.R
import kotlinx.android.synthetic.main.stats_fragment.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DemoObjectFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StatsFragment : Fragment(R.layout.stats_fragment) {

    companion object{
        private const val ARG_OBJECT = "object"
    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.stats_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("BUNDLEMAGICO","BUNDLE  "+arguments)
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            textView.text = "Fragmento "+getInt(ARG_OBJECT).toString()
            //textView2.text = "Fragmento "+getInt(ARG_OBJECT).toString()
            Log.e("BUNDLEMAGICO","BUNDLE  "+getInt(ARG_OBJECT).toString())
        }


    }






}