package com.example.luxi.weatherapp.ui.activities

import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.support.v7.widget.RecyclerView
import android.widget.Toolbar
import com.example.luxi.weatherapp.R
import com.example.luxi.weatherapp.extensions.ctx
import com.example.luxi.weatherapp.extensions.slideEnter
import com.example.luxi.weatherapp.extensions.slideExit
import com.example.luxi.weatherapp.ui.App
import org.jetbrains.anko.toast

interface ToolbarManager {

    val toolbar: android.support.v7.widget.Toolbar

    var toolbarTitle: String
    get() = toolbar.title.toString()
    set(value){
        toolbar.title = value
    }

    fun initToolbar(){
        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setOnMenuItemClickListener{
            when(it.itemId){
                R.id.action_settings -> App.instance.toast(toolbarTitle)
                else -> App.instance.toast("Unknown Option")
            }
            true
        }
    }

    fun enableHomeAsUp(up: () -> Unit){
        toolbar.navigationIcon = createUpDrawable()
        toolbar.setNavigationOnClickListener { up() }
    }

    private fun createUpDrawable() = DrawerArrowDrawable(toolbar.ctx).apply { progress = 1f }

    fun attachToScroll(recyclerView: RecyclerView){
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) toolbar.slideExit() else toolbar.slideEnter()
            }
        })
    }
}