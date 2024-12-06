package pl.fmizielinski.riverside.ui.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import pl.fmizielinski.riverside.R
import pl.fmizielinski.riverside.model.MovieDetailsUiModel
import pl.fmizielinski.riverside.model.RatingUiModel

@Composable
fun DetailsScreen(movie: MovieDetailsUiModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Header(movie)
        if (movie is MovieDetailsUiModel.Loaded) {
            Details(
                director = movie.director,
                actors = movie.actors,
                runtime = movie.runtime,
                genre = movie.genre,
                country = movie.country,
                released = movie.released,
            )
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = movie.plot,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Header(movie: MovieDetailsUiModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
    ) {
        GlideImage(
            model = movie.posterUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .aspectRatio(3 / 4f),
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp),
        ) {
            Text(text = movie.title, style = MaterialTheme.typography.titleMedium)
            Text(text = movie.year, style = MaterialTheme.typography.bodySmall)
            Ratings(movie.ratings)
        }
    }
}

@Composable
fun Ratings(
    ratings: List<RatingUiModel>,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
    ) {
        ratings.forEach { rating ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
            ) {
                Icon(
                    painter = painterResource(id = rating.iconResId),
                    contentDescription = null,
                    modifier = Modifier
                        .height(16.dp)
                        .padding(end = 4.dp),
                    tint = Color.Unspecified,
                )
                Text(
                    text = rating.value,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}

@Composable
fun Details(
    director: String,
    actors: String,
    runtime: String,
    genre: String,
    country: String,
    released: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, bottom = 16.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(0.5f),
        ) {
            Text(
                text = stringResource(R.string.details_director, director),
                style = MaterialTheme.typography.bodySmall,
            )
            Text(
                text = stringResource(R.string.details_actors, actors),
                style = MaterialTheme.typography.bodySmall,
            )
            Text(
                text = stringResource(R.string.details_runtime, runtime),
                style = MaterialTheme.typography.bodySmall,
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = stringResource(R.string.details_genre, genre),
                style = MaterialTheme.typography.bodySmall,
            )
            Text(
                text = stringResource(R.string.details_country, country),
                style = MaterialTheme.typography.bodySmall,
            )
            Text(
                text = stringResource(R.string.details_released, released),
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailsScreenPreview() {
    MaterialTheme {
        DetailsScreen(previewMovie)
    }
}

private val previewMovie = MovieDetailsUiModel.Loaded(
    title = "The Matrix",
    year = "1999",
    posterUrl = "https://example.com/poster.jpg",
    plot = "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.",
    director = "Lana Wachowski, Lilly Wachowski",
    actors = "Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss",
    runtime = "136 min",
    genre = "Action, Sci-Fi",
    country = "USA",
    released = "31 Mar 1999",
    ratings = listOf(
        RatingUiModel(
            iconResId = R.drawable.ic_imdb,
            value = "8.7/10",
        ),
        RatingUiModel(
            iconResId = R.drawable.ic_rotten_tomatoes,
            value = "87%",
        ),
        RatingUiModel(
            iconResId = R.drawable.ic_metacritic,
            value = "73/100",
        ),
    ),
)
