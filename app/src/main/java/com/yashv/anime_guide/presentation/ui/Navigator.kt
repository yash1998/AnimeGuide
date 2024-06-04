package com.yashv.anime_guide.presentation.ui

import androidx.annotation.IdRes
import com.yashv.anime_guide.R
import com.yashv.anime_guide.base.BaseActivity
import com.yashv.anime_guide.base.BaseFragment

fun BaseActivity?.addFrag(
    frag: BaseFragment<*>,
    tag: String,
    addToBS: Boolean = true,
    @IdRes container: Int = R.id.container
) {
    val transaction = this?.supportFragmentManager?.beginTransaction()
        ?.add(container, frag, tag)
    if (addToBS) transaction?.addToBackStack("$tag::transaction")
    transaction?.commit()
}

fun BaseActivity?.replaceFrag(
    frag: BaseFragment<*>,
    tag: String,
    addToBS: Boolean = true,
    @IdRes container: Int = R.id.container
) {
    val transaction = this?.supportFragmentManager?.beginTransaction()
        ?.replace(container, frag, tag)
    if (addToBS) transaction?.addToBackStack("$tag::transaction")
    transaction?.commit()
}

fun BaseActivity?.addRootFrag(
    frag: BaseFragment<*>,
    tag: String,
    @IdRes container: Int = R.id.container
) {
    addFrag(frag, tag, false, container)
}