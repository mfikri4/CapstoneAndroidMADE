package com.mfikri.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mfikri.core.R
import com.mfikri.core.databinding.ListItemsBinding
import com.mfikri.core.domain.model.Tv

class TvAdapter : RecyclerView.Adapter<TvAdapter.ListViewHolder>() {

    private var mData = ArrayList<Tv>()
    private var onItemClickCallback: OnItemClickCallback? = null

    interface OnItemClickCallback {
        fun onItemClicked(tv: Tv)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setData(tv: List<Tv>?) {
        if (tv == null) return
        mData.clear()
        mData.addAll(tv)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(private val binding: ListItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(list: Tv) {
            with(binding) {
                tvTitle.text = list.name
                tvDate.text = list.first_air_date
                tvRating.text = list.vote_average.toString()
                val urlPoster = "https://image.tmdb.org/t/p/w500/${list.poster_path}"
                Glide.with(itemView.context)
                    .load(urlPoster)
                    .apply(
                        RequestOptions().placeholder(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(ivFoto)

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(list) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemsBinding =
            ListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemsBinding)
    }


    override fun getItemCount(): Int = mData.size
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(mData[position])
    }
}