package com.areeb.boxoffice.data.cache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.areeb.boxoffice.data.model.Movie

@Entity
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val originalTitle: String = "",
    val overview: String = "",
    val posterPath: String? = "",
    val backdropPath: String? = "",
    val releaseDate: String = "",
    val voteAverage: Double = 0.0,
    var isBookmarked: Boolean = false,
    val addedDate: Long = System.currentTimeMillis()
)


fun List<MovieEntity>.toDomain() = map { Movie(it.id,it.originalTitle,it.overview,"https://image.tmdb.org/t/p/w500${it.posterPath}", "https://image.tmdb.org/t/p/w500${it.backdropPath}",it.releaseDate,it.voteAverage,it.isBookmarked) }

enum class MovieType(val value: String) {
    NowPlaying("now_playing"),
    Popular("popular"),
    Upcoming("upcoming"),
    TopRated("top_rated");


    companion object {
        fun fromValue(value: String?): MovieType? {
            return entries.find { it.value == value }
        }
    }
}


