package pl.fmizielinski.riverside.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import pl.fmizielinski.riverside.R
import pl.fmizielinski.riverside.databinding.ItemMovieBinding
import pl.fmizielinski.riverside.model.MovieUiModel
import pl.fmizielinski.riverside.utils.SingleLiveEvent

class MovieAdapter : ListAdapter<MovieUiModel, MovieAdapter.MovieViewHolder>(MovieDiffCallback()) {

    private val _clicks = SingleLiveEvent<MovieUiModel>()
    val clicks: LiveData<MovieUiModel> = _clicks

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    inner class MovieViewHolder(itemView: View) : ViewHolder(itemView) {

        private val binding = ItemMovieBinding.bind(itemView)

        init {
            itemView.setOnClickListener {
                val movie = getItem(adapterPosition)
                _clicks.postValue(movie)
            }
        }

        fun bind(movie: MovieUiModel) {
            binding.title.text = movie.title
            binding.year.text = movie.year

            Glide.with(itemView)
                .load(movie.posterUrl)
                .into(binding.poster)
        }
    }

    class MovieDiffCallback : DiffUtil.ItemCallback<MovieUiModel>() {
        override fun areItemsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel): Boolean {
            return oldItem == newItem
        }
    }
}
