/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License.
 *
 */

package com.surfaceduo.listitems

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.surfaceduo.listitems.recyclerview.Item

class SharedVM : ViewModel() {
    val selectedItem = MutableLiveData<Item>()
    val selectedItemPosition = MutableLiveData<Int>()

    init {
        //Initial position set to this value in order to see when we never clicked on an item yet.
        selectedItemPosition.value = -1
    }
}