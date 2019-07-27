package com.example.android.challenge.ui

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.android.challenge.R
import com.example.android.challenge.model.Content

/**
 * View Holder for a [Content] RecyclerView list item.
 */
class ContentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val name: TextView = view.findViewById(R.id.title)
    private val description: TextView = view.findViewById(R.id.description)
    private val image: ImageView = view.findViewById(R.id.image)

    private var content: Content? = null

    fun bind(content: Content?) {
        if (content == null) {
            val resources = itemView.resources
            name.text = resources.getString(R.string.loading)
            description.visibility = View.GONE
        } else {
            showRepoData(content)
        }
    }

    private fun showRepoData(content: Content) {
        this.content = content
        name.text = content.title

        // if the description is missing, hide the TextView
        var descriptionVisibility = View.GONE
        if (content.description != null) {
            description.text = content.description
            descriptionVisibility = View.VISIBLE
        }
        description.visibility = descriptionVisibility

        if (content.imageHref != null)
            Glide.with(image.context)
                    .load(content.imageHref)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .fitCenter()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(image)
    }

    companion object {
        fun create(parent: ViewGroup): ContentViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.repo_view_item, parent, false)
            return ContentViewHolder(view)
        }
    }
}
