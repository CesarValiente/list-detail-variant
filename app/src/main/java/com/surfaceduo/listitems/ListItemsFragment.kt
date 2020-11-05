/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License.
 *
 */

package com.surfaceduo.listitems

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.microsoft.device.dualscreen.core.ScreenHelper
import com.surfaceduo.listitems.recyclerview.Item
import com.surfaceduo.listitems.recyclerview.ItemsAdapter

class ListItemsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var myLayoutManager: RecyclerView.LayoutManager
    private lateinit var myAdapter: ItemsAdapter

    private val sharedVM: SharedVM by activityViewModels()

    private val dataset = arrayOf(
        Item(1, "This is number 1"),
        Item(2, "This is number 2"),
        Item(3, "This is number 3"),
        Item(4, "This is number 4"),
        Item(5, "This is number 5"),
        Item(6, "This is number 6"),
        Item(7, "This is number 7"),
        Item(8, "This is number 8"),
        Item(9, "This is number 9"),
        Item(10, "This is number 10"),
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.list_items_layout, container, false)
        myAdapter = ItemsAdapter(dataset, ::onClickListener, sharedVM)
        myLayoutManager = LinearLayoutManager(requireContext())
        recyclerView = view.findViewById<RecyclerView>(R.id.list_items).apply {
            setHasFixedSize(true)
            layoutManager = myLayoutManager
            adapter = myAdapter
        }

        //We set the first item as default just in case we span the app without having clicked on an item
        if (sharedVM.selectedItemPosition.value == -1) {
            sharedVM.selectedItem.value = dataset[0]
            sharedVM.selectedItemPosition.value = 0
        }

        //We hide the back action when from the toolbar when we are on single screen mode (just in case it was added
        //before when we showed the detail view)
        if (!ScreenHelper.isDualMode(requireContext())) {
            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
        return view
    }

    private fun onClickListener() {
        activity?.run {
            if (!ScreenHelper.isDualMode(this)) {
                //We replace this fragment for the detail view when we click on an item and we are in single screen mode
                parentFragmentManager.beginTransaction()
                    .replace(R.id.first_container_id, DetailFragment(), "detailFragment")
                    .addToBackStack("detailFragmentBackStack")
                    .commit()
            }
        }
    }
}