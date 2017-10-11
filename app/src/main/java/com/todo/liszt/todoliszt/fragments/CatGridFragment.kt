package com.todo.liszt.todoliszt.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.todo.liszt.todoliszt.R


/**
 * A simple [Fragment] subclass.
 */
class CatGridFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_cat_grid, container, false)
    }

}// Required empty public constructor
