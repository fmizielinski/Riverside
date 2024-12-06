package pl.fmizielinski.riverside.model

import androidx.annotation.DrawableRes
import pl.fmizielinski.riverside.R
import pl.fmizielinski.riverside.domain.model.Rating

data class RatingUiModel(
    @DrawableRes val iconResId: Int,
    val value: String,
)

fun Rating.toMovieUiModel(): RatingUiModel {
    val iconResId = when (this) {
        is Rating.Imdb -> R.drawable.ic_imdb
        is Rating.RottenTomatoes -> R.drawable.ic_rotten_tomatoes
        is Rating.Metacritic -> R.drawable.ic_metacritic
    }
    return RatingUiModel(iconResId, value)
}
