package com.example.branchedlauncher.ui.searchscreen

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.branchedlauncher.R
import com.example.branchedlauncher.model.App

class SearchAppsAdapter(
    private val context: Context?,
    private val viewModel: SearchScreenViewModel,
    private val textQuery: CharSequence
) : RecyclerView.Adapter<SearchAppsAdapter.SearchAppsViewHolder>() {

    private val apps: MutableList<App> = viewModel.loadApps()
    private val filteredApps: List<App> = viewModel.filterApps(apps, textQuery)

    class SearchAppsViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        val appIcon = view?.findViewById<ImageView>(R.id.app_icon)
        val appName = view?.findViewById<TextView>(R.id.app_name)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAppsViewHolder =
        SearchAppsViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.app_item, parent, false)
        )

    override fun onBindViewHolder(holder: SearchAppsViewHolder, position: Int) {
        filter(textQuery)
        val item = filteredApps[position]
        holder.appName?.text = item.name
        holder.appIcon?.setImageDrawable(item.icon)
        Log.d("textQuery", "$textQuery")

    }

    fun filter(textQuery : CharSequence) {
        textQuery
    }

    override fun getItemCount(): Int = apps.size
}