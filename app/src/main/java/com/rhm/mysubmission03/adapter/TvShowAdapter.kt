package com.rhm.mysubmission03.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.rhm.mysubmission03.R
import com.rhm.mysubmission03.api.TvShowItems
import com.rhm.mysubmission03.ui.activity.DetailClickActivity

class TvShowAdapter(
    private var tvShows: List<TvShowItems>
) : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): TvShowViewHolder {
        val view = LayoutInflater
            .from(viewGroup.context)
            .inflate(R.layout.item_tvshow, viewGroup, false)
        return TvShowViewHolder(view)
    }

    override fun getItemCount(): Int = tvShows.size

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bind(tvShows[position])
    }

    fun updateTvShows(tvShows: List<TvShowItems>) {
        this.tvShows = tvShows
        notifyDataSetChanged()
    }

    inner class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val poster: ImageView = itemView.findViewById(R.id.img_item_photo)
        private val title: TextView = itemView.findViewById(R.id.tv_item_title)
        private val release_date: TextView = itemView.findViewById(R.id.tv_item_date)
        private val description: TextView = itemView.findViewById(R.id.tv_item_description)

        fun bind(tvShow: TvShowItems) {
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w342${tvShow.posterPath}")
                .transform(CenterCrop())
                .into(poster)

            title.text = tvShow.name
            release_date.text = tvShow.firstAirDate
            description.text = tvShow.overview

            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "Pilih ${tvShow.name}", Toast.LENGTH_SHORT).show()

                val detailIntent = Intent(itemView.context, DetailClickActivity::class.java)

                val listTvShow = TvShowItems(
                    tvShow.id,
                    tvShow.name,
                    tvShow.overview,
                    tvShow.posterPath,
                    tvShow.backdropPath,
                    tvShow.rating,
                    tvShow.firstAirDate
                )

                detailIntent.putExtra(DetailClickActivity.EXTRA_TV_SHOW, listTvShow)
                detailIntent.putExtra(DetailClickActivity.EXTRA_ADAPTER, "tvShow")

                itemView.context.startActivity(detailIntent)
            }
        }

    }

}