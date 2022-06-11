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
    private val viewModel: SearchScreenViewModel
) : RecyclerView.Adapter<SearchAppsAdapter.SearchAppsViewHolder>() {

    private val apps: MutableList<App> = viewModel.loadApps()
    var filteredApps: MutableList<App> = mutableListOf()

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
        val item = filteredApps[position]
        holder.appName?.text = item.name
        holder.appIcon?.setImageDrawable(item.icon)

        holder.itemView.setOnClickListener {
            val intent = holder.appName?.context?.packageManager?.getLaunchIntentForPackage(
                filteredApps[position].packageName
            )
            Log.d("apps[position].name", filteredApps[position].name)
            context?.startActivity(intent)
        }

    }

    override fun getItemCount(): Int = filteredApps.size

}