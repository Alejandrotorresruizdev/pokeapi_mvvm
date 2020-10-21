package com.example.pokedex_mvvm.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.pokedex_mvvm.R
import kotlinx.android.synthetic.main.fragment_demo_object.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DemoObjectFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DemoObjectFragment : Fragment(R.layout.fragment_demo_object) {

    companion object{
        private const val ARG_OBJECT = "object"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_demo_object, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            textView.text = "Fragmento "+getInt(ARG_OBJECT).toString()
            //textView2.text = "Fragmento "+getInt(ARG_OBJECT).toString()
        }
    }

}